package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.AddressDto;
import com.sda.onlinestore.dto.RoleDto;
import com.sda.onlinestore.dto.UserAccountDto;
import com.sda.onlinestore.entity.Address;
import com.sda.onlinestore.entity.Role;
import com.sda.onlinestore.entity.UserAccount;
import com.sda.onlinestore.exception.NotFoundException;
import com.sda.onlinestore.repository.RoleRepository;
import com.sda.onlinestore.repository.UserAccountRepository;
import com.sda.onlinestore.transformers.RoleTransformer;
import com.sda.onlinestore.transformers.UserAccountTransformer;
import org.springframework.beans.factory.annotation.Autowired;
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
    private BCryptPasswordEncoder encoder;

    @Autowired
    private final UserAccountRepository userAccountRepository;
    @Autowired
    private final UserAccountTransformer userAccountTransformer;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final RoleTransformer roleTransformer;



    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository, RoleRepository roleRepository, RoleTransformer roleTransformer, UserAccountTransformer userAccountTransformer) {
        this.roleRepository = roleRepository;
        this.userAccountRepository = userAccountRepository;
        this.roleTransformer = roleTransformer;
        this.userAccountTransformer = userAccountTransformer;
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
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        UserAccount userAccount = userAccountTransformer.transform(userAccountDto);


        Role role = new Role("ADMIN");
        userAccount.setRole(role);
        userAccount.setPassword(bCryptPasswordEncoder.encode(userAccountDto.getPassword()));

        userAccountRepository.save(userAccount);
    }

    public List<UserAccountDto> getAllUserAccounts() {
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
