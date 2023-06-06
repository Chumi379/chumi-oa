package top.chumi.oa.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.function.Function;

/**
 * MyBatisUtils工具类
 * 改良：在执行SQL前后自动打开Connection和关闭Connection
 */
public class MyBatisUtils  {
    private static SqlSessionFactory sqlSessionFactory = null;
    static {
        Reader reader=null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 执行SELECT查询SQL
     * @param function 要执行查询语句的代码块
     * @return 查询结果
     */
    public static Object executeQuery(Function<SqlSession,Object> function){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Object obj = function.apply(sqlSession);
            return obj;
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 执行INSERT/UPDATE/DELETE写操作SQL
     * @param function 要执行的写操作代码块
     * @return 写操作返回结果
     */
    public static Object executeUpdate(Function<SqlSession,Object> function){
        SqlSession sqlSession = sqlSessionFactory.openSession(false);
        try {
            Object obj = function.apply(sqlSession);
            sqlSession.commit();
            return obj;
        }catch (RuntimeException e){
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }
}
