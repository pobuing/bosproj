package cn.probuing.bos.service.impl;

import cn.probuing.bos.dao.IRoleDao;
import cn.probuing.bos.domain.Function;
import cn.probuing.bos.domain.Role;
import cn.probuing.bos.service.IRoleService;
import cn.probuing.bos.utils.PageBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/8/15 17:08
 * @Description:
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao iRoleDao;

    @Override
    public void save(Role model, String functionIds) {
        iRoleDao.save(model);
        if (StringUtils.isNotBlank(functionIds)) {
            String[] fIds = functionIds.split(",");
            for (String functionId : fIds) {
                Function function = new Function(functionId);
                model.getFunctions().add(function);
            }
        }
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        iRoleDao.queryPage(pageBean);
    }

    @Override
    public List<Role> findAll() {
        return iRoleDao.findAll();
    }
}
