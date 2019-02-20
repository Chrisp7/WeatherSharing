package database;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	private static Connection instance=null;
	public DBConnection(){}
	public static synchronized Connection getInstance() {
		if (instance == null) {
			instance = new DBConnection().getConn();
			return instance;
		} else
			return instance;
	}

	private static Connection conn = null;
	private static Properties props = null;
	private static String url = null;
	private static String ln = null;
	private static String pw = null;
	static {// JVM加载该类时加载好数据库驱动
		props = new Properties();
		try {
			props.load(DBConnection.class
					.getResourceAsStream("/dbconf.properties"));

			Class.forName(props.getProperty("driverClass"));
			url = props.getProperty("url");
			ln = props.getProperty("ln");
			pw = props.getProperty("pw");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Connection getConn() {
		try {
			conn = (Connection) DriverManager.getConnection(url, ln, pw);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void closeConn() {
		try {
			if (conn != null)
				conn.close();
			if (props != null)
				props.clear();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * private static DBConnection instance = null;
	 * 
	 * // 取得连接 public static synchronized Connection getConnection() { if
	 * (instance == null) { instance = new DBConnection(); } return
	 * instance._getConnection(); }
	 * 
	 * private DBConnection() { super(); }
	 * 
	 * private Connection _getConnection() { try { String url = null; String ln
	 * = null; String pw = null; Connection conn=null; Properties p = new
	 * Properties();
	 * p.load(DBConnection.class.getResourceAsStream("/dbconf.properties"));
	 * 
	 * Class.forName(p.getProperty("driverClass")); url = p.getProperty("url");
	 * ln = p.getProperty("ln"); pw = p.getProperty("pw"); conn = (Connection)
	 * DriverManager.getConnection(url,ln,pw); conn.setAutoCommit(false); return
	 * conn; } catch (Exception se) { System.out.println(se); return null; }
	 * 
	 * 
	 * }
	 * 
	 * // 释放资源 public static void dbClose(Connection conn, PreparedStatement ps,
	 * ResultSet rs) throws SQLException { rs.close(); ps.close(); conn.close();
	 * }
	 */
}
