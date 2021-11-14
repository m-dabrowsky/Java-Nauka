package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.dto.EmployeeDto;
import sample.factory.PopUpFactory;
import sample.rest.EmployeeRestClient;
import sample.table.EmployeeTableModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EmployeeController implements Initializable {

    private final EmployeeRestClient employeeRestClient;
    private ObservableList<EmployeeTableModel> data;
    private PopUpFactory popUpFactory;      // utworzenie pola dla okna PopUp

    @FXML
    private TableView<EmployeeTableModel> employeeTableView;

    @FXML
    private Button addButton;

    @FXML
    private Button viewButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button refreshButton;


    public EmployeeController(){
        data = FXCollections.observableArrayList();
        employeeRestClient = new EmployeeRestClient();
        popUpFactory = new PopUpFactory();
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeAddEmployeeButton();
        initializeViewEmployeeButton();
        initializeEditEmployeeButton();
        initializeTableView();
        initializeRefreshButton();
    }

    private void initializeEditEmployeeButton() {
        editButton.setOnAction(x -> {
            EmployeeTableModel selectedEmployee = employeeTableView.getSelectionModel().getSelectedItem();
            if(selectedEmployee != null){

                try {
                    Stage waitingPopUp = popUpFactory.createWaitingPopUp("Loading employee data..");
                    waitingPopUp.show();
                    Stage editEmployeeStage = creatEditEmployeeStage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/edit-employee.fxml"));
                    Scene scene = new Scene(loader.load(), 500, 400);
                    editEmployeeStage.setScene(scene);
                    EditEmployeeController controller = loader.getController();
                    controller.loadEmployeeData(selectedEmployee.getIdEmployee(), ()-> {
                        waitingPopUp.close();
                        editEmployeeStage.show();
                    });
                } catch (IOException e) {
                    throw new RuntimeException("Can't load fxml file");
                }
            }
        });
    }

    private Stage creatEditEmployeeStage() {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        return stage;
    }

    private void initializeViewEmployeeButton() {
        viewButton.setOnAction(x -> {
            EmployeeTableModel employee = employeeTableView.getSelectionModel().getSelectedItem();
            if(employee == null){
                return;
            }else {

                try {
                    Stage waitingPopUp = popUpFactory.createWaitingPopUp("Loading employee data..");
                    waitingPopUp.show();
                    Stage viewEmployeeStage = new Stage();
                    viewEmployeeStage.initStyle(StageStyle.UNDECORATED);
                    viewEmployeeStage.initModality(Modality.APPLICATION_MODAL);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/view-employee.fxml"));
                    Scene scene = new Scene((BorderPane)loader.load(), 500, 400);
                    viewEmployeeStage.setScene(scene);
                    ViewEmployeeController controller = loader.<ViewEmployeeController>getController();                 // wczytanie kontrolera
                    controller.loadEmployeeData(employee.getIdEmployee(), () -> {
                        waitingPopUp.close();
                        viewEmployeeStage.show();
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void initializeRefreshButton() {
        refreshButton.setOnAction(x -> {
            loadEmployeeDate();
        });

    }

    // określenie działania przycisku ADD
    private void initializeAddEmployeeButton() {
        addButton.setOnAction((x) -> {
            Stage addEmployeeStage = new Stage();
            addEmployeeStage.initStyle(StageStyle.UNDECORATED);
            addEmployeeStage.initModality(Modality.APPLICATION_MODAL);
            try {
                Parent addEmployeeParent = FXMLLoader.load(getClass().getResource("/fxml/add-employee.fxml"));
                Scene scene = new Scene(addEmployeeParent, 500,400);
                addEmployeeStage.setScene(scene);
                addEmployeeStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }

    private void initializeTableView() {
        employeeTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);  // dodawanie kolejnych kolumn sprawi że będą zajmowały równa szerokość w tabeli

        //Utworzenie kolumny dla Employee (imie, nazwisko itp.)
        TableColumn firstNameColumn = new TableColumn("First Name");
        TableColumn lastNameColumn = new TableColumn("Last Name");
        TableColumn salaryColumn = new TableColumn("Salary");

        // minimalna szerokość kolumny
        firstNameColumn.setMinWidth(100);
        lastNameColumn.setMinWidth(100);
        salaryColumn.setMinWidth(100);

        // Jakie wartości kolumna będzie przechowywała
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<EmployeeTableModel, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<EmployeeTableModel, String>("lastName"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<EmployeeTableModel, String>("salary"));


        //
        employeeTableView.getColumns().addAll(firstNameColumn,lastNameColumn,salaryColumn);


        loadEmployeeDate();
        employeeTableView.setItems(data);
    }

    private void loadEmployeeDate() {
        Thread thread = new Thread(() -> {
            List<EmployeeDto> employee = employeeRestClient.getEmployee();
            data.clear();
            data.addAll(employee.stream().map(EmployeeTableModel::of).collect(Collectors.toList()));

        });
        thread.start();
    }


}
