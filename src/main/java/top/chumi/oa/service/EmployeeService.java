package top.chumi.oa.service;

import top.chumi.oa.dao.EmployeeDao;
import top.chumi.oa.entity.Employee;
import top.chumi.oa.utils.MyBatisUtils;

public class EmployeeService {
    public Employee selectById(Long employeeId){
        return  (Employee)MyBatisUtils.executeQuery(session -> {
            EmployeeDao employeeDao = session.getMapper(EmployeeDao.class);
            return employeeDao.selectById(employeeId);
        });
    }
}
