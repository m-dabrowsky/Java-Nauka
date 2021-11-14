package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.dto.EmployeeDto;
import sample.factory.PopUpFactory;
import sample.rest.EmployeeRestClient;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {
    private PopUpFactory popUpFactory;      // utworzenie pola dla okna PopUp
    private EmployeeRestClient employeeRestClient;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField salaryTextField;

    @FXML
    private BorderPane addEmployeeBorderPane;

    public AddEmployeeController() {
       popUpFactory = new PopUpFactory();
       employeeRestClient = new EmployeeRestClient();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initializeCancelButton();
        initializeSaveButton();

    }

    private void initializeSaveButton() {
        saveButton.setOnAction((x) -> {
            // tworzenie employeedto
            EmployeeDto dto = createEmployeeDto();
            Stage waitingPopUp = popUpFactory.createWaitingPopUp("Connecting to the server...");
            waitingPopUp.show();
            employeeRestClient.saveEmployee(dto, () -> { // co ma sie dziać jak Dto sie zapisze w bazie danych
                waitingPopUp.close();
                Stage infoPopup = popUpFactory.createInfoPopup("Pracownik został zapisany", () -> {
                    getStage().close();
                });
                infoPopup.show();

            });
        });
    }

    private EmployeeDto createEmployeeDto() {
        // pobranie z texfieldów wartości
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String salary = salaryTextField.getText();
        // utworzenie obiektu dto i przypisanie do niego pobranych wartości
        EmployeeDto dto = new EmployeeDto();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setSalary(salary);
        return dto;
    }

    private void initializeCancelButton() {
        cancelButton.setOnAction((x) -> {
            getStage().close();
        });
    }

    private Stage getStage(){
        return (Stage) addEmployeeBorderPane.getScene().getWindow();
    }
}
