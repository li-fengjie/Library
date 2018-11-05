package com.life.servlet;

import com.life.dao.UserDao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaveServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private UserDao userDao=new UserDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("update_stu_id");
		String college=request.getParameter("update_stu_college");
		String grade=request.getParameter("update_stu_grade");
		String profession=request.getParameter("update_stu_profession");
		String classes=request.getParameter("update_stu_classes");
		String name=request.getParameter("update_stu_name");
		userDao.updateUser(id, college,grade,profession,classes,name);
		System.out.println(id + " " + college + " " + grade + " " + profession + " " + classes + " " + name);
		response.getWriter().print("true");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
