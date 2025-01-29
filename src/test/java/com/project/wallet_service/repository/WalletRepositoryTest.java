package com.project.wallet_service.repository;

import com.project.wallet_service.model.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static com.project.wallet_service.util.TestUtil.VALID_EMAIL;
import static com.project.wallet_service.util.TestUtil.ZERO;
import static com.project.wallet_service.util.TestUtil.buildValidWalletEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class WalletRepositoryTest {
    @Autowired
    private WalletRepository walletRepository;

    @BeforeEach
    public void setUp() {
        walletRepository.save(buildValidWalletEntity(ZERO));
    }

    @Test
    public void saveWalletTest() {
        Wallet wallet = buildValidWalletEntity(ZERO);
        wallet.setEmail("email@abv.bg");

        walletRepository.save(wallet);

        assertEquals(2, walletRepository.findAll().size());
    }

    @Test
    public void findWalletByEmailTest() {
        Optional<Wallet> actualOptionalWallet = walletRepository.findByEmail(VALID_EMAIL);

        assertTrue(actualOptionalWallet.isPresent());
    }
}
