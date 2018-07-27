package cn.probuing.bos.dao;

import cn.probuing.bos.dao.base.IBaseDao;
import cn.probuing.bos.domain.Subarea;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/26 16:37
 * @Description:
 */
public interface ISubareaDao extends IBaseDao<Subarea> {
    void save(Subarea model);
}
