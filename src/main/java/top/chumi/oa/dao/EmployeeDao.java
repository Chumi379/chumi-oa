package top.chumi.oa.dao;

import org.apache.ibatis.annotations.Param;
import top.chumi.oa.entity.Employee;

public interface EmployeeDao {
    public Employee selectById(Long employeeId);

    /**
     * 根据传入员工获取上级主管对象
     * @param employee 员工对象
     * @return 上级主管对象
     */
    public Employee selectLeader(@Param("emp") Employee employee);
}
