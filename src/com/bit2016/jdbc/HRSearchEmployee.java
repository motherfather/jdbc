package com.bit2016.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class HRSearchEmployee {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "hr", "hr");
			
			stmt = conn.createStatement();
			
			String name = scan.nextLine();
 
			String sql = "select first_name|| ' ' || last_name, email, phone_number, hire_date "
					+        "from employees "
					+      "where first_name || ' ' || last_name like '%" + name + "%'";
			rs = stmt.executeQuery(sql);


			
			while (rs.next()) {
				String name1 = rs.getString(1);
				String email = rs.getString(2);
				String phoneNumber = rs.getString(3);
				String date = rs.getString(4);
				
				System.out.println(name1 + "\t\t" + email + "\t\t" + phoneNumber + "\t\t" + date);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패: " + e);
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				// 3. 자원정리
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
