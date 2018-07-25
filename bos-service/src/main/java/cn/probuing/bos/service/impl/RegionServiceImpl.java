package cn.probuing.bos.service.impl;

import cn.probuing.bos.dao.IRegionDao;
import cn.probuing.bos.domain.Region;
import cn.probuing.bos.service.IRegionService;
import cn.probuing.bos.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/25 17:57
 * @Description:
 */
@Service
@Transactional
public class RegionServiceImpl implements IRegionService {
    @Autowired
    private IRegionDao iRegionDao;

    @Override
    public void saveBatch(ArrayList<Region> regionArrayList) {
        for (Region region : regionArrayList) {
            iRegionDao.saveOrUpdate(region);

        }
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        iRegionDao.queryPage(pageBean);
    }
}
