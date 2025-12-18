package com.fu.bookshop.controller;

import com.fu.bookshop.entity.Shop;
import com.fu.bookshop.service.EditShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/manager/shop")
@RequiredArgsConstructor
public class EditShopController {

    private final EditShopService shopService;

    // GET hiển thị form
    @GetMapping("/edit")
    public String editShopForm(@RequestParam(defaultValue = "1") Long id,
                               Model model,
                               @ModelAttribute("successMessage") String successMessage) {

        Shop shop = shopService.getShop(id)
                .orElseThrow(() -> new RuntimeException("Shop not found"));
        model.addAttribute("shop", shop);//gui data xuong html
        return "manager/edit";
    }

    @PostMapping("/edit")
    public String updateShop(@RequestParam(defaultValue = "1") Long id,
                             @ModelAttribute Shop shop,//nhan data,list tu form html
                             @RequestParam(name="banners", required=false) List<String> banners,
                             RedirectAttributes redirectAttributes) {

        shop.setBanners(banners); // gán banners từ form
        shopService.updateShop(id, shop);//goi service xu ly data tu form html

        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật Shop thành công!");
        return "redirect:/manager/shop/edit?id=" + id;
    }

}
