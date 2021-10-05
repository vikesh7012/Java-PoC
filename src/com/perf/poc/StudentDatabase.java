package com.perf.poc;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;

public class StudentDatabase {

	String path = "D:\\POC-DBData\\input.txt";
	static DepartmentTable obj = new DepartmentTable();
	int studentId;
	String fullName;
	String lastName;
	int departmentId;
	String joiningDate;
	String studentDob;
	BigInteger mobileNo;
	String email;

	public void readFile() throws Exception {
		try (Scanner sc = new Scanner(new File(this.path))) {
//			studentId = (Integer) null;
			fullName = "";
			lastName = "";
			joiningDate = "";
			studentDob = "";
			email = "";

			while (sc.hasNextLine()) {
				String line;
				line = sc.nextLine();
				String[] rowData;
				rowData = line.split("\t");
				for (int i = 0; i < rowData.length; i++) {
					System.out.print(rowData[i] + "  ");
				}
				System.out.println();
				uploadRecord(rowData);

			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void uploadRecord(String[] rowData) {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poc", "root", "root123");

			PreparedStatement stmt = con.prepareStatement("INSERT INTO STUDENT values(?,?,?,?,?,?,?,?)");
			stmt.setInt(1, Integer.parseInt(rowData[0]));
			stmt.setString(2, rowData[1]);
			stmt.setString(3, rowData[2]);
//			stmt.setString(4, rowData[3]);
			String deptName = rowData[3];

			int deptId = obj.getDepartmentId(deptName);

			stmt.setInt(4, deptId);

			stmt.setDate(5, Date.valueOf(LocalDate.now()));
			stmt.setDate(6, Date.valueOf(LocalDate.now()));
//			stmt.setDate(5, getDate(rowData[4]));
//			stmt.setDate(6, getDate(rowData[5]));
//			Date dt1 = getDate(rowData[4]);
//			stmt.setDate(5, (java.sql.Date) dt1);
//			stmt.setDate(6, java.sql.Date.valueOf(rowData[5]));

			stmt.setBigDecimal(7, new BigDecimal(rowData[6]));
			stmt.setString(8, rowData[7]);

			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Date getDate(String date) throws Exception {

		Date date1 = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(date);
		return date1;
	}
}
