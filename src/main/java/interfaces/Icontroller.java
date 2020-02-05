/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import com.step.employeefx.model.Employee;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

/**
 *
 * @author Vladislav
 */
public interface Icontroller {
    
    public void updateColection(ObservableList<Employee> obj);
    
}
