package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.UserAccountDto;
import com.sda.onlinestore.dto.UserDto;
import com.sda.onlinestore.entity.Role;
import com.sda.onlinestore.entity.UserAccount;
import com.sda.onlinestore.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service("userService")
public class UserDetailService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserDetailService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public Optional<UserAccount> findUserAccountByUsername(String username) {
        return this.userAccountRepository.findUserAccountByUsername(username);
    }

    public UserAccount register(UserDto userDto) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(userDto.getUsername());
        userAccount.setPassword(bcryptEncoder.encode(userDto.getPassword()));
        userAccount.setRole(userDto.getRole());
        return userAccountRepository.save(userAccount);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> userCredentialModelOptional = userAccountRepository.findUserAccountByUsername(username);
        if (!userCredentialModelOptional.isPresent()) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        UserAccount userCredentialModel = userCredentialModelOptional.get();
        String userName = userCredentialModel.getUsername();
        String password = userCredentialModel.getPassword();
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        //ROLE_ADMIN is important to be picked up by hasRole from @PreAuthorize in DummyController
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + userCredentialModel.getRole().getRoleName().toUpperCase());
        authorities.add(simpleGrantedAuthority);
        return new User(userName, password, authorities);
    }
}

