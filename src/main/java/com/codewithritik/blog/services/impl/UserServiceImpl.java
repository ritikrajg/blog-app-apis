package com.codewithritik.blog.services.impl;

import java.util.ArrayList;
import java.util.List;
import com.codewithritik.blog.exceptions.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithritik.blog.entities.User;
import com.codewithritik.blog.payloads.UserDto;
import com.codewithritik.blog.repositories.UserRepo;
import com.codewithritik.blog.services.UserService;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
        this.userRepo.delete(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=this.userRepo.findAll();
        List<UserDto> userDtos=new ArrayList<>();
        for(User user:users){
            UserDto userDto=new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDto.setPassword(user.getPassword());
            userDto.setAbout(user.getAbout());
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
        UserDto userDto=this.userToDto(user);
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser=this.userRepo.save(user);
        UserDto userDto1=this.userToDto(updatedUser);
        return userDto1;
    }
    public User dtoToUser(UserDto userDto){
        User user=this.modelMapper.map(userDto,User.class);
        // user.setId(userDto.getId());
        // user.setName(userDto.getName());
        // user.setEmail(userDto.getEmail());
        // user.setAbout(userDto.getAbout());
        // user.setPassword(userDto.getPassword());
        return user;
    }
    public UserDto userToDto(User user){
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
        // userDto.setId(user.getId());
        // userDto.setName(user.getName());
        // userDto.setEmail(user.getEmail());
        // userDto.setAbout(user.getAbout());
        // userDto.setPassword(user.getPassword());
        return userDto;
    }
}
