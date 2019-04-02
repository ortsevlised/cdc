package com.ortsevlised.consumerrservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldBlockTransactionWhenCardRiskIsHigh() throws Exception {
        mockMvc.perform(
                post("/fraud-detector")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        "{" +
                                "\"status\":\"BLOCKED\"" +
                                "}")
                );
    }

}
