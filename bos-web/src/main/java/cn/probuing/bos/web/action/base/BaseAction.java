package cn.probuing.bos.web.action.base;

import cn.probuing.bos.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/18 14:03
 * @Description:
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    protected static final String HOME = "home";
    protected static final String LIST = "list";

    protected T model;


    //分页
    protected PageBean pageBean = new PageBean();
    protected DetachedCriteria detachedCriteria;


    public BaseAction() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Class<T> tClass = (Class<T>) actualTypeArguments[0];
        detachedCriteria = DetachedCriteria.forClass(tClass);
        pageBean.setDetachedCriteria(detachedCriteria);
        try {
            model = tClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public void java2Json(Object o, String[] exclude) throws IOException {
        JsonConfig config = new JsonConfig();
        config.setExcludes(exclude);
        String json = JSONObject.fromObject(o, config).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().write(json);
    }


    public void setPage(int page) {
        pageBean.setCurrentPage(page);
    }

    public void setRows(int rows) {
        pageBean.setPageSize(rows);
    }

    @Override
    public T getModel() {
        return model;
    }

    public void java2Json(List list, String[] exclude) {
        JsonConfig config = new JsonConfig();
        config.setExcludes(exclude);
        String json = JSONArray.fromObject(list, config).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
