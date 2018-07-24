package cn.probuing.bos.utils;

import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/24 11:54
 * @Description:分页查询封装
 */
public class PageBean {
    //当前页
    private int currentPage;
    //总页数
    private int total;
    //查询条件
    private DetachedCriteria detachedCriteria;
    //查询结果
    private List rows;
    //每页显示记录数
    private int pageSize;


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public DetachedCriteria getDetachedCriteria() {
        return detachedCriteria;
    }

    public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
        this.detachedCriteria = detachedCriteria;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
