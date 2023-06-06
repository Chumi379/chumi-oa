package top.chumi.oa.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.chumi.oa.entity.LeaveForm;
import top.chumi.oa.entity.User;
import top.chumi.oa.service.LeaveFormService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@WebServlet(name = "LeaveFormServlet", urlPatterns = "/leave/*")
public class LeaveFormServlet extends HttpServlet {
    private LeaveFormService leaveFormService = new LeaveFormService();
    private Logger logger = LoggerFactory.getLogger(LeaveFormServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String uri = request.getRequestURI();
        String methodName = uri.substring(uri.lastIndexOf("/") + 1);
        if (methodName.equals("create")) {
            this.create(request, response);
        } else if (methodName.equals("list")) {
            this.getLeaveFormList(request, response);
        } else if (methodName.equals("audit")) {
            this.audit(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * 创建请假单
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受各项请假单的数据
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("login_user");
        String formType = request.getParameter("formType");
        String strStartTime = request.getParameter("startTime");
        String strEndTime = request.getParameter("endTime");
        String reason = request.getParameter("reason");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");
        Map result = new HashMap();
        try {
            LeaveForm form = new LeaveForm();
            form.setEmployeeId(user.getEmployeeId());
            form.setStartTime(sdf.parse(strStartTime));
            form.setEndTime(sdf.parse(strEndTime));
            form.setFormType(Integer.parseInt(formType));
            form.setReason(reason);
            form.setCreateTime(new Date());
            //2.调用业务逻辑方法
            leaveFormService.createLeaveForm(form);
            result.put("code", "0");
            result.put("message", "success");

        } catch (Exception e) {
            logger.error("请假申请异常", e);
            result.put("code", e.getClass().getSimpleName());
            result.put("message", e.getMessage());
        }
        //3.组织相应数据
        String json = JSON.toJSONString(result);
        response.getWriter().println(json);
    }

    /**
     * 查询需要审核的请假单列表
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void getLeaveFormList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("login_user");
        List<Map> formList = leaveFormService.getLeaveFormList("process", user.getEmployeeId());
        Map result = new HashMap();
        result.put("code", 0);
        result.put("msg", "");
        result.put("count", formList.size());
        result.put("data", formList);
        String json = JSON.toJSONString(result);
        response.getWriter().println(json);
    }

    private void audit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formId = request.getParameter("formId");
        String result = request.getParameter("result");
        String reason = request.getParameter("reason");
        User user =(User) request.getSession().getAttribute("login_user");
        Map mpResult=new HashMap();
        try {
            leaveFormService.audit(Long.parseLong(formId), user.getEmployeeId(), result, reason);
            mpResult.put("code", "0");
            mpResult.put("message", "success");
        } catch (Exception e) {
            logger.error("请假单审核失败", e);
            mpResult.put("code", e.getClass().getSimpleName());
            mpResult.put("message", e.getMessage());
        }
        String jsonString = JSON.toJSONString(mpResult);
        response.getWriter().println(jsonString);
    }
}