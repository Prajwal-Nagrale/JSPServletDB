package com.prac.registration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice?autoReconnect=true&useSSL=false", "root", "root");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	public static void saveEmployee(Employee employee) {
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement sql = con.prepareStatement("insert into employee(username,password) values (?,?)");
			sql.setString(1, employee.getUsername());
			sql.setString(2, employee.getPassword());
			sql.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<Employee> getEmployees(){
		List<Employee> list=new ArrayList<Employee>();
		try {
			Connection con=DBConnection.getConnection();
			PreparedStatement sql= con.prepareStatement("SELECT * FROM employee");
			ResultSet resultSet= sql.executeQuery();
			while(resultSet.next()) {
				Employee emp=new Employee();
				emp.setId(resultSet.getInt(1));
				emp.setUsername(resultSet.getString(2));
				emp.setPassword(resultSet.getString(3));
				
				list.add(emp);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void deleteEmployee(int id) {
		try {
			Connection con= DBConnection.getConnection();
			PreparedStatement sql= con.prepareStatement("delete from employee where id=?");
			sql.setInt(1, id);
			sql.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
