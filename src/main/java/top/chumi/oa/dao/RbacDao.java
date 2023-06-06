package top.chumi.oa.dao;

import top.chumi.oa.entity.Node;
import top.chumi.oa.utils.MyBatisUtils;

import java.util.List;

public class RbacDao {
    public List<Node> selectNodeByUserId(Long userId) {
        return (List) MyBatisUtils.executeQuery(session -> session.selectList("rbacmapper.selectNodeByUserId", userId));
    }
}
