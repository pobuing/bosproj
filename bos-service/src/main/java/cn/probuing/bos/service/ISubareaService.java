package cn.probuing.bos.service;

import cn.probuing.bos.domain.Subarea;
import cn.probuing.bos.utils.PageBean;

import java.util.List;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/26 16:34
 * @Description:
 */
public interface ISubareaService {

    void save(Subarea model);

    void pageQuery(PageBean pageBean);

    List<Subarea> findAll();

    List<Subarea> findListNotAssociation();

    List<Subarea> findListByDecidedzoneId(String decidedzoneId);

}
