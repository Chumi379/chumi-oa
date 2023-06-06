package top.chumi.oa.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.chumi.oa.entity.User;
import top.chumi.oa.service.UserService;
import top.chumi.oa.service.exception.BussinessException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "LoginServlet",urlPatterns = "/check_login")
public class LoginServlet extends HttpServlet {
    Logger logger= LoggerFactory.getLogger(LoginServlet.class);
    private UserService userService=new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //接受用户输入
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Map<String,Object> res=new HashMap<>();
        try {
            //调用业务逻辑
            User user = userService.checkLogin(username, password);
            HttpSession session = request.getSession();
            session.setAttribute("login_user",user);
            res.put("code", "0");
            res.put("message", "success");
            res.put("redirect_url","/index");
        }catch (BussinessException ex){
            logger.error(ex.getMessage(),ex);
            res.put("code", ex.getCode());
            res.put("message", ex.getMessage());
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            res.put("code", ex.getClass().getSimpleName());
            res.put("message", ex.getMessage());
        }
        //返回对应结果
        String jsonString = JSON.toJSONString(res);
        response.getWriter().println(jsonString);
    }
}
