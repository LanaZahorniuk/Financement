package project.financement.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserAfterCreationDto {

    private UUID userId;

    private UserInfoAfterCreationDto userInfoAfterCreation;
}
