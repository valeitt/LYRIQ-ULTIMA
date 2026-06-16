package cl.lyriq.auth_service.dto;

import lombok.Data;

@Data
public class LoginDTO {

    private String username;
    private String password;
}