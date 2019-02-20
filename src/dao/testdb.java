package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;

import database.DBConnection;

public class testdb {
	public static void main(String[] args){
		Connection con=(Connection) new DBConnection().getInstance();
		
		
		String sqlString = "insert into test (expectTime,realTime) values (?,?);";
		PreparedStatement pstmt = null;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(sqlString);
			pstmt.setString(1,"2013-1-1 5:20:4");
			pstmt.setString(2,"2013 1 1");
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {// 关闭资源
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
