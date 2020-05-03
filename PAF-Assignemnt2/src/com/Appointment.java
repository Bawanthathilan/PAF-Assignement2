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
					Class.forName("com.mysql.jdbc.Driver");
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
					String query = " insert into appointment (`AppID`,`fullName`,`mobile`,`email`,`nic`, `address`,`date`,`hospName`,`docname`,`msg`)"
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
					output = "{\"status\":\"error\", \"data\":\"Error while inserting doctor.\"}";
					System.err.println(e.getMessage());
				}

				return output;
			}
			
			//=======================================VIEW ALL DOCTORS METHOD=======================================================
			
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
						String nic = rs.getString("NIC");
						String address = rs.getString("Address");
						String mobNo = Integer.toString(rs.getInt("MobileNo"));
						String email = rs.getString("Email");
						String spec = rs.getString("Specialization");
						String hosp = rs.getString("HospitalName");
						String dept = rs.getString("DepartmentName");

						// Add into the html table
						output += "<tr><td><input id='hidDoctorIDUpdate' name='hidDoctorIDUpdate' type='hidden' value='" +docID+ "'>" + docName + "</td>";
						//output += "<tr><td>" + docName + "</td>";
						output += "<td>" + nic + "</td>";
						output += "<td>" + address + "</td>";
						output += "<td>" + mobNo + "</td>";
						output += "<td>" + email + "</td>";
						output += "<td>" + spec + "</td>";
						output += "<td>" + hosp + "</td>";
						output += "<td>" + dept + "</td>";
						
						// buttons    
						/*output += "<td><input name='btnUpdate' type='button\" value='Update\" class='btnUpdate btn btn-secondary'></td>"
								+ "<td><form method=\"post\" action=\"doctors.jsp\">" 
								+ "<input name='btnRemove' type='submit\" value='Remove\" class=\"btn btn-danger\">" 
								+ "<input name=\"hidDoctorIDDelete\" type=\"hidden\" value=\"" + docID + "\">" + "</form></td></tr>"; */

						// buttons     
						output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
								+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='" + docID + "'>" + "</td></tr>"; 
						
					}

					con.close();

					// Complete the html table
					output += "</table>";
				} catch (Exception e) {
					output = "Error while reading the items.";
					System.err.println(e.getMessage());
				}

				return output;

			}
			
			
			
			
			
			//========================================UPDATE DOCTORS METHOD============================================================
			
			public String updateDoctor(String ID, String docName, String nic, String address, String mobNo, String email, String spec, String hosp, String dept ) {
				String output = "";

				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database for updating.";
					}

					// create a prepared statement
					String query = "UPDATE doctors SET DoctorName=?,NIC=?,Address=?,MobileNo=?,Email=?,Specialization=?,HospitalName=?,DepartmentName=?WHERE DoctorID=?";

					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					preparedStmt.setString(1, docName);
					preparedStmt.setString(2, nic);
					preparedStmt.setString(3, address);
					preparedStmt.setInt(4, Integer.parseInt(mobNo));
					preparedStmt.setString(5, email);
					preparedStmt.setString(6, spec);
					preparedStmt.setString(7, hosp);
					preparedStmt.setString(8, dept);
					preparedStmt.setInt(9, Integer.parseInt(ID));

					// execute the statement
					preparedStmt.execute();
					con.close();

					String newDoctor = readDoctors();    
					output = "{\"status\":\"success\", \"data\": \"" + newDoctor + "\"}";
					
				} catch (Exception e) {
					output = "{\"status\":\"error\", \"data\":\"Error while updating doctor.\"}";
					System.err.println(e.getMessage());
				}

				return output;
			}
			
			//========================================DELETE DOCTORS METHOD============================================================
			
			public String deleteDoctor(String appID) {
				String output = "";

				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database for deleting.";
					}

					// create a prepared statement
					String query = "delete from doctors where AppID=?";

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
