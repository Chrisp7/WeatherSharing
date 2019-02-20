package bean;

import java.io.Serializable;

public class User implements Serializable{
	private String UserName;
	private String PassWord;
	public User(){}
	public User(String username,String password){
		UserName=username;
		PassWord=password;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassWord() {
		return PassWord;
	}
	public void setPassWord(String passWord) {
		PassWord = passWord;
	}
	
}
