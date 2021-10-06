package com.perf.poc;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DepartmentCount {

	@SuppressWarnings("removal")
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(
				new BufferedOutputStream(new FileOutputStream("D:\\Assigned-poc\\departmentCount.txt")), "UTF-8"));
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poc", "root", "root123");
				Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);) {
//			statement.setFetchSize(Integer.MIN_VALUE);

			try (ResultSet resultSet = statement.executeQuery(
					"SELECT departmentId, COUNT(*) as departmentCount FROM student GROUP BY departmentId;")) {
				while (resultSet.next()) {

					String ID = resultSet.getString("departmentId");
					String FirstName = resultSet.getString("departmentCount");

					writer.append(ID).append("\t").append(FirstName).append("\t").println();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Sucessfully fetched to a file");
	}
}
