package com.clubix.repository;

import com.clubix.repository.mapper.CustomerMapper;
import com.clubix.repository.reactive.CustomerEntityRepository;
import com.clubix.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Repository
public class CustomerPostgreSQLRepository implements CustomerRepository {

    private final CustomerEntityRepository repository;
    private final CustomerMapper mapper;

    @Override
    public Mono<Customer> findById(String id) {
        return repository.findById(id).map(mapper::toCustomer);
    }
}
