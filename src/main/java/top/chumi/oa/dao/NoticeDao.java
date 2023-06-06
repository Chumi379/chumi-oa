package top.chumi.oa.dao;

import top.chumi.oa.entity.Notice;

import java.util.List;

public interface NoticeDao {
    public void insert(Notice notice);
    public List<Notice> selectByReceiverId(Long receiverId);
}
