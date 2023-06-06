package top.chumi.oa.service;

import top.chumi.oa.dao.NoticeDao;
import top.chumi.oa.entity.Notice;
import top.chumi.oa.utils.MyBatisUtils;

import java.util.List;

public class NoticeService {
    public List<Notice> getNoticeList(Long receverId) {
        return (List)MyBatisUtils.executeQuery(session -> {
            NoticeDao dao = session.getMapper(NoticeDao.class);
            return dao.selectByReceiverId(receverId);
        });
    }
}
