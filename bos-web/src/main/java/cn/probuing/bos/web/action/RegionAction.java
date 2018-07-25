package cn.probuing.bos.web.action;

import cn.probuing.bos.domain.Region;
import cn.probuing.bos.service.IRegionService;
import cn.probuing.bos.utils.PageBean;
import cn.probuing.bos.utils.PinYin4jUtils;
import cn.probuing.bos.web.action.base.BaseAction;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/25 17:20
 * @Description:
 */
@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {
    //上传文件路径
    private File regionFile;
    //分页数据
    private int page;
    private int rows;

    @Autowired
    private IRegionService regionService;

    public String importXls() throws Exception {
        //解析xls文件
        ArrayList<Region> regionArrayList = new ArrayList<>();
        //POI解析Excel文件
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(regionFile));
        HSSFSheet sheet1 = hssfWorkbook.getSheet("Sheet1");
        for (Row row : sheet1) {
            //获得遍历的行数
            int rowNum = row.getRowNum();
            if (rowNum == 0) {
                continue;
            }
            String id = row.getCell(0).getStringCellValue();
            String province = row.getCell(1).getStringCellValue();
            String city = row.getCell(2).getStringCellValue();
            String district = row.getCell(3).getStringCellValue();
            String postcode = row.getCell(4).getStringCellValue();
            //创建区域对象
            Region region = new Region(id, province, city, district, postcode, null, null, null);
            //去掉 最后一个字
            province = province.substring(0, province.length() - 1);
            city = city.substring(0, city.length() - 1);
            district = district.substring(0, district.length() - 1);
            String info = province + city + district;
            //汉字转拼音
            String[] headByString = PinYin4jUtils.getHeadByString(info);
            String shortcode = StringUtils.join(headByString);
            //城市
            String citycode = PinYin4jUtils.hanziToPinyin(city, "");
            region.setShortcode(shortcode);
            region.setCitycode(citycode);
            regionArrayList.add(region);
        }
        //service执行保存
        regionService.saveBatch(regionArrayList);
        return LIST;
    }

    public String pageQuery() throws IOException {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Region.class);
        pageBean.setDetachedCriteria(detachedCriteria);
        regionService.pageQuery(pageBean);
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"currentPage", "detachedCriteria", "pageSize"});
        String json = JSONObject.fromObject(pageBean, config).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().write(json);
        return NONE;

    }

    public File getRegionFile() {
        return regionFile;
    }

    public void setRegionFile(File regionFile) {
        this.regionFile = regionFile;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
