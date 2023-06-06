package top.chumi.oa.dao;

import top.chumi.oa.entity.Department;
import top.chumi.oa.entity.Employee;

public interface DepartmentDao {
    public Department selectById(Long departmentId);
}
