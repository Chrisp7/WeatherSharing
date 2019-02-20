package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao_inter.userLogin;
import database.DBConnection;
import bean.User;

public class userLoginImp implements userLogin{
	
	private User user;
	private Connection con;
	public userLoginImp() {
		con = new DBConnection().getInstance();
	}
/**
 * 用户登录
 * 0为不存在该用户
 * -1为密码不正确
 * 其他数字为通过验证
 */
	public int userCheck(User user){
		String username=user.getUserName();
		String password=user.getPassWord();
		String sqlString = "select passwd,id from user where username = ?;";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result=0;
		try {
			pstmt = (PreparedStatement) con.prepareStatement(sqlString);
			pstmt.setString(1,username);
			rs = pstmt.executeQuery();
			if (rs==null){
				result=0;
			}
			else {
				rs.next();
				String pd=rs.getString("passwd");
				if (!pd.equals(password)) {
					result=-1;
				}else {
					int uid=rs.getInt("id");
					result=uid;
				}
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
		return result;
	
	}
}
