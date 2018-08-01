package cn.probuing.bos.web.action;

import cn.probuing.bos.domain.Region;
import cn.probuing.bos.domain.Subarea;
import cn.probuing.bos.service.ISubareaService;
import cn.probuing.bos.utils.FileUtils;
import cn.probuing.bos.web.action.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.List;

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
        if (StringUtils.isNotBlank(addresskey)) {
            //添加过滤条件，根据地址关键字模糊查询
            detachedCriteria.add(Restrictions.like("addresskey", "%" + addresskey + "%"));
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
            this.java2Json(pageBean, new String[]{"currentPage", "detachedCriteria", "pageSize", "decidedzone", "subareas"});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
    }


    /**
     * 导出功能
     *
     * @return
     */
    public String exportXls() {
        //查询所有分区数据
        List<Subarea> list = iSubareaService.findAll();
        //创建Excel文件
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //创建标签页
        HSSFSheet sheet = hssfWorkbook.createSheet("分区数据");
        //创建标题第一行
        HSSFRow headRow = sheet.createRow(0);
        //创建单元格
        headRow.createCell(0).setCellValue("分区编号");
        headRow.createCell(1).setCellValue("开始编号");
        headRow.createCell(2).setCellValue("结束编号");
        headRow.createCell(3).setCellValue("位置信息");
        headRow.createCell(4).setCellValue("省市区");
        for (Subarea subarea : list) {
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            //创建单元格
            dataRow.createCell(0).setCellValue(subarea.getId());
            dataRow.createCell(1).setCellValue(subarea.getStartnum());
            dataRow.createCell(2).setCellValue(subarea.getEndnum());
            dataRow.createCell(3).setCellValue(subarea.getPosition());
            dataRow.createCell(4).setCellValue(subarea.getRegion().getName());
        }
        //使用输出流下载图片
        ServletOutputStream out = null;
        try {
            String fileName = "分区数据.xls";
            String contentType = ServletActionContext.getServletContext().getMimeType(fileName);
            out = ServletActionContext.getResponse().getOutputStream();
            //设置contenttype
            ServletActionContext.getResponse().setContentType(contentType);
            //获取浏览器类型
            String agent = ServletActionContext.getRequest().getHeader("User-Agent");
            fileName = FileUtils.encodeDownloadFilename(fileName, agent);
            ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename" + fileName);
            //调用poi将文件写回浏览器
            hssfWorkbook.write(out);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //


        return NONE;
    }

    public String listajax() {
        List<Subarea> list = iSubareaService.findListNotAssociation();
        this.java2Json(list, new String[]{"decidedzone", "region"});
        return NONE;
    }
}
