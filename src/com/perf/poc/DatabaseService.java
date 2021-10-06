package com.perf.poc;

public class DatabaseService {

	public static void main(String[] args) {

		StudentDatabase studentObject = new StudentDatabase();
		DepartmentTable departmentObject = new DepartmentTable();
		try {

			// getting department details
			departmentObject.getDepartment();

			// reading the file data to upload into student DB
			studentObject.readFile();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
