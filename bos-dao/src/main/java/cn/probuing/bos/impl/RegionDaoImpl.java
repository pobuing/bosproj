package cn.probuing.bos.impl;

import cn.probuing.bos.dao.IRegionDao;
import cn.probuing.bos.dao.base.impl.BaseDaoImpl;
import cn.probuing.bos.domain.Region;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/25 18:00
 * @Description:
 */
@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {
    @Override
    public List<Region> findListByQ(String q) {
        String hql = "FROM Region r WHERE r.shortcode LIKE ?" +
                "OR r.citycode LIKE ? OR r.province LIKE ?" +
                "OR r.city LIKE ? OR r.district LIKE ?";
        List<Region> list = (List<Region>) this.getHibernateTemplate().find(hql, "%" + q + "%", "%" + q + "%", "%" + q + "%", "%" + q + "%", "%" + q + "%");
        return list;
    }
}
