package top.chumi.oa.dao;

import org.junit.Test;
import top.chumi.oa.entity.LeaveForm;
import top.chumi.oa.utils.MyBatisUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class LeaveFormDaoTest {

    @Test
    public void testInsert() {
        MyBatisUtils.executeUpdate(session -> {
            LeaveFormDao dao = session.getMapper(LeaveFormDao.class);
            LeaveForm form = new LeaveForm();
            form.setEmployeeId(4l);
            form.setFormType(1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startTime=null;
            Date endTime=null;
            try{
                startTime = sdf.parse("2020-3-25 08:00:00");
                endTime = sdf.parse("2020-4-1 18:00:00");
            }catch (ParseException e){
                e.printStackTrace();
            }
            form.setStartTime(startTime);
            form.setEndTime(endTime);
            form.setReason("回家探亲");
            form.setCreateTime(new Date());
            form.setState("processing");
            dao.insert(form);
            return null;
        });
    }
    @Test
    public void testSelectByParams() {
        MyBatisUtils.executeQuery(session -> {
            LeaveFormDao dao = session.getMapper(LeaveFormDao.class);
            List<Map> mapList = dao.selectByParams("process", 2l);
            System.out.println(mapList);
            return mapList;
        });
    }
}