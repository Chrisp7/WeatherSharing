package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.sun.accessibility.internal.resources.accessibility;

import dao.registerImp;
import dao_inter.registerInter;

/**
 * Servlet implementation class register
 */
@MultipartConfig(location = "/Users/CP/Documents/Study/code/jee_eclipse/WeatherSharing/WebContent/weathersharing_pic/user_portrait/")
@WebServlet("/register.do")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String PORTRAIT_PATH="./weathersharing_pic/user_portrait";

	public register() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		Part part = req.getPart("portrait");
		String userName = req.getParameter("userName");
		String passWord = req.getParameter("passWord");
		String province = req.getParameter("province");
		String city = req.getParameter("city");
//		String filename=getFilename(part);
		String filename=Long.toString(System.currentTimeMillis())+".jpg";
		String file_path=PORTRAIT_PATH+"/"+filename;
		System.out.println(file_path);
		part.write(filename);
		registerInter ri=new registerImp();
		if (ri.addUser(userName, passWord, city, file_path)) {
			System.out.println("插入成功！");
			req.getRequestDispatcher("/success.html").forward(req, resp);;
		}else{
			System.out.println("插入失败！");
		}
	}

	private String getFilename(Part part) {
		String header = part.getHeader("Content-Disposition");
		String filename = header.substring(header.indexOf("filename=\"") + 10,
				header.lastIndexOf("\""));
		header.lastIndexOf("\"");
		return filename;
	}

}
