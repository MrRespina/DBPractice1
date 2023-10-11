package com.ji.db1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class TestMySql {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection conn;
		Statement stmt = null;
		ResultSet rs = null;
		String sql, name = "";
		String location = "";
		int age = 0;

		try {

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice", "root", "sdj7524");

			System.out.println("DB 연결");

			stmt = conn.createStatement();
			sql = "SELECT * FROM Respina WHERE age>20";

			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				name = rs.getString(1);
				age = rs.getInt(2);
				location = rs.getString(3);
				System.out.println("가져온 값 : " + name + ", " + age + ", "+location);

			}
			
			sql = "INSERT INTO respina VALUES('나',30,'SEOUL')";
			stmt.executeUpdate(sql);

			conn.close();

		} catch (Exception e) {
			System.out.println("오류 코드 : " + e);
			e.printStackTrace();
		}

	}

}
