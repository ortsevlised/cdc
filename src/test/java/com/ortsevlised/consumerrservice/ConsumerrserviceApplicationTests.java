package com.ortsevlised.consumerrservice;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc //instantiate a mock mvc bean when starting our tests
public class ConsumerrserviceApplicationTests {

    @Rule
    public StubRunnerRule stubRunnerRule = new StubRunnerRule()
            .downloadStub("com.ortsevlised", "providerService", "0.0.1-SNAPSHOT", "stubs")
            .withPort(8080)
            .stubsMode(StubRunnerProperties.StubsMode.LOCAL);

    @Autowired
    private MockMvc mockMvc;

    static String LOW_RISK_CARD = "4242424242424242";
    static String HIGH_RISK_CARD = "400000000000000";

    @Test
    public void shouldBlockTransactionWhenCardRiskIsHigh() throws Exception {

        mockMvc.perform(
                post("/fraud-detector/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"cardNumber\":\"" + HIGH_RISK_CARD + "\"" +
                                "}"))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        "{" +
                                "\"status\":\"BLOCKED\"" +
                                "}")
                );
    }


    @Test
    public void shouldAllowTransactionWhenCardRiskIsLow() throws Exception {
        mockMvc.perform(
                post("/fraud-detector/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"cardNumber\":\"" + LOW_RISK_CARD + "\"" +
                                "}"))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        "{" +
                                "\"status\":\"VALID\"" +
                                "}")
                );
    }
}
