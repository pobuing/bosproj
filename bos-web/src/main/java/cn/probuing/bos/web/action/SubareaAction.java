package cn.probuing.bos.web.action;

import cn.probuing.bos.domain.Region;
import cn.probuing.bos.domain.Subarea;
import cn.probuing.bos.service.ISubareaService;
import cn.probuing.bos.web.action.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/26 16:33
 * @Description:
 */
@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
    @Autowired
    private ISubareaService iSubareaService;

    public String add() {
        iSubareaService.save(model);
        return LIST;
    }


    /**
     * 待条件查询
     *
     * @return
     */
    public String pageQuery() {
        //获得离线查询对象
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
        //动态添加过滤条件
        String addresskey = model.getAddresskey();
        if(StringUtils.isNotBlank(addresskey)){
            //添加过滤条件，根据地址关键字模糊查询
            detachedCriteria.add(Restrictions.like("addresskey", "%"+addresskey+"%"));
        }
        //设置别名 多表关联查询
        detachedCriteria.createAlias("region", "r");
        Region region = model.getRegion();
        if (region != null) {

            //获得页面传递的参数
            String province = region.getProvince();
            String city = region.getCity();
            String district = region.getDistrict();
            if (StringUtils.isNotBlank(province)) {
                detachedCriteria.add(Restrictions.like("r.province", "%" + province + "%"));
            }
            if (StringUtils.isNotBlank(city)) {
                detachedCriteria.add(Restrictions.like("r.city", "%" + city + "%"));
            }
            if (StringUtils.isNotBlank(district)) {
                detachedCriteria.add(Restrictions.like("r.district", "%" + district + "%"));
            }
        }
        iSubareaService.pageQuery(pageBean);
        try {
            this.java2json(pageBean, new String[]{"currentPage", "detachedCriteria", "pageSize", "decidedzone", "subareas"});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
    }
}
