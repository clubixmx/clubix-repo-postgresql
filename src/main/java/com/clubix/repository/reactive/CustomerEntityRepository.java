package com.clubix.repository.reactive;

import com.clubix.repository.entity.CustomerEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerEntityRepository extends ReactiveCrudRepository<CustomerEntity, String> {

}

