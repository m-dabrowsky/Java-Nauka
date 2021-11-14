package sample.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.dto.EmployeeDto;
import sample.factory.PopUpFactory;
import sample.handler.EmployeeLoadedHandler;
import sample.rest.EmployeeRestClient;

import java.net.URL;
import java.util.ResourceBundle;

public class EditEmployeeController implements Initializable {
    
    private final EmployeeRestClient employeeRestClient;
    private final PopUpFactory popUpFactory;

    @FXML
    private BorderPane editEmployeeBorderPane;

    @FXML
    private Button editButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField salaryTextField;

    private Long idEmployee;

    public EditEmployeeController() {
        employeeRestClient = new EmployeeRestClient();
        popUpFactory = new PopUpFactory();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeCancelButton();
        initializeEditButton();
    }

    private void initializeEditButton() {
        editButton.setOnAction((x) -> {
            Stage waitingPopUp = popUpFactory.createWaitingPopUp("Connecting to the server... ");
            waitingPopUp.show();
            Thread thread = new Thread(() -> {
                EmployeeDto dto = createEmployeeDto();
                employeeRestClient.saveEmployee(dto, () -> {      // co ma sie stac jak pracownik sie zapisze
                    Platform.runLater(() -> {
                        waitingPopUp.close();
                        Stage infoPopup = popUpFactory.createInfoPopup("Employee has been updated", () -> {
                            getStage().close();
                        });
                        infoPopup.show();
                    });
                });
            });
            thread.start();
        });
    }

    private EmployeeDto createEmployeeDto() {
        // pobranie z texfieldów wartości
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String salary = salaryTextField.getText();
        // utworzenie obiektu dto i przypisanie do niego pobranych wartości
        EmployeeDto dto = new EmployeeDto();
        dto.setIdEmployee(idEmployee);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setSalary(salary);
        return dto;
    }

    public void loadEmployeeData(Long idEmployee, EmployeeLoadedHandler handler){
        Thread thread = new Thread(() -> {
            EmployeeDto dto = employeeRestClient.getEmployees(idEmployee);
            Platform.runLater(() -> {
                this.idEmployee = idEmployee;
                firstNameTextField.setText(dto.getFirstName());
                lastNameTextField.setText(dto.getLastName());
                salaryTextField.setText(dto.getSalary());
                handler.handle();
            });
        });
        thread.start();
    }

    private void initializeCancelButton() {
        cancelButton.setOnAction((x) -> {
            getStage().close();
        });
    }

    private Stage getStage(){
        return (Stage) editEmployeeBorderPane.getScene().getWindow();
    }
}
