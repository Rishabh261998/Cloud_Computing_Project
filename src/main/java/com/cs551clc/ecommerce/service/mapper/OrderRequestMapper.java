package com.cs551clc.ecommerce.service.mapper;

import com.cs551clc.ecommerce.service.data.dynamodb.Order;
import com.cs551clc.ecommerce.service.data.request.OrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

@Mapper(imports= {DateTime.class, DateTimeFormat.class}, componentModel = "spring")
public interface OrderRequestMapper{

    @Mapping(target = "dateTime", expression = "java(DateTimeFormat.forPattern(\"yyyy-MM-dd'T'HH:mm:ss'Z'\").print(DateTime.now()))")
    Order mapOrder(OrderRequest orderRequest);
}
