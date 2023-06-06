package top.chumi.oa.service;

import com.sun.org.apache.xpath.internal.operations.Neg;
import top.chumi.oa.dao.RbacDao;
import top.chumi.oa.dao.UserDao;
import top.chumi.oa.entity.Node;
import top.chumi.oa.entity.User;
import top.chumi.oa.service.exception.BussinessException;
import top.chumi.oa.utils.MD5Utils;

import java.util.List;

public class UserService {
    private UserDao userDao=new UserDao();
    private RbacDao rbacDao = new RbacDao();

    public User checkLogin(String username, String password) {
        User user = userDao.selectByUsername(username);
        if (user==null){
            //用户不存在，抛出异常（自建）
            throw new BussinessException("L001","用户名不存在");
        }
        //加盐MD5摘要
        String md5 = MD5Utils.md5Digest(password, user.getSalt());
        System.out.println(user);
        if (!md5.equals(user.getPassword())){
            throw new BussinessException("L002", "密码错误");
        }
        return user;
    }
    public List<Node> selectNodeByUserId(Long userId) {
        List<Node> nodeList = rbacDao.selectNodeByUserId(userId);
        return nodeList;
    }
}
