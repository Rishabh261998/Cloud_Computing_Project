package com.cs551clc.ecommerce.service.service;

import com.cs551clc.ecommerce.service.data.request.LoginUserRequest;
import com.cs551clc.ecommerce.service.data.request.RegisterUserRequest;
import com.cs551clc.ecommerce.service.data.dynamodb.User;
import com.cs551clc.ecommerce.service.mapper.UserRequestMapper;
import com.cs551clc.ecommerce.service.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    UserRequestMapper mapper;

    @Autowired
    UserRepository repository;

    public void registerUser(RegisterUserRequest registerUserRequest) throws Exception {
        User user = mapper.mapUser(registerUserRequest);
        if(!repository.getUserById(user.getUsername()).isEmpty())
            throw new BadRequestException("username already exists");
        if(!repository.getUserByEmail(user.getEmail()).isEmpty())
            throw new BadRequestException("email-id already exists");
        repository.saveUser(user);
    }

    public User loginUser(LoginUserRequest loginUserRequest) throws Exception{
        List<User> userList = repository.getUserById(loginUserRequest.getUsername());
        if(userList.isEmpty() || !userList.get(0).getPassword().equals(loginUserRequest.getPassword()))
        {
            throw new BadRequestException("Invalid username/password!!");
        }
        return userList.get(0);
    }

}
