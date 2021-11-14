package sample.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Klasa odpowiedzialna za wysyłanie loginu i hasła
 */

@Data
public class OperatorCredentialDto {

    private String login;
    private String password;

}
