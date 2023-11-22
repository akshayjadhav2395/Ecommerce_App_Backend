package com.myEcom.controller;

import com.myEcom.payload.JwtRequest;
import com.myEcom.payload.JwtResponse;
import com.myEcom.payload.UserDto;
import com.myEcom.security.JwtHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) throws Exception
    {
        //authenticate
        this.authenticateUser(jwtRequest.getUsername(), jwtRequest.getPassword());

        //loading user from userDetailService
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());

        //generating Token
        String token = jwtHelper.generateToken(userDetails);

        JwtResponse jwtResponse=new JwtResponse();
        jwtResponse.setToken(token);
        jwtResponse.setUser(this.modelMapper.map(userDetails, UserDto.class));

        return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.OK);
    }

    private void authenticateUser(String username, String password) throws Exception {

        try {
            //authenticate
            Authentication authenticate = this.manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException exception)
        {
            throw new Exception("Invalid Username or Password..!");
        }
        catch (DisabledException exception)
        {
            throw new Exception("User is not Active..!");
        }
    }

}
