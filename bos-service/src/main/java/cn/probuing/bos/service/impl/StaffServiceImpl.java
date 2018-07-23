package cn.probuing.bos.service.impl;

import cn.probuing.bos.dao.IStaffDao;
import cn.probuing.bos.domain.Staff;
import cn.probuing.bos.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
