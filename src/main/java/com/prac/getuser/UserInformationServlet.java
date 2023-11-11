package com.prac.getuser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prac.registration.DBConnection;
import com.prac.registration.Employee;

/**
 * Servlet implementation class UserInformationServlet
 */
@WebServlet("/UserInformationServlet")
public class UserInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInformationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Employee> list= DBConnection.getEmployees();
		System.out.println(list);
		PrintWriter out=response.getWriter();
		out.println("<table border=1 width='50%'>");
		out.println("<tr><th>ID</th><th>UserName</th><th>Password</th></tr>");
		
		for(Employee emp:list) {
			out.println("<tr><td>"+emp.getId()+"</td><td>"+emp.getUsername()+"</td><td>"+emp.getPassword()+"</td><td><a href='DeleteEmployee?id="+emp.getId()+"'>Delete Employee</a></td></tr>");
		}
		
		out.println("</table>");
	}


}
