package cn.probuing.bos.impl;

import cn.probuing.bos.dao.IFunctionDao;
import cn.probuing.bos.dao.base.impl.BaseDaoImpl;
import cn.probuing.bos.domain.Function;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/8/15 15:25
 * @Description:
 */
@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {
    public List<Function> findAll() {
        String hql = "FROM Function f WHERE f.parentFunction IS NULL";
        List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);
        return list;
    }
}
