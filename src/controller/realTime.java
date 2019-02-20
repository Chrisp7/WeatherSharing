package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.JsonUtil;
import bean.Head;
import dao.cityQuery;
import dao.historyImp;
import dao.realTimeImp;
import dao_inter.historyInter;
import dao_inter.realTimeInter;

@WebServlet("/realTime.do")
public class realTime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public realTime() {
		super();
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
		String currentCity=req.getParameter("currentCity");
		PrintWriter out = resp.getWriter();
		Head head = new Head();
		List<Object> bufferList = null;
		List<Object> imgUrlList = new ArrayList<Object>();
		int cityId=new cityQuery().findCityId(currentCity);
		bufferList =new realTimeImp().getRealTimePhoto(cityId);
		
		// 检查操作是否成功
		switch (((Head) bufferList.get(0)).getStatus()) {
		case -2:
			head.setMessage("还未发布照片。");
			head.setStatus(-2);
			imgUrlList.add(head);
			break;
		case -3:
			head.setMessage("获取照片成功。");
			imgUrlList = bufferList;
			break;
		default:
			break;
		}
		String json = JsonUtil.toJson(imgUrlList);
		System.out.println("**********输出json字符串**********\n" + json + "\n\n");
		out.write(json);
		out.close();
	}

}
