package com.hullo.testClasses;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*; //where the jdbc is

/**
 * Servlet implementation class TestDBServlet
 */
@WebServlet("/TestDBServlet")
public class TestDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	//setup connection variables
	String user = "admhullo";
	String pass = "admhullo";
	
	String jdbcUrl = "jdbc:mysql://localhost:3306/hullo?useSSL=false";
	String driver = "com.mysql.jdbc.Driver";
		
	//get connection to database
	try{
		//PrintWriter out = response.getWriter();
		System.out.println("connecting to db...");
		Class.forName(driver);
		Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
		System.out.println("success");
		myConn.close();
	}
	catch (Exception exc){
		exc.printStackTrace();
		throw new ServletException(exc);
	}
	
	
	}
		
				
		
	}

