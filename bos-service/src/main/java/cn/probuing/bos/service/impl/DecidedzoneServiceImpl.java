package cn.probuing.bos.service.impl;

import cn.probuing.bos.dao.IDecidedzoneDao;
import cn.probuing.bos.dao.ISubareaDao;
import cn.probuing.bos.domain.Decidedzone;
import cn.probuing.bos.domain.Subarea;
import cn.probuing.bos.service.IDecidedZoneService;
import cn.probuing.bos.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ${DESCRIPTION}
 *
 * @author wx-blackmac
 * @create 2018-08-01 22:35
 * bestwishes for you
 * good luck!
 **/
@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedZoneService {
    @Autowired
    private IDecidedzoneDao iDecidedzoneDao;
    //持有subareaDao对象
    @Autowired
    private ISubareaDao iSubareaDao;

    @Override
    public void save(Decidedzone model, String[] subareaid) {
        iDecidedzoneDao.save(model);
        //根据定区中要添加的分区id查询出分区数据
        for (String id : subareaid) {
            Subarea subarea = iSubareaDao.findById(id);
            //多对一表关系 多的一方维护外键关系
            subarea.setDecidedzone(model);
        }

    }

    @Override
    public void pageQuery(PageBean pageBean) {
        iDecidedzoneDao.queryPage(pageBean);
    }
}
