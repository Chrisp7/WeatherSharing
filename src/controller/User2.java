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
import javax.servlet.http.HttpSession;
import corefuction.getDegree;
import corefuction.visitUrl;
import dao.getUserInfo;
import bean.Weather;
import bean.userInfo;


@WebServlet("/user2.do")
public class User2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String ipAddres="";
	userInfo userinfo=null;
	public User2() {
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
		HttpSession session = req.getSession();
		if (session.getAttribute("login") == null) {
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		} else {
			String cityName="";
			ArrayList<Weather> weatherlist=null;
			ArrayList<Weather> careCityWeatherlist=null;
			ArrayList<String> degreeData=new ArrayList<String>();
			visitUrl vu=new visitUrl();
			//ipAddres = getIpAddr(req);
			System.out.println("当前IP地址--->112.80.65.143");
			//根据ip获取城市名
			cityName=vu.getCityNameByIp("112.80.65.143");
			session.setAttribute("cityName", cityName);
			//根据城市名获取当地天气
			weatherlist=vu.getWeatherByCityName(cityName);
			//获取度数信息
			for (int i = 3,j=0; i < 6; i++,j++) {
				degreeData.add(j, weatherlist.get(i).getTitle());
			}
			//将一个三天的天气温度写进temperature.js文件
			new getDegree().writeAverageDegreeToFile(degreeData);
			//将获取的所有天气数据存在req对象中，发放userpage.jsp
			session.setAttribute("weatherlist", weatherlist);
			userinfo=new getUserInfo().getUserInformation((int)session.getAttribute("uid"));
			session.setAttribute("userinfo", userinfo);
			System.out.println("usercarecity--->"+userinfo.getCareCity());
			careCityWeatherlist=vu.getWeatherByCityName(userinfo.getCareCity());
			String careCityWeather=careCityWeatherlist.get(3).getTitle();
			
			session.setAttribute("careCityWeather", careCityWeather.substring(10, careCityWeather.length())); 
			req.getRequestDispatcher("/userpage.jsp").forward(req, resp);
			
		}
	}

	
	private String getIpAddr(HttpServletRequest request) {

		String ipAddress = null;
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}

		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

}
