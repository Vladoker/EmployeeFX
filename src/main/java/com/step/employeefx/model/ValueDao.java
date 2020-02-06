
package com.step.employeefx.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.ObservableList;

/**
 *
 * @author Vladislav
 */
public class ValueDao {
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/EmpManager";
    private static final String USER = "postgres";
    private static final String PASS = "postgres";
    
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
    
    public void getAll(ObservableList<Employee> list){
        try (Connection connection = connect();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from app.employee")) {
            
            while(result.next()){
                Employee emp = new Employee(result.getString("name"),
                result.getString("surname"), result.getString("gender"),
                result.getString("date"), result.getString("address"),
                result.getDouble("salary"));
                
                list.add(emp);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }     
    } 
    
    public boolean addEmp(Employee emp){
       String sql = "INSERT INTO app.employee (name,surname,date,gender,salary,address) values (?,?,?,?,?,?);";
        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            
            
            statement.setString(1, emp.getName());
            statement.setString(2, emp.getSurname());
            statement.setString(3, emp.getDate());
            statement.setString(4, emp.getSex());
            statement.setDouble(5, emp.getSalary());
            statement.setString(6, emp.getAddress());
            
            int affectedRows = statement.executeUpdate();
            
            if(affectedRows == 0){
                return false;
            }
                                                
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
        return true;     
    }
}
