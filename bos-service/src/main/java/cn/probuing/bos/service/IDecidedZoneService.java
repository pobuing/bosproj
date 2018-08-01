package cn.probuing.bos.service;

import cn.probuing.bos.domain.Decidedzone;
import cn.probuing.bos.utils.PageBean;

/**
 * ${DESCRIPTION}
 *
 * @author wx-blackmac
 * @create 2018-08-01 22:33
 * bestwishes for you
 * good luck!
 **/
public interface IDecidedZoneService {
    /**
     * 保存定区数据
     *
     * @param model
     * @param subareaid
     */
    void save(Decidedzone model, String[] subareaid);

    void pageQuery(PageBean pageBean);
}
