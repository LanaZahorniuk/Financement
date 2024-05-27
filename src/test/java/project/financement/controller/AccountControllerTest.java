package project.financement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springdoc.core.converters.ResponseSupportConverter;
import org.springdoc.core.service.GenericResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import project.financement.dto.AccountDto;
import project.financement.entity.enums.Currency;

import java.math.BigDecimal;


@SpringBootTest
@AutoConfigureMockMvc
@Sql("/db/schemaTest.sql")
@Sql("/db/dataTest.sql")
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private GenericResponseService responseBuilder;
    @Autowired
    private ResponseSupportConverter responseSupportConverter;

    @Test
    void createAccountPositiveTest() throws Exception {

        //UUID userId = UUID.randomUUID();
//        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16]);
//        byteBuffer.putLong(userId.getMostSignificantBits());
//        byteBuffer.putLong(userId.getLeastSignificantBits());
//        byte[] userIdBytes = byteBuffer.array();
        String userIdHex = "12345678123456781234567812345678";


        AccountDto dto = new AccountDto("Name", BigDecimal.valueOf(12.0000), Currency.EUR);

        String json = objectMapper.writeValueAsString(dto);
        System.out.println(json);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/account/create-account/" + userIdHex)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();


        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();
        System.out.println(content);

        Assertions.assertEquals(HttpStatus.CREATED.value(), status);

        AccountDto responseDto = objectMapper.readValue(content, AccountDto.class);
        Assertions.assertEquals(dto.getAccountName(), responseDto.getAccountName());
        Assertions.assertEquals(dto.getBalance(), responseDto.getBalance());
        Assertions.assertEquals(dto.getCurrency(), responseDto.getCurrency());}

}
//        AccountDto accountDto = objectMapper.readValue(json, AccountDto.class);
//        System.out.println(jsonResult);
//
//        Assertions.assertEquals(200, result.getResponse().getStatus());
//        Assertions.assertEquals(accountDto, objectMapper.readValue(jsonResult, AccountDto.class));
//int status = result.getResponse().getStatus();
//Assertions.assertEquals(200, status);

//        Assertions.assertEquals(dto.getAccountName(), responseDto.getAccountName());
//        Assertions.assertEquals(dto.getBalance(), responseDto.getBalance());
//        Assertions.assertEquals(dto.getCurrency(), responseDto.getCurrency());
