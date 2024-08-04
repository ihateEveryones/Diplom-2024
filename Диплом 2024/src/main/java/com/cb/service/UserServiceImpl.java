package com.cb.service;


import com.cb.dto.UserDto;
import com.cb.model.Roles;
import com.cb.model.User;
import com.cb.repository.RoleRepository;
import com.cb.repository.UserRepository;
import com.cb.util.TbConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Override
    public void saveUser(UserDto userDto) {
        Roles role = roleRepository.findByName(TbConstants.Roles.USER);

        if (role == null)
            role = roleRepository.save(new Roles(TbConstants.Roles.USER));

        User user = new User(userDto.getName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
                role,userDto.getSurname(),userDto.getPatronymic(),userDto.getPhone_number());
        userRepository.save(user);
    }
    public boolean changePassword(String username, String currentPassword, String newPassword) {
        Optional<User> optionalUser = userRepository.findUserByEmail(username);
        if (!optionalUser.isPresent()) {
            return false;
        }
        User user = optionalUser.get();
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return true;
    }

    public boolean changeEmail(String email, String newEmail,String newUsername,String newPhoneNumber,String newSurname,String newPatronymic ) {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if (!optionalUser.isPresent()) {
            return false;
        }
        User user = optionalUser.get();
        user.setName(newUsername);
        user.setEmail(newEmail);
        user.setPhone_number(newPhoneNumber);
        user.setSurname(newSurname);
        user.setPatronymic(newPatronymic);
        userRepository.save(user);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(newEmail);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return true;
    }
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
