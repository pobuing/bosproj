package cn.probuing.bos.service.impl;

import cn.probuing.bos.dao.IStaffDao;
import cn.probuing.bos.domain.Staff;
import cn.probuing.bos.service.IStaffService;
import cn.probuing.bos.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/23 17:33
 * @Description:
 */
@Service
@Transactional
public class StaffServiceImpl implements IStaffService {
    @Autowired
    private IStaffDao staffDao;

    @Override
    public void save(Staff model) {
        staffDao.save(model);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        staffDao.queryPage(pageBean);
    }

    @Override
    public void deleteBatch(String ids) {
        //拆分ids
        String[] idsArr = ids.split(",");
        for (String id : idsArr) {
            staffDao.executeUpdate("staff.delete", id);
        }
    }

    @Override
    public Staff findById(String id) {
        return staffDao.findById(id);
    }

    @Override
    public void update(Staff staff) {
        staffDao.update(staff);
    }

    @Override
    public List<Staff> findListNotDelete() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
        detachedCriteria.add(Restrictions.eq("deltag", "0"));
        return staffDao.findByCriteria(detachedCriteria);
    }
}
