package cn.probuing.bos.service;

import cn.probuing.bos.domain.Function;
import cn.probuing.bos.utils.PageBean;

import java.util.List;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/8/15 15:22
 * @Description:
 */
public interface IFunctionService {

    List<Function> findAll();

    void save(Function model);

    void pageQuery(PageBean pageBean);
}
