package sample.dto;

import lombok.Data;

/**
 * DTO - Data Transfer Object - to obiekt, za pośrednictwem, którego udostępnia się jakieś dane zwykle na zewnątrz aplikacji - suche dane.
 * To ma głównie zastosowanie w aplikacjach, które posiadają interfejs do którego można podłączyć się zdalnie (np. REST API)
 */

@Data
public class EmployeeDto {

    private Long idEmployee;
    private String firstName;
    private String lastName;
    private String salary;

}
