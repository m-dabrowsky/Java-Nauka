package sample.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sample.dto.EmployeeDto;
import sample.handler.SaveEmployeeHandler;

import java.util.Arrays;
import java.util.List;

public class EmployeeRestClient {

    private static final String EMPLOYEES_URL = "http://localhost:8080/employees";
    private final RestTemplate restTemplate;
    public EmployeeRestClient(){
        restTemplate = new RestTemplate();
    }

    public List<EmployeeDto> getEmployee(){
        ResponseEntity<EmployeeDto[]> employeesResponseEntity = restTemplate.getForEntity(EMPLOYEES_URL, EmployeeDto[].class);
        return Arrays.asList(employeesResponseEntity.getBody());
    }

    public void saveEmployee(EmployeeDto dto, SaveEmployeeHandler handel) {
        ResponseEntity<EmployeeDto> responseEntity = restTemplate.postForEntity(EMPLOYEES_URL, dto, EmployeeDto.class);
        if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
            handel.handle();
        } else {
            // TODO implement
        }
    }

    public EmployeeDto getEmployees(Long idEmployee) {

        String url = EMPLOYEES_URL + "/" + idEmployee;
        ResponseEntity<EmployeeDto> responseEntity = restTemplate.getForEntity(url, EmployeeDto.class);

        if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
            return responseEntity.getBody();
        } else {
            // TODO implement
            throw new RuntimeException("Can't load employee");
        }
    }
}
