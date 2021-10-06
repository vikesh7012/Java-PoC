package com.perf.poc;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class WriteJoinToFile {

	@SuppressWarnings("removal")
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(
				new BufferedOutputStream(new FileOutputStream("D:\\Assigned-poc\\output1.txt")), "UTF-8"));
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poc", "root", "root123");
				Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);) {
//			statement.setFetchSize(Integer.MIN_VALUE);

			try (ResultSet resultSet = statement.executeQuery(
					"SELECT * FROM student INNER JOIN department ON student.departmentId=department.departmentId;")) {
				while (resultSet.next()) {

					String ID = resultSet.getString("studentId");
					String FirstName = resultSet.getString("fullName");
					String LastName = resultSet.getString("lastName");
					String deptId = resultSet.getString("departmentId");
					String joiningDate = resultSet.getString("joiningDate");
					String dob = resultSet.getString("studentDob");
					String mobile = resultSet.getString("mobileNo");
					String email = resultSet.getString("email");
					String deptName = resultSet.getString("departmentName");

					writer.append(ID).append("\t").append(FirstName).append("\t").append(LastName).append("\t")
							.append(deptId).append("\t").append(joiningDate).append("\t").append(dob).append("\t")
							.append(mobile).append("\t").append(email).append("\t").append(deptName).append("\t")
							.println();

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("Sucessfully written the data to file");
	}
}
