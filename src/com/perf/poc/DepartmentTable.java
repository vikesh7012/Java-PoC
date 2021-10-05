package com.perf.poc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DepartmentTable {

	static Map<Integer, String> map = new HashMap<Integer, String>();

//	public static void main(String[] args) {
//		getDepartment();
//
//	}

	public void getDepartment() {
		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poc", "root", "root123");

			PreparedStatement stmt = con.prepareStatement("SELECT * FROM DEPARTMENT");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				// System.out.println(rs.getInt(1) + " " + rs.getString(2));
				map.put(rs.getInt(1), rs.getString(2));

			}

//			for (Map.Entry m : map.entrySet()) {
//				System.out.println(m.getKey() + " " + m.getValue());
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getDepartmentId(String deptName) {
		for (Entry<Integer, String> entry : map.entrySet()) {
			if (entry.getValue().equalsIgnoreCase(deptName)) {
				return entry.getKey();
			}
		}
		return 0;

	}
}
