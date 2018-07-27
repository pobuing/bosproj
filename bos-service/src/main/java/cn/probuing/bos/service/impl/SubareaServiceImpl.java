package cn.probuing.bos.service.impl;

import cn.probuing.bos.dao.ISubareaDao;
import cn.probuing.bos.domain.Subarea;
import cn.probuing.bos.service.ISubareaService;
import cn.probuing.bos.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/26 16:36
 * @Description:
 */
@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {
    @Autowired
    private ISubareaDao iSubareaDao;

    @Override
    public void save(Subarea model) {
        iSubareaDao.save(model);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        iSubareaDao.queryPage(pageBean);
    }

    @Override
    public List<Subarea> findAll() {
        return iSubareaDao.findAll();
    }

    @Override
    public List<Subarea> findListNotAssociation() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
        //筛选出分区为空的数据
        detachedCriteria.add(Restrictions.isNull("decidedzone"));
        return iSubareaDao.findByCriteria(detachedCriteria);
    }
}
