package com.myEcom.services;

import com.myEcom.payload.UserDto;

import java.util.List;

public interface UserServiceI {

    public UserDto createUser(UserDto UserDto);
    public List<UserDto> getUsers();
    public UserDto getSingleUser(int userId);
    public UserDto updateUser(UserDto userDto, int userId);

    public UserDto getByUserEmail(String email);
    public void deleteUser(int userId);
}
