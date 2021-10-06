package com.perf.poc;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class StudentDatabase {

	String path = "D:\\Assigned-poc\\POC-Data\\input.txt";
	File file = new File(this.path);

	static Connection con;
	static PreparedStatement stmt;
	static DepartmentTable obj = new DepartmentTable();

	public void readFile() throws Exception {
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNextLine()) {
				String line;
				line = sc.nextLine();
				String[] rowData;
				rowData = line.split("\t");
				int rowNo = Integer.parseInt(rowData[0]);
				boolean b = getBoolean(rowNo);
				if (b == true) {
					updateRecord(rowData);
				} else {
					insertRecord(rowData);
				}
			}

		} catch (Exception e) {
			System.out.println("Handled the data");
		}
		System.out.println("Records inserted and updated Sucessfully");
	}

	public static void insertRecord(String[] rowData) {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poc", "root", "root123");
			stmt = con.prepareStatement("INSERT INTO STUDENT values(?,?,?,?,?,?,?,?)");
			stmt.setInt(1, Integer.parseInt(rowData[0]));
			stmt.setString(2, rowData[1]);
			stmt.setString(3, rowData[2]);
			String deptName = rowData[3];
			int deptId = obj.getDepartmentId(deptName);
			stmt.setInt(4, deptId);
			stmt.setDate(5, java.sql.Date.valueOf(rowData[4]));
			stmt.setDate(6, java.sql.Date.valueOf(rowData[5]));
			stmt.setBigDecimal(7, new BigDecimal(rowData[6]));
			stmt.setString(8, rowData[7]);
			int i = stmt.executeUpdate();
			System.out.println(rowData[0] + " no record inserted");

			con.close();

		} catch (Exception e) {
			System.out.println("error2");
			e.printStackTrace();
		}
	}

	public static void updateRecord(String[] rowData) {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poc", "root", "root123");
			stmt = con.prepareStatement(
					"UPDATE student SET  fullName = ?, lastName = ?, departmentId = ?, joiningDate = ?, studentDob = ?, mobileNo = ?, email = ? WHERE (studentId = ?)");
			stmt.setString(1, rowData[1]);
			stmt.setString(2, rowData[2]);
			String deptName = rowData[3];
			int deptId = obj.getDepartmentId(deptName);
			stmt.setInt(3, deptId);
			stmt.setDate(4, java.sql.Date.valueOf(rowData[4]));
			stmt.setDate(5, java.sql.Date.valueOf(rowData[5]));
			stmt.setBigDecimal(6, new BigDecimal(rowData[6]));
			stmt.setString(7, rowData[7]);
			stmt.setInt(8, Integer.parseInt(rowData[0]));
			int i = stmt.executeUpdate();
			System.out.println(rowData[0] + " no record updated");

			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static boolean getBoolean(int no) {
		try {
			if (no == 3) {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poc", "root", "root123");
				stmt = con.prepareStatement("SELECT EXISTS (SELECT 1 FROM student WHERE studentId = ?)");
				stmt.setInt(1, 3);
				ResultSet rs = stmt.executeQuery();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("error3");
			e.printStackTrace();
		}
		return false;

	}
}
