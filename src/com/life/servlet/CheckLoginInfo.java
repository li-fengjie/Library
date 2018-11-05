package com.life.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.life.bean.Admin;
import com.life.service.AdminService;
import com.life.service.impl.AdminServiceImpl;

/**
 * Servlet implementation class CheckLoginInfo
 */
@WebServlet("/checklogininfo")
public class CheckLoginInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckLoginInfo() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	private AdminService userService=new AdminServiceImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String code = request.getParameter("checkcode");
		String checkcode = (String)request.getSession().getAttribute("checkcode");
		Admin user=userService.login(username, password);
		if(user!=null  && code.equals(checkcode)) {
			request.getSession().setAttribute("admin", user);
			response.getWriter().print("true");
		}else {
			response.getWriter().print("false");
		}
		
		
	}

}
