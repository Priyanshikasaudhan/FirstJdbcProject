package com.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//create table login(userid varchar(100),password varchar(20);
//insert into login values("Prishu","1243454");
/**
 * Servlet implementation class LoginServelt
 */
@WebServlet("/LoginServelt")
public class LoginServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 String userid = request.getParameter("userid");
		 String password = request.getParameter("password");
		 //JDBC connection
		 try {
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Login","root","prishu");
		 Statement st = con.createStatement();
		 String query = "select * from login where userid= '"+userid+"' and password = '"+password+"'";
		 ResultSet rs = st.executeQuery(query);
		 if(rs.next()) {
			 out.print("<h1>"+userid+": Welcome to Home page</h1></br>");
			 out.print("<h1>Login successfully!</h1></br>");
		 }else {
			 //the user id and password nor available in DB
			 out.print("<h1>"+userid+": please enter correct userId and Password</h1></br>");
			 out.print("<h1>Login failed!</h1></br>");
		 }
		 rs.close();
		 st.close();
		 con.close();
		 }
		 catch(ClassNotFoundException e) {
			 out.print("<h1>Login failed! because of server exception</h1></br>");
			 e.printStackTrace();
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			 
			 out.print("<h1>Login failed! because of server exception</h1></br>");
			e.printStackTrace();
		}
		 
		  
		 }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
