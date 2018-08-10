package cn.probuing.bos.service.impl;

import cn.probuing.bos.dao.IWorkOrderManageDao;
import cn.probuing.bos.domain.Workordermanage;
import cn.probuing.bos.service.IWorkOrderManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/8/9 11:47
 * @Description:
 */
@Service
@Transactional
public class WorkOrderManageServiceImpl implements IWorkOrderManageService {
    @Autowired
    private IWorkOrderManageDao workOrderManageDao;
    @Override
    public void save(Workordermanage model) {
        workOrderManageDao.save(model);
    }
}
