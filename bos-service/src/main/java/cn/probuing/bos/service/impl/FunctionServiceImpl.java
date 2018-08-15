package cn.probuing.bos.service.impl;

import cn.probuing.bos.dao.IFunctionDao;
import cn.probuing.bos.domain.Function;
import cn.probuing.bos.service.IFunctionService;
import cn.probuing.bos.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/8/15 15:23
 * @Description:
 */
@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {
    @Autowired
    private IFunctionDao functionDao;

    @Override
    public List<Function> findAll() {
        List<Function> list = functionDao.findAll();
        return list;
    }

    @Override
    public void save(Function model) {
        Function function = model.getParentFunction();
        if (function != null && function.getId().equals("")) {
            model.setParentFunction(null);
        }
        functionDao.save(model);

    }

    @Override
    public void pageQuery(PageBean pageBean) {
        functionDao.queryPage(pageBean);
    }
}
