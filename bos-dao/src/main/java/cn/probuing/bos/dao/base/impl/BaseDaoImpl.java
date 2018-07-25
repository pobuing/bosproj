package cn.probuing.bos.dao.base.impl;

import cn.probuing.bos.dao.base.IBaseDao;
import cn.probuing.bos.utils.PageBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/18 13:41
 * @Description:
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
    private Class<T> entityClass;

    public BaseDaoImpl() {
        ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = superclass.getActualTypeArguments();
        entityClass = (Class<T>) actualTypeArguments[0];
    }

    @Resource
    public void setMySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public void save(T entity) {
        this.getHibernateTemplate().save(entity);
    }

    @Override
    public void delete(T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    @Override
    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }

    @Override
    public T findById(Serializable id) {
        return this.getHibernateTemplate().get(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        String hql = "FROM" + entityClass.getSimpleName();
        return (List<T>) this.getHibernateTemplate().find(hql);
    }

    @Override
    public void executeUpdate(String queryName, Object... objects) {
        Session session = this.getSessionFactory().getCurrentSession();
        Query query = session.getNamedQuery(queryName);
        for (int i = 0; i < objects.length; i++) {
            query.setParameter(i, objects[i]);
        }
        //执行更新
        query.executeUpdate();
    }

    @Override
    public void queryPage(PageBean pageBean) {
        int currentPage = pageBean.getCurrentPage();
        int pageSize = pageBean.getPageSize();
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
        //查询数据量
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> countList = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        Long count = countList.get(0);
        pageBean.setTotal(count.intValue());
        //查询数据
        detachedCriteria.setProjection(null);
        //计算页数
        int first = (currentPage - 1) * pageSize;
        int max = pageSize;
        List rows = this.getHibernateTemplate().findByCriteria(detachedCriteria, first, max);
        pageBean.setRows(rows);
    }

    @Override
    public void saveOrUpdate(T entiry) {
        this.getHibernateTemplate().saveOrUpdate(entiry);
    }
}
