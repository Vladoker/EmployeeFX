package com.step.employeefx;
import com.step.employeefx.model.Employee;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController implements Initializable {
    
     private final ObservableList<Employee> mainList = FXCollections.observableArrayList();
     
    @FXML
    private TableView<Employee> table;
        
    @FXML
    private TableColumn tableName;

    @FXML
    private TableColumn tabeSurname;

    @FXML
    private TableColumn tableDate;

    @FXML
    private TableColumn tableGender;

    @FXML
    private TableColumn tableSalary;

    @FXML
    private TableColumn tableAddress;


    
     @FXML
    void OpenAddDialog(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AddScene.fxml"));
        Parent parent = fxmlLoader.load();
        AddController addController = (AddController)fxmlLoader.getController();
        addController.updateColection(mainList);
        
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("Add employee");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tabeSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        tableGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tableDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        tableAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        table.setItems(mainList);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
    }    
}
