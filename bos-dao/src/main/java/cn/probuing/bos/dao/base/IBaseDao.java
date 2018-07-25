package cn.probuing.bos.dao.base;

import cn.probuing.bos.utils.PageBean;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/18 13:37
 * @Description:
 */
public interface IBaseDao<T> {
    public void save(T entity);

    void delete(T entity);

    void update(T entity);

    T findById(Serializable id);

    List<T> findAll();

    public void executeUpdate(String queryName, Object... objects);

    public void queryPage(PageBean pageBean);

    void saveOrUpdate(T entiry);
}
