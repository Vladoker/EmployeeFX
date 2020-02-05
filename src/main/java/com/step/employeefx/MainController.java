package com.step.employeefx;
import com.step.employeefx.model.Employee;
import com.step.employeefx.interfaces.Icontroller;
import com.step.employeefx.model.ValueDao;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController implements Initializable {
    
     private final ObservableList<Employee> mainList = FXCollections.observableArrayList();
     private final ValueDao dao = new ValueDao();
     
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
    void openModalAdd() throws IOException {
       createModal("/fxml/AddScene.fxml", "Add employee", new AddController());      
    }
    @FXML
    void openModalEdit() throws IOException {
        
        int selectedRow = this.table.getSelectionModel().getSelectedIndex();
        if(selectedRow != -1){
        EditController editController = new EditController();
        editController.setSelectedRow(selectedRow);
        createModal("/fxml/EditScene.fxml", "Edit employee", editController);  
        }
    }
    @FXML
    void buttonEventDelete() throws IOException {
        int selectedRow = this.table.getSelectionModel().getSelectedIndex();
        
        if(selectedRow != -1){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Предупреждения");
            alert.setHeaderText("Вы уверены что хотите удалить ?");
//            alert.setHeaderText("Are you sure you want to delete this user?");
            
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) {
                this.mainList.remove(selectedRow);
            }
            
        }
    }
    
    
    private void createModal(String srcFxml, String nameModal, Icontroller controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(srcFxml));
        Parent parent = fxmlLoader.load();
        Icontroller contrl = (Icontroller)fxmlLoader.getController();
        contrl.updateColection(mainList);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle(nameModal);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dao.getAll(mainList);
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tabeSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        tableGender.setCellValueFactory(new PropertyValueFactory<>("sex"));
        tableDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        tableAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        table.setItems(mainList);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);       
    }    
}
