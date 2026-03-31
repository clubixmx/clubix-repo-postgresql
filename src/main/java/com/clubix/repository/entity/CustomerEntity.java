package com.clubix.repository.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerEntity {
    private String id;
    private Double balance;
}
