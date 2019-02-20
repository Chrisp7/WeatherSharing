package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.sun.org.apache.xalan.internal.xsltc.trax.OutputSettings;

import dao.cityQuery;
import dao.uploadImp;
import dao_inter.uploadInter;

/**
 * Servlet implementation class uploadServlet
 */
@MultipartConfig(location="/Users/CP/Documents/Study/code/jee_eclipse/WeatherSharing/WebContent/weathersharing_pic/weather/")
@WebServlet("/upload.do")
public class uploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String PHOTO_PATH="./weathersharing_pic/weather";
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
	
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		Part part=req.getPart("photo");
		String province=req.getParameter("province");
		String city=req.getParameter("city");
		String current_time=req.getParameter("current_time");
		String description=req.getParameter("description");
		int cityId=new cityQuery().findCityId(city);
		String filename=Long.toString(System.currentTimeMillis())+".jpg";
		String file_path=PHOTO_PATH+"/"+filename;
		int uid=(int)req.getSession().getAttribute("uid");
		uploadInter ui=new uploadImp();
		part.write(filename);
		if(ui.upload(file_path, uid,description,cityId)){
			System.out.println("插入成功！");
		}else {
			System.out.println("插入失败！");
		}
		resp.sendRedirect("/WeatherSharing/uploadSuccess.html");
	}
	private String getFilename(Part part){
		String header =part.getHeader("Content-Disposition");
		String filename=header.substring(header.indexOf("filename=\"")+10,header.lastIndexOf("\""));
		header.lastIndexOf("\"");
		return filename;
		}

}
