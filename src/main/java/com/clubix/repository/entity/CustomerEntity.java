package com.clubix.repository.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("customer")
public class CustomerEntity {
    @Id
    private String id;
    private Double balance;
}
