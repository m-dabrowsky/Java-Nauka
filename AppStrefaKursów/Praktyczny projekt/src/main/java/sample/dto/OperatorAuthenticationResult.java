package sample.dto;

import lombok.Data;

/**
 * Klasa odpowiedzialna za odbieranie
 */
@Data
public class OperatorAuthenticationResult {

    private Long idOperator;
    private String firstName;
    private String lastName;
    private boolean authenticated;

}
