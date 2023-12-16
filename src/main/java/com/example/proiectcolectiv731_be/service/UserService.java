package com.example.proiectcolectiv731_be.service;

import com.example.proiectcolectiv731_be.model.User;
import com.example.proiectcolectiv731_be.model.UserDto;
import com.example.proiectcolectiv731_be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // calls repository function to check whether the given username is unique or not
    public boolean isUsernameUnique(String username) {
        return !userRepository.existsByUsername(username);
    }

    public void signUp(UserDto userDto) {
        String username = userDto.getUsername();

        // Check if the username is unique
        // TODO: 11/18/2023 Custom exceptions for better exception handling, maybe
        if (!isUsernameUnique(username)) {
            throw new IllegalArgumentException("Username already exists!");
        }

        User user = new User();
        user.createNewUserFromDto(userDto);
        userRepository.save(user);
    }

    public void updateDetails(UserDto userDto){
        User user = new User();
        user.createNewUserFromDto(userDto);
        User dbUserInstance=userRepository.findByUsername(user.getUsername());
        dbUserInstance.setPassword(user.getPassword());
        dbUserInstance.setEmail(user.getEmail());
        dbUserInstance.setAddress(user.getAddress());
        dbUserInstance.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(dbUserInstance);
    }


    public void updatePassword(String username,String newPass){
        //changes the password in the database
        User result= userRepository.findByUsername(username);
        result.setPassword(newPass);
        userRepository.save(result);

    }
    public String getEmailByUsername(String username){
        // gets the email address of a user based on their username
        User result= userRepository.findByUsername(username);
        return result.getEmail();
    }

    public Optional<User> getUserByUsername(String username){
        return userRepository.getUserByUsername(username);
    }



}
