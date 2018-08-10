package cn.probuing.bos.service;

import cn.probuing.bos.domain.Workordermanage;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/8/9 11:46
 * @Description:
 */
public interface IWorkOrderManageService {

    /**
     * 保存工单
     * @param model
     */
    void save(Workordermanage model);
}
