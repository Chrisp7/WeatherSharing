package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;

import dao_inter.registerInter;
import database.DBConnection;

public class registerImp implements registerInter {
	private Connection con;

	public registerImp() {
		con = new DBConnection().getInstance();
	}
	@Override
	public boolean addUser(String userName, String passWord, String caredCity,
			String portraitPath) {
		String insertIntoImgSql = "insert into user(username,passwd,care_city,portrait)values(?,?,?,?)";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			pstmt = con.prepareStatement(insertIntoImgSql);
			pstmt.setString(1, userName);
			pstmt.setString(2, passWord);
			pstmt.setString(3, caredCity);
			pstmt.setString(4, portraitPath);
			pstmt.executeUpdate();
			con.commit();
			result=true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {// 关闭资源
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
