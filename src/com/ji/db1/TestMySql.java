package com.ji.db1;

import java.util.Scanner;
import java.sql.SQLException;

public class TestMySql {

	static Scanner s = new Scanner(System.in);
	static Database db = new Database();
	static boolean b=true;

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		while (true) {

			if (b == true) {

				startDB(b);

			}else if (b == false) {
				
				startMenu();
				
			}

			

			// DB Connection conn
			// getConnection(jdbc:mysql://user:port/DB_name,DB_id,DB_password)
			// conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice",
			// "root", "sdj7524");

			// System.out.println("DB 연결");

			// conn.createStatement() = use Query
			// stmt = conn.createStatement();

			// SELECT 'column' FROM 'DB' WHERE VALUE (=|>|>=|...) 'VALUE'
			// sql = "SELECT * FROM Respina WHERE age>20";

			// executeQuery() = SELECT , executeUpdate() = DELETE,UPDATE,INSERT ~
			// rs = stmt.executeQuery(sql);

			// while (rs.next()) {

			// name = rs.getString(1);
			// age = rs.getInt(2);
			// location = rs.getString(3);
			// System.out.println("가져온 값 : " + name + ", " + age + ", "+location);

			// }

			// INSERT INTO 'DB' VALUES ('VALUE1','VALUE2'~)
			// sql = "INSERT INTO respina VALUES('나',30,'SEOUL')";

			// DELETE FROM 'DB' WHERE VALUE = 'VALUE'
			// sql = "DELETE FROM respina WHERE name='나'";

			// stmt.executeUpdate(sql);
		}

	}

	public static void startDB(boolean b) throws SQLException {

		String dbname = "";

		try {
			if (b == true) {
				System.out.println("=====================");
				System.out.print("연결할 DB의 이름 : ");
				dbname = s.nextLine();
				if (dbname.equals("") || dbname == null) {
					System.out.println("db명을 입력해주세요!");
					startDB(b);
				} else {
					b = db.connectDB(dbname);
				}
			}
		} catch (Exception e) {
			System.out.println("정확한 DB 명을 입력해주세요.");
			e.printStackTrace();
		}

	}

	public static void startMenu() throws SQLException {

		try {
			System.out.println("=====================");
			System.out.println("1. TABLE 이름 보기");
			System.out.println("2. 전체 테이블 보기");
			System.out.println("3. 테이블에 정보 입력");
			System.out.println("4. 테이블에서 정보 삭제");
			System.out.print("번호 입력 : ");
			int cate = s.nextInt();
			if (cate < 1 || cate > 6) {
				System.out.println("정확한 숫자를 입력해주세요!");
				startMenu();
			}

			s.nextLine();
			switch (cate) {

			case 1:
				db.printTableName();
				break;
			case 2:
				db.printDBUser();
				break;
			case 3:
				db.insertUser();
				break;
			case 4:
				db.deleteUser();
				break;

			}

		} catch (Exception e) {
			System.out.println("숫자를 입력해주세요!");
			s.nextLine();
			startMenu();

		}

	}

}
