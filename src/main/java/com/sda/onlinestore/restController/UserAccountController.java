package com.sda.onlinestore.restController;

import com.sda.onlinestore.dto.CategoryDto;
import com.sda.onlinestore.dto.UserAccountDto;
import com.sda.onlinestore.entity.Category;
import com.sda.onlinestore.entity.UserAccount;
import com.sda.onlinestore.service.UserAccountService;
import com.sda.onlinestore.transformers.UserAccountTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/userAccount")
public class UserAccountController {

    private final UserAccountService userAccountService;

    private final UserAccountTransformer userAccountTransformer;

    @Autowired
    public UserAccountController(UserAccountService userAccountService, UserAccountTransformer userAccountTransformer){
        this.userAccountService = userAccountService;
        this.userAccountTransformer = userAccountTransformer;
    }

    @PostMapping
    public ResponseEntity<UserAccountDto> createUserAccount(@RequestBody UserAccountDto userAccountDto){
        UserAccount userAccount = userAccountTransformer.transform(userAccountDto);
        UserAccount savedUserAccount = userAccountService.saveUserAccount(userAccount);
        UserAccountDto savedUserAccountDto = userAccountTransformer.transformReversed(savedUserAccount);
        return ResponseEntity.ok(savedUserAccountDto);
    }

    @GetMapping
    public ResponseEntity<List<UserAccountDto>> getUserAccounts(){
        List<UserAccountDto> userAccountDtoList = userAccountService.getUserAccounts();
        return ResponseEntity.ok(userAccountDtoList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserAccountDto> findUserAccountById(@PathVariable("id") Long id) {
        UserAccount userAccount = userAccountService.findUserAccountById(id);
        UserAccountDto userAccountDto = userAccountTransformer.transformReversed(userAccount);
        return ResponseEntity.ok(userAccountDto);
    }

//    @GetMapping
//    public ResponseEntity<List<UserAccountDto>> getUserAccountByUsername(@RequestParam(value = "username") String username){
//        Optional<UserAccount> userAccounts = userAccountService.findUserAccountByUsername(username);
//        List<UserAccountDto> userAccountDtos = userAccounts.stream().map(userAccountTransformer::transformReversed).collect(Collectors.toList());
//        return ResponseEntity.ok(userAccountDtos);
//    }

    @PutMapping
    public ResponseEntity<UserAccountDto> updateUserAccount(@RequestBody UserAccountDto userAccountDto) {
        UserAccount userAccount = userAccountTransformer.transform(userAccountDto);
        UserAccount savedUserAccount = userAccountService.saveUserAccount(userAccount);
        UserAccountDto savedUserAccountDto = userAccountTransformer.transformReversed(savedUserAccount);
        return ResponseEntity.ok(savedUserAccountDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUserAccountById(@PathVariable("id") Long id) {
        userAccountService.deleteUserAccountById(id);
        return ResponseEntity.noContent().build();
    }
}
