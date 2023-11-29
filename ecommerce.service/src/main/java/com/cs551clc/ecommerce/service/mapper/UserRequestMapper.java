package com.cs551clc.ecommerce.service.mapper;

import com.cs551clc.ecommerce.service.data.request.RegisterUserRequest;
import com.cs551clc.ecommerce.service.data.dynamodb.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserRequestMapper {
    User mapUser(RegisterUserRequest registerUserRequest);
}
