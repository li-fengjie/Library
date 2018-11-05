package com.life.servlet;

import com.life.bean.User;
import com.life.dao.UserDao;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/selectUser")
public class SelectServlet extends HttpServlet {
    private UserDao userDao=new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        User user = userDao.selectUser(id);
        if(user!=null) {
            JSONObject jsonObject = JSONObject.fromObject(user);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(jsonObject.toString());
            System.out.println(jsonObject.toString());
        }else {
            response.getWriter().print("{\"pageNo\":0,\"pageSize\":0,\"pageSum\":0,\"user\":[]}");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
