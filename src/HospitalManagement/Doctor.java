package HospitalManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {


    private Connection connection;



    public Doctor(Connection connection){
        this.connection = connection;

    }


    public void viewDoctor(){
        String query = "select * from patients";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctors: ");
            System.out.println("+------------+---------------------+--------+---------+");
            System.out.println("| Doctors Id | Name                | specialization   |");
            System.out.println("+------------+---------------------+--------+---------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name =resultSet.getString("name");
                String speicalization = resultSet.getString("specialization");
                System.out.printf(" | %-10s| %-20s| %-18s|\n", id, name, speicalization);
                System.out.println("+------------+---------------------+--------+---------+");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getDoctorsById(int id){
        String query = "SELECT * FROM doctors WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){

            e.printStackTrace();
        }
        return  false;
    }

}
