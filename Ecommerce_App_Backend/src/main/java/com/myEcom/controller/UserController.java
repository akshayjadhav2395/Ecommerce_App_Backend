package com.myEcom.controller;

import com.myEcom.payload.ApiResponse;
import com.myEcom.payload.UserDto;
import com.myEcom.services.UserServiceI;
import com.myEcom.services.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceI userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto)
    {
        userDto.setCreateAt(new Date());
        userDto.setActive(true);
        UserDto savedUser = this.userService.createUser(userDto);
        return new ResponseEntity<UserDto>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser()
    {
        List<UserDto> userList = this.userService.getUsers();
        return new ResponseEntity<List<UserDto>>(userList, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable int userId)
    {
        UserDto singleUser = this.userService.getSingleUser(userId);
        return new ResponseEntity<UserDto>(singleUser, HttpStatus.OK);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable int userId)
    {
        UserDto updatedUser = this.userService.updateUser(userDto, userId);
        return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int userId)
    {
        this.userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>( new ApiResponse("User Deleted Successfully..!", true) , HttpStatus.OK);
    }
}
