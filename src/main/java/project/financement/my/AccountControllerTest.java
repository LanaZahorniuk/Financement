//package project.financement.my;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import project.financement.dto.AccountDto;
//import project.financement.entity.enums.Currency;
//
//import java.math.BigDecimal;
//
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@Sql("/db/schemaTest.sql")
//@Sql("/db/dataTest.sql")
//public class AccountControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
////    @MockBean
////    private UserServiceImpl userServiceImpl;
////
////    @BeforeEach
////    void setUp() {
////        String userIdStr = "12345678-1234-5678-1234-567812345678";
////        UUID userId = UUID.fromString(userIdStr);
////        UserAfterCreationDto userDto = new UserAfterCreationDto();
////        userDto.setUserId(userId);
////
////        Mockito.when(userServiceImpl.getUserById(userId)).thenReturn(userDto);
////
////        try {
////            MvcResult mappingsResult = mockMvc.perform(MockMvcRequestBuilders.get("/actuator/mappings"))
////                    .andReturn();
////            System.out.println(mappingsResult.getResponse().getContentAsString());
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
//
//
////    @Test
////    public void createAccountPositiveTest() throws Exception {
////        String userIdStr = "12345678-1234-5678-1234-567812345678";
////        UUID userId = UUID.fromString(userIdStr);
////        AccountDto dto = new AccountDto("Name", BigDecimal.valueOf(12.0000), Currency.EUR);
////
////        String json = objectMapper.writeValueAsString(dto);
////        System.out.println(json);
////
////        MvcResult result = mockMvc
////                .perform(MockMvcRequestBuilders.post("/account/create-account/" + userId)
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(json))
////                .andReturn();
////
////        Assertions.assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
////
////        String jsonResult = result.getResponse().getContentAsString();
////        System.out.println(jsonResult);
////
////        AccountDto dto2 = objectMapper.readValue(jsonResult, AccountDto.class);
////
////        Assertions.assertNotNull(dto2);
////        Assertions.assertEquals(201, result.getResponse().getStatus());
////        Assertions.assertEquals(dto.getAccountName(), dto2.getAccountName());
////        Assertions.assertEquals(dto.getBalance(), dto2.getBalance());
////        Assertions.assertEquals(dto.getCurrency(), dto2.getCurrency());
////
////
////
//
//    @Test
//    public void createAccountPositiveTest() throws Exception {
//        String userId = "12345678-1234-5678-1234-567812345678";
//
//        AccountDto dto = new AccountDto("Name", BigDecimal.valueOf(12.0000), Currency.EUR);
//
//        String json = objectMapper.writeValueAsString(dto);
//        System.out.println(json);
//
//        MvcResult result = mockMvc
//                .perform(MockMvcRequestBuilders.post("/account/create-account/"+ userId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andReturn();
//
//        Assertions.assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
//
//        String jsonResult = result.getResponse().getContentAsString();
//
//        AccountDto accountDto;
//        accountDto = objectMapper.readValue(jsonResult, AccountDto.class);
//        System.out.println(jsonResult);
//
//        Assertions.assertNotNull(accountDto);
//        Assertions.assertEquals(201, result.getResponse().getStatus());
//        Assertions.assertEquals(dto.getAccountName(), accountDto.getAccountName());
//        Assertions.assertEquals(dto.getBalance(), accountDto.getBalance());
//        Assertions.assertEquals(dto.getCurrency(), accountDto.getCurrency());
//    }
//}
