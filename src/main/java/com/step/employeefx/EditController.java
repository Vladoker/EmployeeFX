package com.step.employeefx;
import com.step.employeefx.model.Employee;
import com.step.employeefx.interfaces.Icontroller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 *
 * @author Vladislav
 */
public class EditController implements Initializable,Icontroller {

    private ObservableList<Employee> list;
    private ToggleGroup group = new ToggleGroup();
    private int selectedRow;
 
    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldSurname;

    @FXML
    private RadioButton btnMale;

    @FXML
    private RadioButton btnFemale;
 
    @FXML
    private TextField textFieldDate;
 
    @FXML
    private TextField textFieldSalary;

    @FXML
    private TextField textFieldAddress;
  

    @FXML
    public void btnEventEdit(ActionEvent event) {
        
        String name = textFieldName.getText().trim();
        String surname = textFieldSurname.getText().trim();
        String date = textFieldDate.getText().trim();
        double salary = Double.parseDouble(textFieldSalary.getText().trim());
        RadioButton s = (RadioButton)group.getSelectedToggle();
        String gender = s.getText().equalsIgnoreCase("Male") ? "Male" : "Female";      
        String address = textFieldAddress.getText();

        Employee emp = new Employee(name, surname, gender, date, address, salary);
        list.set(this.selectedRow, emp);
        
        closeStage(event);
    }
    
    @Override
    public void updateColection(ObservableList<Employee> obj){
        this.list = obj;
        
        Employee emp = list.get(this.selectedRow);
        
         textFieldName.setText(emp.getName());
         textFieldSurname.setText(emp.getSurname());
         textFieldDate.setText(emp.getDate());
         textFieldSalary.setText(String.valueOf(emp.getSalary()));
         textFieldAddress.setText(emp.getAddress());
         if(emp.getSex().equalsIgnoreCase("Male")){
             btnMale.fire();
         }
         else btnFemale.fire();
         
         
    }
    
    
    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource(); 
        Stage stage  = (Stage)source.getScene().getWindow();
        stage.close();
    }  
    
    public void setSelectedRow(int selectedRow){
        this.selectedRow = selectedRow;
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnMale.setToggleGroup(group);
        btnFemale.setToggleGroup(group);   
    }
    
}
