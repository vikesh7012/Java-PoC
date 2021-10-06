package com.perf.poc;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataToFile {

	@SuppressWarnings("removal")
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(
				new BufferedOutputStream(new FileOutputStream("D:\\Assigned-poc\\output.txt")), "UTF-8"));
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poc", "root", "root123");
				Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);) {
//			statement.setFetchSize(Integer.MIN_VALUE);

			try (ResultSet resultSet = statement.executeQuery("SELECT * FROM student")) {
				while (resultSet.next()) {
					writer.append(new Integer(resultSet.getInt(1)).toString()).append("\t")
							.append(resultSet.getString(2)).append("\t").append(resultSet.getString(3)).append("\t")
							.append(new Integer(resultSet.getInt(4)).toString()).append("\t")
							.append(resultSet.getString(5)).append("\t").append(resultSet.getString(6)).append("\t")
							.append(new Long(resultSet.getLong(7)).toString()).append("\t")
							.append(resultSet.getString(8)).append("\t").println();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
