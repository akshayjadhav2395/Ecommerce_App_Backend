package com.myEcom.services.serviceImpl;

import com.myEcom.Exceptions.ResourceNotFoundException;
import com.myEcom.entity.User;
import com.myEcom.payload.UserDto;
import com.myEcom.repository.UserRepository;
import com.myEcom.services.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServiceI {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto UserDto) {
        User user = this.toEntity(UserDto);
        User savedUser = this.userRepository.save(user);
        return this.toDto(savedUser);
    }

    @Override
    public List<UserDto> getUsers() {

        List<User> allUser = userRepository.findAll();
        List<UserDto> allDtos = allUser.stream().map(user -> this.toDto(user)).collect(Collectors.toList());

        return allDtos;
    }

    @Override
    public UserDto getSingleUser(int userId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found: "+userId));
        return this.toDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, int userId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found: "+userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user.setActive(userDto.isActive());
        user.setCreateAt(userDto.getCreateAt());
        user.setGender(userDto.getGender());
        user.setPhoneNumber(userDto.getPhoneNumber());

        User updatedUser=userRepository.save(user);
        return this.toDto(updatedUser);
    }

    @Override
    public UserDto getByUserEmail(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User not found: "+email));

        return this.toDto(user);
    }

    @Override
    public void deleteUser(int userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found: "+userId));
        userRepository.delete(user);
    }

    public UserDto toDto(User user)
    {
        UserDto userDto=new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setAddress(user.getAddress());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        userDto.setActive(user.isActive());
        userDto.setCreateAt(user.getCreateAt());
        userDto.setGender(user.getGender());
        userDto.setPhoneNumber(user.getPhoneNumber());
        return userDto;
    }

    public User toEntity(UserDto userDto)
    {
        User user=new User();
        user.setUserId(userDto.getUserId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user.setActive(userDto.isActive());
        user.setCreateAt(userDto.getCreateAt());
        user.setGender(userDto.getGender());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return user;
    }
}
