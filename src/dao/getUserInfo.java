package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Head;
import bean.userInfo;
import database.DBConnection;

public class getUserInfo {
	private Connection con;

	public getUserInfo() {
		con = new DBConnection().getInstance();
	}
	/**
	 * -1:获取数据失败
	 * -2：获取
	 * @param uid
	 * @return
	 */
	public userInfo getUserInformation(int uid){
		userInfo userinfo=new userInfo();
		String sqlString = "select care_city,portrait from user where id = ?;";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = (PreparedStatement) con.prepareStatement(sqlString);
			pstmt.setInt(1,uid);
			rs = pstmt.executeQuery();
			if (!rs.next()){
				userinfo.setResultStatus(-1);
			}
			else {
				userinfo.setCareCity(rs.getString("care_city"));
				userinfo.setPortrait(rs.getString("portrait"));
			}
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
		return userinfo;
	
	}
}
