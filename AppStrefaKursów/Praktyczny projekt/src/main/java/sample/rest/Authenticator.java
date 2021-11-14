package sample.rest;

import sample.dto.OperatorCredentialDto;
import sample.handler.AuthenticationResultHandler;

public interface Authenticator {

    void authenticate(OperatorCredentialDto operatorCredentialDto, AuthenticationResultHandler authenticationResultHandler);

}
