package top.chumi.oa.controller;

import top.chumi.oa.entity.Department;
import top.chumi.oa.entity.Employee;
import top.chumi.oa.entity.Node;
import top.chumi.oa.entity.User;
import top.chumi.oa.service.DepartmentService;
import top.chumi.oa.service.EmployeeService;
import top.chumi.oa.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "IndexServlet", urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
    private UserService userService=new UserService();
    private EmployeeService employeeService=new EmployeeService();
    private DepartmentService departmentService=new DepartmentService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //由于两个Servlet不是一个请求，所以我们不能通过getAttribute来获取属性
        // 而是Session
        HttpSession session = request.getSession();
        //得到当前登录用户对象
        User loginUser = (User) session.getAttribute("login_user");
        //获取当前登录的员工对象
        Employee employee = employeeService.selectById(loginUser.getEmployeeId());
        System.out.println(employee.getName());
        //获取员工对应的部门
        Department department = departmentService.selectById(employee.getDepartmentId());
        //获取登录用户的可用功能模块
        List<Node> nodeList = userService.selectNodeByUserId(loginUser.getUserId());
        //放入请求属性
        request.setAttribute("node_list", nodeList);
        //由于当前登录的员工信息需要反复用，我们将其放入Session而非request
        session.setAttribute("current_employee",employee);
        session.setAttribute("current_department",department);
        request.getRequestDispatcher("/index.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
