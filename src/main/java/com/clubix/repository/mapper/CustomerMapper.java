package com.clubix.repository.mapper;

import com.clubix.entity.Customer;
import com.clubix.repository.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {

    Customer toCustomer(CustomerEntity entity);
}

