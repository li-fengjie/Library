package com.life.servlet;

import com.life.bean.RoomPageBean;
import com.life.dao.RoomDao;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class InfoServlet
 */
@WebServlet("/roomsinfo")
public class RoomInfoServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private RoomDao RoomDao=new RoomDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pageNoStr=request.getParameter("pageno");
		String pageSizeStr=request.getParameter("pagesize");
		System.out.println(pageNoStr + " " + pageSizeStr);
		int pageno=Integer.parseInt(pageNoStr);
		int pagesize=Integer.parseInt(pageSizeStr);
		RoomPageBean RoomPageBean=RoomDao.queryRoomPageBean(pageno, pagesize);
		if(RoomPageBean != null){
            JSONObject jsonObject = JSONObject.fromObject(RoomPageBean);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(jsonObject.toString());
        } else {
			response.getWriter().print("error");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
