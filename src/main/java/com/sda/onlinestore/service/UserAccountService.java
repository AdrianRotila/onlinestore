package com.sda.onlinestore.service;


import com.sda.onlinestore.dto.AddressDto;
import com.sda.onlinestore.dto.ProductDto;
import com.sda.onlinestore.dto.UserAccountDto;
import com.sda.onlinestore.entity.Product;
import com.sda.onlinestore.entity.Role;
import com.sda.onlinestore.entity.UserAccount;
import com.sda.onlinestore.exception.NotFoundException;
import com.sda.onlinestore.repository.UserAccountRepository;
import com.sda.onlinestore.transformers.UserAccountTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserAccountService implements UserDetailsService {

    @Autowired
    private final UserAccountRepository userAccountRepository;

    @Autowired
    private UserAccountTransformer userAccountTransformer;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public UserAccount saveUserAccount(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    public UserAccount findUserAccountById(Long id) {
        Optional<UserAccount> optUserAccount = userAccountRepository.findById(id);
        if(optUserAccount.isPresent()){
            UserAccount userAccount = optUserAccount.get();
            System.out.println(userAccount.toString());
            return userAccount;
        } else {
            System.out.println("User account with ID " + id + " does not exist.");
            throw new NotFoundException("User account with ID " + id + " does not exist.");
        }
    }

    public Optional<UserAccount> findUserAccountByUsername(String username) {
        return this.userAccountRepository.findUserAccountByUsername(username);
    }

    public void deleteUserAccountById(Long id) {
        this.findUserAccountById(id);
        userAccountRepository.deleteById(id);
    }

    public void addUserAccount(UserAccountDto userAccountDto) {
        UserAccount userAccount = userAccountTransformer.transform(userAccountDto);
        Role role = new Role();
        if (userAccount.getUsername().equals("admin")) {
            role.setRoleName("ADMIN");
        } else {
            role.setRoleName("USER");
        }
        userAccount.setRole(role);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userAccount.setPassword(bCryptPasswordEncoder.encode(userAccountDto.getPassword()));
        userAccountRepository.save(userAccount);
    }

    public List<UserAccountDto> getUserAccounts() {
        List<UserAccount> userAccounts = userAccountRepository.findAll();
        List<UserAccountDto> userAccountDtoList = new ArrayList<>();

        for (UserAccount userAccount : userAccounts) {
            UserAccountDto userAccountDto = userAccountTransformer.transformReversed(userAccount);
            userAccountDtoList.add(userAccountDto);
        }
        return userAccountDtoList;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> optUserAccount = this.userAccountRepository.findUserAccountByUsername(username);
        if (optUserAccount.isPresent()) {
            return new org.springframework.security.core.userdetails.User(
                    optUserAccount.get().getUsername(),
                    optUserAccount.get().getPassword(),
                    Arrays.asList(new SimpleGrantedAuthority(optUserAccount.get().getRole().toString()))
            );
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

}
