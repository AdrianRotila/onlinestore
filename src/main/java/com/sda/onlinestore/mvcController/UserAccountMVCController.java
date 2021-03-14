package com.sda.onlinestore.mvcController;

import com.sda.onlinestore.dto.UserAccountDto;
import com.sda.onlinestore.entity.UserAccount;
import com.sda.onlinestore.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserAccountMVCController {

    private final UserAccountService userAccountService;

    @Autowired
    public UserAccountMVCController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @RequestMapping("/login")
    public String login() {
        return "signinSignup";
    }
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "signinSignup";
    }

    @GetMapping(path = "/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userRegister", new UserAccountDto());
        return "signinSignup";
    }


    @PostMapping(path = "/user/register")
    public String registerUser(@ModelAttribute("userRegister") @Valid UserAccountDto userAccountDto, BindingResult result) {
        Optional<UserAccount> optUserAccount = this.userAccountService.findUserAccountByUsername(userAccountDto.getUsername());
        if (optUserAccount.isPresent()) {
            result.rejectValue("username", null, "Username already exists.");
        }
        if (!userAccountDto.getPassword().equals(userAccountDto.getConfirmPassword())) {
            result.rejectValue("password", null, "Passwords are not matching!");
        }
        if (result.hasErrors()) {
            return "signinSignup";
        }
        userAccountService.addUserAccount(userAccountDto);
        return "signinSignup";
    }

    @GetMapping(path = "/viewUserAccounts")
    public String viewUserAccounts(Model model) {
        model.addAttribute("users", this.userAccountService.getUserAccounts());
        return "userAccount-list";
    }

    @GetMapping(path = "/userAccount/edit/{id}")
    public String showEditPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("userAccount", this.userAccountService.findUserAccountById(id));
        return "edit-userAccount";
    }

    @GetMapping(path = "/userAccount/delete/{id}")
    public String deleteUserAccountById(@PathVariable("id") Long id, Model model) {
        this.userAccountService.deleteUserAccountById(id);
        return "redirect:/viewUserAccounts";
    }

    @PostMapping(path = "/userAccount/update")
    public String editUserAccount(@ModelAttribute("userAccount") @Valid UserAccount userAccount, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-userAccount";
        }
        this.userAccountService.saveUserAccount(userAccount);
        return "redirect:/viewUserAccounts";
    }

}
