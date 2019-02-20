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
import javax.servlet.http.HttpSession;

import util.JsonUtil;
import bean.Head;
import dao.historyImp;
import dao_inter.historyInter;


@WebServlet("/history.do")
public class checkHistory extends HttpServlet {
	int uid;
	private static final long serialVersionUID = 1L;
       
   
    public checkHistory() {
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
		//这个resp是告知浏览器如何处理你的响应。
		resp.setCharacterEncoding("UTF-8");
		//通过out对象向前台发送json数据
		PrintWriter out = resp.getWriter();
		Head head=new Head();
		uid=(int)req.getSession().getAttribute("uid");
		historyInter hi=new historyImp();
		List<Object> bufferList=null;
		List<Object> imgUrlList=new ArrayList<Object>();
		bufferList=hi.getHistoryPhoto(uid);
		//检查操作是否成功
		switch (((Head)bufferList.get(0)).getStatus()) {
		case -2:
			head.setMessage("还未发布照片。");
			head.setStatus(-2);
			imgUrlList.add(head);
			break;
		case -3:
			
			imgUrlList=bufferList;
			break;
		default:
			break;
		}
		String json = JsonUtil.toJson(imgUrlList);
		System.out.println("**********输出json字符串**********\n" + json
				+ "\n\n");
		out.write(json);
		out.close();
		
	}
}
