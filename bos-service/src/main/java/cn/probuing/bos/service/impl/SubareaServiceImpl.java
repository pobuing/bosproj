package cn.probuing.bos.service.impl;

import cn.probuing.bos.dao.ISubareaDao;
import cn.probuing.bos.domain.Subarea;
import cn.probuing.bos.service.ISubareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
