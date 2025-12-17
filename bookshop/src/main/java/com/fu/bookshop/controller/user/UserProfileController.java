package com.fu.bookshop.controller.user;

import com.fu.bookshop.dto.user.OrderHistoryDTO;
import com.fu.bookshop.dto.user.UserProfileDTO;
import com.fu.bookshop.entity.User;
import com.fu.bookshop.enums.Gender;
import com.fu.bookshop.enums.OrderStatus;
import com.fu.bookshop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class UserProfileController {

    private final UserService userService;

    @GetMapping
    public String profile (
            @RequestParam(name = "status", required = false)OrderStatus status,
            Principal principal,
            Model model
            ){
        String email = principal.getName();


        UserProfileDTO user = userService.getUserProfileByEmail(email);

        List<OrderHistoryDTO> orders;

        if(status == null){
            orders = userService.getOrderHistory(email);
        }else {
            orders = userService.getOrderHistoryByStatus(email, status);
        }

        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        model.addAttribute("currentStatus", status);

        return "user/profile";
    }

    @GetMapping("/edit")
    public String editProfile(Principal principal,
                              Model model){
        String email = principal.getName();

        UserProfileDTO user = userService.getUserProfileByEmail(email);

        model.addAttribute("user", user);
        model.addAttribute("genders", Gender.values());


        return "user/edit-profile";
    }

    @PostMapping("/edit")
    public String updateProfile(
            Principal principal,
            @Valid UserProfileDTO user,
            BindingResult bindingResult,
            @RequestParam(required = false)MultipartFile avatar,
            Model model
            ){
        if (bindingResult.hasErrors()) {
            model.addAttribute("genders", Gender.values());
            return "user/edit-profile";
        }
        String email = principal.getName();




        userService.updateProfile(email, user.getName(), user.getDateOfBirth(), user.getGender(),
                avatar);
        return "redirect:/profile";


    }


}
