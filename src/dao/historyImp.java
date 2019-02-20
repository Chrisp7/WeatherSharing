package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Head;
import dao_inter.historyInter;
import database.DBConnection;

public class historyImp implements historyInter {
	private Connection con;

	public historyImp() {
		con = new DBConnection().getInstance();
	}

	@Override
	public List<Object> getHistoryPhoto(int uid) {
		String findUserImgId = "select img_url from upload join img on upload.upload_img_id=img.id where upload.upload_user_id=? ORDER BY upload.upload_time DESC;";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Head head=new Head();
		List<Object> imgUrlList=new ArrayList<Object>();
		try {
			pstmt = (PreparedStatement) con.prepareStatement(findUserImgId);
			pstmt.setInt(1,uid);
			rs = pstmt.executeQuery();
			//这里调用rs.next()实际他内部是这么做的：rs=rs.next()
			if (!rs.next()){
				head.setMessage("无数据");
				head.setStatus(-2);//-2表示数据库没有数据
				imgUrlList.add(head);
			}
			else {
				head.setMessage("取得数据成功");
				head.setStatus(-3);
				imgUrlList.add(head);
				do{
					String url=rs.getString(1);
					//System.out.println("图片url为-->"+url);
					imgUrlList.add(url);
				}while(rs.next());
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
		return imgUrlList;
		
	}
}
