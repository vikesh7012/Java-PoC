package com.perf.poc;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class StudentDelete {

	public static void main(String[] args) throws Exception {

		String path = "D:\\Assigned-poc\\POC-Data\\deletestudent.txt";
		File file = new File(path);
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNextLine()) {
				String line;
				line = sc.nextLine();
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poc", "root", "root123");
					PreparedStatement stmt = con.prepareStatement("DELETE FROM student WHERE studentId=? ");
					stmt.setInt(1, Integer.parseInt(line));
					stmt.executeUpdate();

					System.out.println("Records with ID: " + line + " deleted Sucessfully");

				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
