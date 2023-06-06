package top.chumi.oa.utils;

import org.junit.Test;

public class MyBatisUtilsTestor {
    @Test
    public void testcase1(){
        String res = (String) MyBatisUtils.executeQuery(sqlSession -> {
            String out = (String) sqlSession.selectOne("test.sample");
            return out;
        });
        System.out.println(res);
    }

}
