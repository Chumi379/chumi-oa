package top.chumi.oa.dao;

import org.junit.Test;
import top.chumi.oa.entity.ProcessFlow;
import top.chumi.oa.utils.MyBatisUtils;

import java.util.Date;

import static org.junit.Assert.*;

public class ProcessFlowDaoTest {

    @Test
    public void testInsert() {
        MyBatisUtils.executeUpdate(session -> {
            ProcessFlowDao dao = session.getMapper(ProcessFlowDao.class);
            ProcessFlow flow = new ProcessFlow();
            flow.setFormId(3l);
            flow.setOperatorId(2l);
            flow.setAction("audit");
            flow.setReason("approved");
            flow.setReason("同意");
            flow.setCreateTime(new Date());
            flow.setAuditTime(new Date());
            flow.setOrderNo(1);
            flow.setState("ready");
            flow.setIsLast(1);
            dao.insert(flow);
            return null;
        });
    }
}