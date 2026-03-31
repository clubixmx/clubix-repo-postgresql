package com.clubix.repository;

import com.clubix.entity.Customer;
import com.clubix.repository.entity.CustomerEntity;
import com.clubix.repository.reactive.CustomerEntityRepository;
import com.clubix.repository.mapper.CustomerMapper;
import io.r2dbc.spi.R2dbcNonTransientResourceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerPostgreSQLRepositoryTest {

    @Mock
    private CustomerEntityRepository customerEntityRepository;

    @Mock
    private CustomerMapper mapper;

    private CustomerPostgreSQLRepository repository;

    @BeforeEach
    void setUp() {
        repository = new CustomerPostgreSQLRepository(customerEntityRepository, mapper);
    }

    // -------------------------------------------------------------------------
    // SUCCESS
    // -------------------------------------------------------------------------

    @Test
    void findById_whenCustomerExists_returnsCustomer() {
        CustomerEntity entity = CustomerEntity.builder().id("C1").balance(500.0).build();
        Customer expected     = Customer.builder().id("C1").balance(500.0).build();

        when(customerEntityRepository.findById("C1")).thenReturn(Mono.just(entity));
        when(mapper.toCustomer(entity)).thenReturn(expected);

        StepVerifier.create(repository.findById("C1"))
                .expectNext(expected)
                .verifyComplete();
    }

    // -------------------------------------------------------------------------
    // FAIL — not found
    // -------------------------------------------------------------------------

    @Test
    void findById_whenCustomerDoesNotExist_returnsEmpty() {
        when(customerEntityRepository.findById("UNKNOWN")).thenReturn(Mono.empty());

        StepVerifier.create(repository.findById("UNKNOWN"))
                .verifyComplete();
    }

    // -------------------------------------------------------------------------
    // FAIL — database error
    // -------------------------------------------------------------------------

    @Test
    void findById_whenDatabaseError_propagatesError() {
        R2dbcNonTransientResourceException dbError =
                new R2dbcNonTransientResourceException("Connection refused");

        when(customerEntityRepository.findById("C1")).thenReturn(Mono.error(dbError));

        StepVerifier.create(repository.findById("C1"))
                .expectError(R2dbcNonTransientResourceException.class)
                .verify();
    }
}
