package sample.rest;

import org.springframework.web.client.RestTemplate;
import sample.dto.OperatorAuthenticationResult;
import sample.dto.OperatorCredentialDto;
import sample.handler.AuthenticationResultHandler;

public class AuthenticatorImpl implements Authenticator{

    private static final String AUTHENTICATION_URL = "http://localhost:8080/verify_operator_credential";
    private final RestTemplate restTemplate;                                                                // do wykonywania żądań
    public AuthenticatorImpl(){
        restTemplate = new RestTemplate();
    }

    @Override
    public void authenticate(OperatorCredentialDto operatorCredentialDto, AuthenticationResultHandler authenticationResultHandler) {
        Runnable authenticationTask = () -> {processAuthentication(operatorCredentialDto, authenticationResultHandler);};
        Thread authenticationThread = new Thread(authenticationTask);
        authenticationThread.setDaemon(true);
        authenticationThread.start();
    }

    // Proces uwierzytelniania - wysyłanie informacji do backendu i odbieranie
    private void processAuthentication(OperatorCredentialDto operatorCredentialDto, AuthenticationResultHandler authenticationResultHandler){
     ///   ResponseEntity<OperatorAuthenticationResult> responseEntity = restTemplate.postForEntity(AUTHENTICATION_URL, operatorCredentialDto, OperatorAuthenticationResult.class);
        OperatorAuthenticationResult dto = new OperatorAuthenticationResult();
        dto.setAuthenticated(true);
        dto.setFirstName("Szymon");
        dto.setLastName("Sołtys");
        dto.setIdOperator(1L);

        authenticationResultHandler.handle(dto);

    }








}

