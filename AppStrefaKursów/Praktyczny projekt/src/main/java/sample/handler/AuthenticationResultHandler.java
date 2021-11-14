package sample.handler;

import sample.dto.OperatorAuthenticationResult;

@FunctionalInterface
public interface AuthenticationResultHandler {

    void handle(OperatorAuthenticationResult operatorAuthenticationResult);

}
