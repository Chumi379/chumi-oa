package top.chumi.oa.dao;

import org.apache.ibatis.annotations.Param;
import top.chumi.oa.entity.LeaveForm;

import java.util.List;
import java.util.Map;

public interface LeaveFormDao {
    public void insert(LeaveForm form);

    public List<Map> selectByParams(@Param("pf_state") String pfState, @Param("pf_operator_id") Long operatorId);

    public LeaveForm selectById(Long formId);

    public void update(LeaveForm form);
}
