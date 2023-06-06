package top.chumi.oa.dao;

import org.junit.Test;
import top.chumi.oa.entity.Notice;
import top.chumi.oa.utils.MyBatisUtils;

import java.util.Date;

import static org.junit.Assert.*;

public class NoticeDaoTest {

    @Test
    public void testInsert() {
        MyBatisUtils.executeUpdate(session -> {
            NoticeDao dao = session.getMapper(NoticeDao.class);
            Notice notice=new Notice();
            notice.setReceiverId(2l);
            notice.setContent("测试消息");
            notice.setCreateTime(new Date());
            dao.insert(notice);
            return null;
        });
    }
}