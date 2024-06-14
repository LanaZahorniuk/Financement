package project.financement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import project.financement.dto.UserAfterCreationDto;
import project.financement.dto.UserCreateDto;
import project.financement.dto.UserInfoCreateDto;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/db/schemaTest.sql")
@Sql("/db/dataTest.sql")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithAnonymousUser
    void createUserPositiveTest() throws Exception {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setFirstName("Christian");
        userCreateDto.setLastName("Carl");
        userCreateDto.setDateOfBirth(LocalDate.of(1989, 7, 8));
        UserInfoCreateDto userInfoCreateDto = new UserInfoCreateDto("Chris", "carl.t@example.com", "password123", "+7771167890", "ROLE_FreeUser");
        userCreateDto.setUserInfo(userInfoCreateDto);

        String json = objectMapper.writeValueAsString(userCreateDto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/create-user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();

        Assertions.assertEquals(200, result.getResponse().getStatus());

        String jsonResult = result.getResponse().getContentAsString();
        UserAfterCreationDto userAfterCreationDto = objectMapper.readValue(jsonResult, UserAfterCreationDto.class);
        Assertions.assertNotNull(userAfterCreationDto.getUserId());
        Assertions.assertEquals(userInfoCreateDto.getUsername(), userAfterCreationDto.getUserInfoAfterCreation().getUsername());
    }


    @Test
    @WithAnonymousUser
    void createUserNegativeTest() throws Exception {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setLastName("Karl");
        userCreateDto.setDateOfBirth(LocalDate.of(1989, 7, 8));
        UserInfoCreateDto userInfoCreateDto = new UserInfoCreateDto("Chris", "karl.t@example.com", "password123", "+7771167890", "FreeUser");
        userCreateDto.setUserInfo(userInfoCreateDto);

        String json = objectMapper.writeValueAsString(userCreateDto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/create-user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();

        Assertions.assertEquals(400, result.getResponse().getStatus());

        String responseText = result.getResponse().getContentAsString();

        Assertions.assertTrue(responseText.contains("NULL not allowed for column \"FIRST_NAME\""));

    }


    @Test
    //@WithMockUser(value = "johnsmith", password = "123123", roles = "PremiumUser")
    @WithAnonymousUser
    void getUserByIdPositiveTest() throws Exception {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setFirstName("Christian");
        userCreateDto.setLastName("Carl");
        userCreateDto.setDateOfBirth(LocalDate.of(1989, 7, 8));
        UserInfoCreateDto userInfoCreateDto = new UserInfoCreateDto("Chris", "carl.t@example.com", "password123", "+7771167890", "ROLE_FreeUser");
        userCreateDto.setUserInfo(userInfoCreateDto);

        String json = objectMapper.writeValueAsString(userCreateDto);

        MvcResult createResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/create-user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();

        Assertions.assertEquals(200, createResult.getResponse().getStatus());

        String jsonCreateResult = createResult.getResponse().getContentAsString();
        UserAfterCreationDto createdUser = objectMapper.readValue(jsonCreateResult, UserAfterCreationDto.class);
        UUID testUserId = createdUser.getUserId();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", testUserId)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        Assertions.assertEquals(200, result.getResponse().getStatus());

        String jsonResult = result.getResponse().getContentAsString();
        UserAfterCreationDto userAfterCreationDto = objectMapper.readValue(jsonResult, UserAfterCreationDto.class);
        Assertions.assertNotNull(userAfterCreationDto.getUserId());
        Assertions.assertEquals("Chris", userAfterCreationDto.getUserInfoAfterCreation().getUsername());
    }


    @Test
    @WithMockUser(value = "User", password = "123123", roles = "FreeUser")
    void getUserByIdNegativeTest() throws Exception {
        UUID nonExistentUserId = UUID.randomUUID();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", nonExistentUserId)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        Assertions.assertEquals(404, result.getResponse().getStatus());
        String errorResponse = result.getResponse().getContentAsString();
        Assertions.assertTrue(errorResponse.contains("User not found with ID: " + nonExistentUserId));
    }
}
