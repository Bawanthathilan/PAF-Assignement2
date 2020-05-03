package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;


/**
 * Servlet implementation class AppointmentApi
 */
@WebServlet("/AppointmentApi")
public class AppointmentApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	Appointment appObj = new Appointment();
    public AppointmentApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = appObj.insertAppointment(request.getParameter("fullName"), 
				request.getParameter("mobile"), 
				request.getParameter("email"), 
				request.getParameter("nic"), 
				request.getParameter("address"), 
				request.getParameter("date"), 
				request.getParameter("hospName"), 
				request.getParameter("docName"), 
				request.getParameter("msg")); 
 
		response.getWriter().write(output); 
 

		doGet(request, response);
	}
	private static Map getParasMap(HttpServletRequest request) {  
		
		Map<String, String> map = new HashMap<String, String>();  
		
		try  {   
			
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");   
			String queryString = scanner.hasNext() ?        		
			scanner.useDelimiter("\\A").next() : "";   
			scanner.close(); 
	 
			String[] params = queryString.split("&");   
			
			for (String param : params)   { 
				String[] p = param.split("=");    
				map.put(p[0], p[1]);   
				
			}  
			
		}  catch (Exception e)  {  
			
		}  
		
		return map; 
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		 
		 String output = appObj.updateAppointment(paras.get("hidppIDSave").toString(),     
				 		paras.get("fullName").toString(),     
				 		paras.get("mobile").toString(),        
				 		paras.get("email").toString(),        
				 		paras.get("nic").toString(),
				 		paras.get("address").toString(),
				 		paras.get("date").toString(),
				 		paras.get("hospName").toString(),
				 		paras.get("docName").toString(),
		 				paras.get("msg").toString());
		 
		 response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		 
		 String output = appObj.deleteAppointment(paras.get("appID").toString()); 
		 
		 response.getWriter().write(output);
	}

}
