package com.project.wallet_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.wallet_service.service.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.project.wallet_service.util.TestUtil.CREATE_WALLET_URL;
import static com.project.wallet_service.util.TestUtil.INCORRECT_URL;
import static com.project.wallet_service.util.TestUtil.OPERATION_URL;
import static com.project.wallet_service.util.TestUtil.VIEW_BALANCE_URL;
import static com.project.wallet_service.util.TestUtil.buildInvalidCreateWalletRequest;
import static com.project.wallet_service.util.TestUtil.buildInvalidOperationRequest;
import static com.project.wallet_service.util.TestUtil.buildValidCreateWalletRequest;
import static com.project.wallet_service.util.TestUtil.buildValidDepositOperationRequest;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = WalletController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class WalletControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private WalletService walletService;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void createWalletReturns201Created() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_WALLET_URL)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(buildValidCreateWalletRequest())))
                .andExpect(status().isCreated());
    }

    @Test
    public void createWalletReturns400BadRequestWithInvalidParameters() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_WALLET_URL)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(buildInvalidCreateWalletRequest())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createWalletReturn404NotFoundWithIncorrectUrl() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(INCORRECT_URL)
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(objectMapper.writeValueAsString(buildValidCreateWalletRequest())))
                .andExpect(status().isNotFound());
    }

    @Test
    public void viewBalanceReturns400BadRequestWithMissingParameter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(VIEW_BALANCE_URL)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void viewBalanceReturns404NotFoundWithIncorrectUrl() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(INCORRECT_URL)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void operationReturns200Ok() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(OPERATION_URL)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(buildValidDepositOperationRequest())))
                .andExpect(status().isOk());
    }

    @Test
    public void operationReturns400BadRequestWithInvalidParameters() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(OPERATION_URL)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(buildInvalidOperationRequest())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void operationReturns404NotFoundWithIncorrectUrl() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(INCORRECT_URL)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(buildInvalidOperationRequest())))
                .andExpect(status().isNotFound());
    }
}
