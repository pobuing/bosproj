package cn.probuing.bos.dao;

import cn.probuing.bos.dao.base.IBaseDao;
import cn.probuing.bos.domain.Region;

import java.util.List;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/25 17:58
 * @Description:
 */
public interface IRegionDao extends IBaseDao<Region> {
    /**
     * 根据输入的关键字段返回list
     * @param q
     * @return
     */
    List<Region> findListByQ(String q);

}
