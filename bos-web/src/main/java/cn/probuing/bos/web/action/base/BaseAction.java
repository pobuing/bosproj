package cn.probuing.bos.web.action.base;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/18 14:03
 * @Description:
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    private T model;

    public BaseAction() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Class<T> tClass = (Class<T>) actualTypeArguments[0];
        try {
            model = tClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T getModel() {
        return model;
    }
}
