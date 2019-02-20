package controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xml.internal.security.Init;

import corefuction.visitUrl;
import bean.User;
import bean.Weather;
import dao.userLoginImp;
import dao_inter.userLogin;

@WebServlet("/login.do")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User user = null;
	int flag;
	String ipAddres = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public login() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");		
		String message = "";
		String username = req.getParameter("username");
		String passwd = req.getParameter("passwd");
		user = new User(username, passwd);
		userLogin ul = new userLoginImp();
		flag = ul.userCheck(user);
		switch (flag) {
		case 0:
			message = "用户不存在！";
			break;
		case -1:
			message = "密码错误！";
			break;
		default:
			message = "登录成功！";
			req.getSession().setAttribute("login", username);
			req.getSession().setAttribute("uid", flag);
			break;
		}
		req.setAttribute("message", message);
		req.getRequestDispatcher("/user2.do").forward(req, resp);
	}

	

}
