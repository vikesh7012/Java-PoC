package com.perf.poc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentDepartmentJoin {

	public static void main(String[] args) {

		try {

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poc", "root", "root123");

			Statement stmt = conn.createStatement();

//			ResultSet rs = stmt.executeQuery("select * from " + "student " + "FULL OUTER JOIN " + "department");
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM student INNER JOIN department ON student.departmentId=department.departmentId;");

			while (rs.next()) {
				String ID = rs.getString("studentId");
				String FirstName = rs.getString("fullName");
				String LastName = rs.getString("lastName");
				String deptId = rs.getString("departmentId");
				String joiningDate = rs.getString("joiningDate");
				String dob = rs.getString("studentDob");
				String mobile = rs.getString("mobileNo");
				String email = rs.getString("email");
				String deptName = rs.getString("departmentName");

				System.out.format("%10s%15s%15s%15s%15s%15s%15s%40s\n", ID, FirstName, LastName, deptId, deptName,
						joiningDate, dob, mobile, email, deptName);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}