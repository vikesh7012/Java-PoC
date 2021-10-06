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

	public void getDepartment() {
		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poc", "root", "root123");
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM DEPARTMENT");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getInt(1), rs.getString(2));
			}

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
