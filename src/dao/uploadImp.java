package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;

import dao_inter.uploadInter;
import database.DBConnection;

public class uploadImp implements uploadInter {
	private Connection con;

	public uploadImp() {
		con = new DBConnection().getInstance();
	}

	@Override
	public boolean upload(String path, int uid, String description, int cityId) {
		String insertIntoImgSql = "insert into img(img_url,img_comment)values(?,?)";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt2 = null;
		boolean result = false;
		try {
			pstmt = con.prepareStatement(insertIntoImgSql,
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, path);
			pstmt.setString(2, description);
			pstmt.executeUpdate();
			con.commit();
			rs = pstmt.getGeneratedKeys();
			int id = 0;
			if (rs.next()) {
				id = rs.getInt(1);
			}
			String insertIntoUploadSqlString = "insert into upload (upload_time,upload_img_id,upload_user_id,upload_city_id)values(now(),?,?,?)";

			pstmt2 = con.prepareStatement(insertIntoUploadSqlString);
			
			pstmt2.setInt(1, id);
			pstmt2.setInt(2, uid);
			pstmt2.setInt(3, cityId);

			pstmt2.executeUpdate();
			//一定要记住executeUpdate()后必须要con.commit();
			//commit一下才会把你之前的那条插入语句执行。
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
				pstmt2.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;

	}

}
