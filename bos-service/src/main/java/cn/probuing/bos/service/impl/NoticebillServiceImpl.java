package cn.probuing.bos.service.impl;

import cn.probuing.bos.dao.IDecidedzoneDao;
import cn.probuing.bos.dao.IWorkbillDao;
import cn.probuing.bos.dao.InoticebillDao;
import cn.probuing.bos.domain.*;
import cn.probuing.bos.service.INoticebillService;
import cn.probuing.bos.utils.BOSUtils;
import cn.probuing.client.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/8/8 17:12
 * @Description:
 */
@Service
@Transactional
public class NoticebillServiceImpl implements INoticebillService {
    @Autowired
    private InoticebillDao noticebillDao;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IDecidedzoneDao decidedzoneDao;
    @Autowired
    private IWorkbillDao workbillDao;

    @Override
    public void save(Noticebill model) {
        //获取当前登录用户
        User loginUser = BOSUtils.getLoginUser();
        model.setUser(loginUser);
        noticebillDao.save(model);
        //获取客户的取件地址
        String pickaddress = model.getPickaddress();
        //根据取件地址查询定区id
        String decidedId = customerService.findDecidedzoneIdByAddress(pickaddress);
        if (decidedId != null) {
            //查询到定区id 系统自动分单
            Decidedzone decidedzone = decidedzoneDao.findById(decidedId);
            //获得定区的取派员
            Staff staff = decidedzone.getStaff();
            //设置通知单的取派员
            model.setStaff(staff);
            //设置分单类型为自动分单
            model.setOrdertype(Noticebill.ORDERTYPE_AUTO);
            //创建一个新的工单
            Workbill workbill = new Workbill();
            workbill.setAttachbilltimes(0);
            workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
            workbill.setPickstate(Workbill.PICKSTATE_NO);//设置取件状态
            workbill.setRemark(model.getRemark());
            workbill.setStaff(staff);
            workbill.setType(Workbill.TYPE_1);
            workbillDao.save(workbill);
            //调用短信接口 发送短信
        } else {
            //未查询到定区id 改为人工分单
            model.setOrdertype(Noticebill.ORDERTYPE_MAN);
        }
    }
}
