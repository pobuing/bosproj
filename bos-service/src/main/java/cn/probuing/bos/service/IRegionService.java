package cn.probuing.bos.service;

import cn.probuing.bos.domain.Region;
import cn.probuing.bos.utils.PageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/25 17:56
 * @Description:
 */
public interface IRegionService {
    /**
     * 批量保存区域信息
     *
     * @param regionArrayList
     */
    void saveBatch(ArrayList<Region> regionArrayList);

    void pageQuery(PageBean pageBean);

    List<Region> findListByQ(String q);

    List<Region> findAll();
}
