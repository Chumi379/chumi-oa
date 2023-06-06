package top.chumi.oa.dao;

import top.chumi.oa.entity.User;
import top.chumi.oa.utils.MyBatisUtils;

public class UserDao {
    public User selectByUsername(String username){
        User user = (User) MyBatisUtils.executeQuery(session -> session.selectOne("usermapper.selectByUsername", username));
        return user;
    }
}
