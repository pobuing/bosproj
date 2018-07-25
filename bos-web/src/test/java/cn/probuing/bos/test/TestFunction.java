package cn.probuing.bos.test;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/25 15:45
 * @Description:功能单元测试类
 */
public class TestFunction {
    @Test
    public void testParseXLS() throws IOException {
        //创建xls对象
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(new File("/Volumes/program/studyvideo/javaee/【阶段11】物流BOS系统/BOS-day05/BOS-day05/资料/分区导入测试数据.xls")));
        //获取sheet分页
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        //遍历行
        for (Row cells : sheet) {
            System.out.println();
            //遍历行单元格
            for (Cell cell : cells) {
                System.out.print(cell.getStringCellValue());
            }
        }

    }
}
