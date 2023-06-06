package top.chumi.oa.service;

import top.chumi.oa.dao.DepartmentDao;
import top.chumi.oa.entity.Department;
import top.chumi.oa.utils.MyBatisUtils;

public class DepartmentService {
    public Department selectById(Long departmentId) {
        return (Department)MyBatisUtils.executeQuery(session -> session.getMapper(DepartmentDao.class).selectById(departmentId));
    }
}
