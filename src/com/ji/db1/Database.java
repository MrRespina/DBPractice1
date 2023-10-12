package com.ji.db1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Database {

	private static String userId = "", userPass = "", connector = "jdbc:mysql://localhost:3306/";
	private static Scanner s = new Scanner(System.in);
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static String sql = "";
	static String plus = "";

	public Database() {
		// TODO Auto-generated constructor stub
	}

	public Database(String connector, String userId, String userPass) {
		super();
		Database.userId = userId;
		Database.userPass = userPass;
		Database.connector = connector;
	}

	public boolean Connection() throws SQLException {

		try {
			conn = DriverManager.getConnection(connector, userId, userPass);
			stmt = conn.createStatement();
			System.out.println(userId + "님 반갑습니다.");
			System.out.println("DB 연결 성공.");
			connector = "jdbc:mysql://localhost:3306/";
			plus = "";
			return false;

		} catch (Exception e) {
			System.out.println("DB 연결 실패.");
			e.printStackTrace();
			connector = "jdbc:mysql://localhost:3306/";
			plus = "";
			return true;
		}

	}

	public static boolean connectDB(String dbName) throws SQLException {

		getName();
		getPass();
		plus = dbName;
		Database db = new Database(connector + plus, userId, userPass);
		boolean b = db.Connection();
		return b;

	}

	public static void getName() {

		System.out.print("ID를 입력해주세요 : ");
		userId = s.nextLine();
		if (userId.equals("") || userId == null) {
			System.out.println("제대로 입력해주세요!");
			getName();
		}
		System.out.println("입력받은 ID : " + userId);

	}

	public static void getPass() {

		System.out.print("PASSWORD를 입력해주세요 : ");
		userPass = s.nextLine();
		if (userPass.equals("") || userPass == null) {
			System.out.println("제대로 입력해주세요!");
			getPass();
		}

	}

	public static void printDBUser() {

		try {

			sql = "SELECT * FROM respina";
			String name, location;
			int age;
			rs = stmt.executeQuery(sql);
			System.out.println("DB Location : " + connector);
			System.out.println("===========");
			while (rs.next()) {
				name = rs.getString(1);
				age = rs.getInt(2);
				location = rs.getString(3);
				System.out.println("USER : " + name);
				System.out.println("AGE : " + age);
				System.out.println("ADDRESS : " + location);
				System.out.println();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void printTableName() {

		try {
			String table;
			sql = "show TABLES";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				table = rs.getString(1);
				System.out.println("TABLE Name : " + table);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void insertUser() {
		String table, name, location;
		int age;
		try {
			System.out.print("테이블 입력 : ");
			table = s.nextLine();
			System.out.print("이름 입력 : ");
			name = s.nextLine();
			System.out.print("나이 입력 : ");
			age = s.nextInt();
			s.nextLine();
			System.out.print("지역 입력 : ");
			location = s.nextLine();

			sql = "INSERT INTO " + table + " VALUES('" + name + "'," + age + ",'" + location + "')";
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			System.out.println("정확한 값을 입력해주세요 !");
			e.printStackTrace();
		}

	}

	public void deleteUser() {
		String table, column, value;
		try {
			System.out.print("삭제할 데이터의 테이블 입력 : ");
			table = s.nextLine();
			System.out.println("삭제할 데이터의 행 입력 : ");
			column = s.nextLine();
			System.out.print("삭제할 데이터의 값 입력 : ");
			value = s.nextLine();

			sql = "DELETE FROM " + table + " WHERE " + column + " = '" + value + "'";
			stmt.executeUpdate(sql);
			System.out.println(table + " 테이블에서 " + column + " 값이 " + value + " 인 column을 삭제했습니다.");

		} catch (Exception e) {
			System.out.println("정확한 값을 입력해주세요 !");
			e.printStackTrace();
		}

	}

}
