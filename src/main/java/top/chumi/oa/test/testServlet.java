package top.chumi.oa.test;

import top.chumi.oa.utils.MyBatisUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "testServlet", value = "/test")
public class testServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str = (String) MyBatisUtils.executeQuery(session -> session.selectOne("test.sample"));
        request.setAttribute("result", str);
        request.getRequestDispatcher("/test.ftl").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
