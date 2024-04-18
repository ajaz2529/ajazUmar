package com.ums.controller;

import com.ums.entity.AppUser;
import com.ums.payload.JWTResponse;
import com.ums.payload.UserDTO;
import com.ums.payload.loginDTO;
import com.ums.service.userServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final userServiceImpl userService;

    public AuthController(userServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/{addUser}")
    public ResponseEntity<String> addUser(@RequestBody UserDTO user){
    	System.out.println("hello");
        userService.addUser(user);
        return new ResponseEntity<>("done..", HttpStatus.CREATED);
    }
    @PostMapping("/addUser/users")
    public ResponseEntity<String> addUsers(@RequestBody List<UserDTO> users){
        System.out.println(users);
        return new ResponseEntity<>("done..", HttpStatus.CREATED);
    }
    //public ResponseEntity<?> VerifyLogin()  if we use ? it means it can return multiple types of values
    @PostMapping("/login")
    public ResponseEntity<?> VerifyLogin(@RequestBody @NotNull loginDTO logindto){
        String token = userService.VerifyLogin(logindto);
        if(token!=null)  {
            JWTResponse response = new JWTResponse();
            response.setToken(token);
            return new ResponseEntity<>(response,HttpStatus.OK);}
        return new ResponseEntity<>("incorrect credentials",HttpStatus.UNAUTHORIZED);
    }
    @GetMapping("/token")
    public String getCountry(){
        return "token";
    }
    @GetMapping("/profile")
    public AppUser getCurrentProfile(@AuthenticationPrincipal AppUser user){
        return user;
    }



}
