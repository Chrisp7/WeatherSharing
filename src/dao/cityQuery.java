package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.User;
import database.DBConnection;

public class cityQuery {
	private Connection con;

	public cityQuery() {
		con = new DBConnection().getInstance();
	}
	public int findCityId(String cityName){
		
		String sqlString = "select id from city where city_name = ?;";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cityId=0;
		try {
			pstmt = (PreparedStatement) con.prepareStatement(sqlString);
			pstmt.setString(1,cityName);
			rs = pstmt.executeQuery();
			if (!rs.next()){
				cityId=-1;
			}
			else {
				cityId=rs.getInt(1);
				System.out.println("cityid--->"+cityId);
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
		return cityId;
	
	}
}
