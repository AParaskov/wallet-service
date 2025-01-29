package com.project.wallet_service.repository;

import com.project.wallet_service.model.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static com.project.wallet_service.util.TestUtil.buildValidOperationEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OperationRepositoryTest {
    @Autowired
    private OperationRepository operationRepository;

    @BeforeEach
    public void setUp() {
        operationRepository.save(buildValidOperationEntity());
    }

    @Test
    public void saveOperationTest() {
        Operation operation = buildValidOperationEntity();

        operationRepository.save(operation);

        assertEquals(2, operationRepository.findAll().size());
    }
}
