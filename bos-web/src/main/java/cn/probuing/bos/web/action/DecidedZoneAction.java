package cn.probuing.bos.web.action;

import cn.probuing.bos.domain.Decidedzone;
import cn.probuing.bos.service.IDecidedZoneService;
import cn.probuing.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * ${DESCRIPTION}
 *
 * @author wx-blackmac
 * @create 2018-08-01 22:32
 * bestwishes for you
 * good luck!
 **/
@Controller
@Scope("prototype")
public class DecidedZoneAction extends BaseAction<Decidedzone> {
    public String[] subareaid;

    public void setSubareaid(String[] subareaid) {
        this.subareaid = subareaid;
    }

    @Autowired
    private IDecidedZoneService iDecidedZoneService;

    public String add() {
        iDecidedZoneService.save(model, subareaid);
        return LIST;
    }

    public String pageQuery() throws IOException {
        iDecidedZoneService.pageQuery(pageBean);
        this.java2Json(pageBean, new String[]{"currentPage", "detachedCriteria", "pageSize", "subareas", "decidedzones"});
        return NONE;
    }

}
