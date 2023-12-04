package com.cs551clc.ecommerce.service.mapper;

import com.cs551clc.ecommerce.service.data.dynamodb.Order;
import com.cs551clc.ecommerce.service.data.request.OrderRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderRequestMapper{

    Order mapOrder(OrderRequest orderRequest);
}
