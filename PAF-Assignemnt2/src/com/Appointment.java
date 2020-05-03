package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
public class Appointment {
	//=========================DB CONNECTION=========================================================
	
			// A common method to connect to the DB
			private Connection connect() {
				Connection con = null;

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					// Provide the correct details: DBServer/DBName, username, password
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
					System.out.print("Successfully connected from Appointment");
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Connection Failed");
					System.out.println(e);
				}

				return con;
			}	
			
			//=================================INSERT APPOINTMENT METHOD=========================================================
			
			public String insertAppointment(String fullName, String mobile, String email, String nic, String address, String date, String hospName, String docName , String msg ) {
				String output = "";

				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database for inserting.";
					}

					// create a prepared statement
					String query = " insert into appointment (`AppID`,`fullName`,`mobile`,`email`,`nic`, `address`,`date`,`hospName`,`docName`,`msg`)"
							+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

							
					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, fullName);
					preparedStmt.setInt(3, Integer.parseInt(mobile));
					preparedStmt.setString(4, email);
					preparedStmt.setString(5,nic);
					preparedStmt.setString(6, address);
					preparedStmt.setString(7, date);
					preparedStmt.setString(8, hospName);
					preparedStmt.setString(9, docName);
					preparedStmt.setString(10, msg);

					// execute the statement
					preparedStmt.execute();
					con.close();

					String newAppointment = readAppointment();    
					output = "{\"status\":\"success\", \"data\": \"" + newAppointment + "\"}";
					
				} catch (Exception e) {
					output = "{\"status\":\"error\", \"data\":\"Error while inserting appointment.\"}";
					System.err.println(e.getMessage());
				}

				return output;
			}
			
			//=======================================VIEW ALL APPOINTMENT METHOD=======================================================
			
			public String readAppointment() {
				String output = "";

				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database for reading.";
					}

					// Prepare the html table to be displayed
					output = "<table border=\"1\"><tr><th>FullName</th><th>Mobile</th><th>Email</th><th>NIC</th><th>Address</th><th>Date</th><th>HospitalName</th><th>DoctorName</th><th>Message</th></tr>";

					String query = "select * from appointment";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);

					// iterate through the rows in the result set
					while (rs.next()) {
						String appID = Integer.toString(rs.getInt("appID"));
						String fullName = rs.getString("fullName");
						String mobile = Integer.toString(rs.getInt("mobile"));
						String email = rs.getString("email");
						String nic = rs.getString("nic");
						String address = rs.getString("address");
						String date = rs.getString("date");
						String hospName = rs.getString("hospName");
						String docName = rs.getString("docName");
						String msg = rs.getString("msg");

						// Add into the html table
						output += "<tr><td><input id='hidappIDUpdate' name='hidappIDUpdate' type='hidden' value='" +appID+ "'>" + fullName + "</td>";
						//output += "<tr><td>" + docName + "</td>";
						output += "<td>" + mobile + "</td>";
						output += "<td>" + email + "</td>";
						output += "<td>" + nic + "</td>";
						output += "<td>" + address + "</td>";
						output += "<td>" + date + "</td>";
						output += "<td>" + hospName + "</td>";
						output += "<td>" + docName + "</td>";
						output += "<td>" + msg + "</td>";
						
						// buttons    
						/*output += "<td><input name='btnUpdate' type='button\" value='Update\" class='btnUpdate btn btn-secondary'></td>"
								+ "<td><form method=\"post\" action=\"doctors.jsp\">" 
								+ "<input name='btnRemove' type='submit\" value='Remove\" class=\"btn btn-danger\">" 
								+ "<input name=\"hidDoctorIDDelete\" type=\"hidden\" value=\"" + docID + "\">" + "</form></td></tr>"; */

						// buttons     
						output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
								+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='" + appID + "'>" + "</td></tr>"; 
						
					}

					con.close();

					// Complete the html table
					output += "</table>";
				} catch (Exception e) {
					output = "Error while reading the appointments.";
					System.err.println(e.getMessage());
				}

				return output;

			}
			
			
			
			
			
			//========================================UPDATE APPOINTMENT METHOD============================================================
			
			public String updateAppointment(String appID, String fullName, String mobile, String email, String nic, String address, String date, String hospName, String docName , String msg ) {
				String output = "";

				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database for updating.";
					}

					// create a prepared statement
					String query = "UPDATE appointment SET fullName=?,mobile=?,email=?,nic=?,address=?,date=?,hospName=?,docName=?,msg=?WHERE appID=?";

					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					preparedStmt.setString(1, fullName);
					preparedStmt.setInt(2,Integer.parseInt (mobile));
					preparedStmt.setString(3, email);
					preparedStmt.setString(4,nic);
					preparedStmt.setString(5, address);
					preparedStmt.setString(6, date);
					preparedStmt.setString(7, hospName);
					preparedStmt.setString(8, docName);
					preparedStmt.setString(9, msg);
					preparedStmt.setInt(10, Integer.parseInt(appID));

					// execute the statement
					preparedStmt.execute();
					con.close();

					String newAppointment = readAppointment();    
					output = "{\"status\":\"success\", \"data\": \"" + newAppointment + "\"}";
					
				} catch (Exception e) {
					output = "{\"status\":\"error\", \"data\":\"Error while updating appointment.\"}";
					System.err.println(e.getMessage());
				}

				return output;
			}
			
			//========================================DELETE APPOINTMENT METHOD============================================================
			
			public String deleteAppointment(String appID) {
				String output = "";

				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database for deleting.";
					}

					// create a prepared statement
					String query = "delete from appointment where AppID=?";

					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					preparedStmt.setInt(1, Integer.parseInt(appID));

					// execute the statement
					preparedStmt.execute();
					con.close();

					String newAppointment = readAppointment();    
					output = "{\"status\":\"success\", \"data\": \"" + newAppointment + "\"}";
					
				} catch (Exception e) {
					output = "{\"status\":\"error\", \"data\":\"Error while updating appointment.\"}";
					System.err.println(e.getMessage());
				}

				return output;
			}
			
			
}
