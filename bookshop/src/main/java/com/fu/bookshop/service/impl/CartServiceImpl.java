package com.fu.bookshop.service.impl;

import com.fu.bookshop.dto.CartDTO;
import com.fu.bookshop.dto.CartItemDTO;
import com.fu.bookshop.entity.Account;
import com.fu.bookshop.entity.Book;
import com.fu.bookshop.entity.Cart;
import com.fu.bookshop.entity.CartItem;
import com.fu.bookshop.entity.User;
import com.fu.bookshop.repository.BookRepository;
import com.fu.bookshop.repository.CartItemRepository;
import com.fu.bookshop.repository.CartRepository;
import com.fu.bookshop.repository.UserRepository;
import com.fu.bookshop.service.home.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public CartDTO getCartByAccount(Account account) {
        User user = userRepository.findByAccount(account)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElse(Cart.builder()
                        .user(user)
                        .build());

        // Nếu cart chưa có ID (chưa lưu trong DB), trả về cart rỗng
        if (cart.getId() == null) {
            return CartDTO.builder()
                    .items(List.of())
                    .totalItems(0)
                    .totalPrice(BigDecimal.ZERO)
                    .build();
        }

        List<CartItem> cartItems = cartItemRepository.findByCartIdWithBook(cart.getId());
        
        List<CartItemDTO> itemDTOs = cartItems.stream()
                .map(this::mapToCartItemDTO)
                .collect(Collectors.toList());

        int totalItems = itemDTOs.stream()
                .mapToInt(CartItemDTO::getQuantity)
                .sum();

        BigDecimal totalPrice = itemDTOs.stream()
                .map(CartItemDTO::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return CartDTO.builder()
                .id(cart.getId())
                .items(itemDTOs)
                .totalItems(totalItems)
                .totalPrice(totalPrice)
                .build();
    }

    @Override
    @Transactional
    public void addToCart(Account account, Long bookId, Integer quantity) {
        User user = userRepository.findByAccount(account)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Kiểm tra số lượng tồn kho
        if (book.getQuantity() == null || book.getQuantity() <= 0) {
            throw new RuntimeException("Sách này hiện không còn trong kho");
        }

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = Cart.builder().user(user).build();
                    return cartRepository.save(newCart);
                });

        // Lấy tất cả cart items của cart này
        List<CartItem> existingItems = cartItemRepository.findByCartIdWithBook(cart.getId());
        
        // Kiểm tra xem sản phẩm đã có trong giỏ chưa
        CartItem existingItem = existingItems.stream()
                .filter(item -> item.getBook().getId().equals(bookId))
                .findFirst()
                .orElse(null);

        int totalQuantity = quantity;
        if (existingItem != null) {
            totalQuantity = existingItem.getQuantity() + quantity;
        }

        // Kiểm tra tổng số lượng có vượt quá tồn kho không
        if (totalQuantity > book.getQuantity()) {
            throw new RuntimeException("Số lượng sách trong kho chỉ còn " + book.getQuantity() + " cuốn");
        }

        if (existingItem != null) {
            // Nếu đã có, tăng số lượng
            existingItem.setQuantity(totalQuantity);
            cartItemRepository.save(existingItem);
        } else {
            // Nếu chưa có, tạo mới
            CartItem newItem = CartItem.builder()
                    .cart(cart)
                    .book(book)
                    .quantity(quantity)
                    .build();
            cartItemRepository.save(newItem);
        }
    }

    @Override
    @Transactional
    public void updateCartItemQuantity(Account account, Long cartItemId, Integer quantity) {
        User user = userRepository.findByAccount(account)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CartItem cartItem = cartItemRepository.findByIdWithBook(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        // Kiểm tra cart item có thuộc về user không
        if (!cartItem.getCart().getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        if (quantity <= 0) {
            cartItemRepository.delete(cartItem);
        } else {
            // Kiểm tra số lượng tồn kho
            Book book = cartItem.getBook();
            if (book.getQuantity() == null || book.getQuantity() <= 0) {
                throw new RuntimeException("Sách này hiện không còn trong kho");
            }

            // Kiểm tra số lượng có vượt quá tồn kho không
            if (quantity > book.getQuantity()) {
                throw new RuntimeException("Số lượng sách trong kho chỉ còn " + book.getQuantity() + " cuốn");
            }

            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
        }
    }

    @Override
    @Transactional
    public void removeCartItem(Account account, Long cartItemId) {
        User user = userRepository.findByAccount(account)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        // Kiểm tra cart item có thuộc về user không
        if (!cartItem.getCart().getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        cartItemRepository.delete(cartItem);
    }

    private CartItemDTO mapToCartItemDTO(CartItem cartItem) {
        Book book = cartItem.getBook();
        BigDecimal subTotal = book.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));

        return CartItemDTO.builder()
                .id(cartItem.getId())
                .bookId(book.getId())
                .bookTitle(book.getTitle())
                .bookImage(book.getUrlImage())
                .bookPrice(book.getPrice())
                .quantity(cartItem.getQuantity())
                .availableQuantity(book.getQuantity() != null ? book.getQuantity() : 0)
                .subTotal(subTotal)
                .build();
    }
}

