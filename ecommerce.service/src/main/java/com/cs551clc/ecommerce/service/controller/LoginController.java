package com.cs551clc.ecommerce.service.controller;

import com.cs551clc.ecommerce.service.data.request.LoginUserRequest;
import com.cs551clc.ecommerce.service.data.request.RegisterUserRequest;
import com.cs551clc.ecommerce.service.data.dynamodb.User;
import com.cs551clc.ecommerce.service.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://23.20.222.173:3000", methods={RequestMethod.POST, RequestMethod.PUT})
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PutMapping("/register")
    @ResponseBody
    public ResponseEntity<Void> registerUser(@RequestBody RegisterUserRequest request) throws Exception
    {
       loginService.registerUser(request);

       return  new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody LoginUserRequest request) throws Exception
    {
        return new ResponseEntity<>(loginService.loginUser(request),HttpStatus.OK);
    }
}
