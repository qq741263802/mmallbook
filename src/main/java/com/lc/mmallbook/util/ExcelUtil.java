package com.lc.mmallbook.util;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lhm
 * @date 2020/8/10 23:48
 */
public class ExcelUtil {

    public static List<Map<String, String>> readExcel(String filepath) {
        List<Map<String, String>> sheetList = new ArrayList<Map<String, String>>();
        try {
            //获取文件类型
            String fileType = filepath.substring(filepath.lastIndexOf(".") + 1, filepath.length());
            InputStream is = new FileInputStream(new File(filepath));
            Workbook wb = null;
            if (fileType.endsWith("xlsx")) {
                //Excel 2007
                wb = new XSSFWorkbook(is);
            } else if (fileType.endsWith("xls")) {
                //Excel 2003
                wb = new HSSFWorkbook(is);
            } else {
                throw new Exception("读取的不是excel文件");
            }
            //获取Excel的第一个sheet页面数据
            Sheet sheet = wb.getSheetAt(0);

            List<String> titles = new ArrayList<String>();
            //当前页面所有行的总数, 去掉+1为当前工作表的最后一行的行号
            int rowSize = sheet.getLastRowNum() + 1;
            //此时j为控制从第几行开始读取数据（避免前几行为表名等字段）
            for (int j = 1; j < rowSize-1; j++) {
                //读取Excel中第 j 行
                Row row = sheet.getRow(j);
                if (row == null) {
                    continue;
                }
                //是获取最后一个不为空的列是第几个,比最后一列列标大1
                int cellSize = row.getLastCellNum();
                //此处比较的值需与j的初始值一致
                if (j == 1) {
                    for (int k = 0; k < cellSize; k++) {
                        //获取第一行单元格数据，即对应的表头(数据库字段)
                        Cell cell = row.getCell(k);
                        titles.add(cell.toString());
                    }
                } else {
                    Map<String, String> rowMap = new HashMap<String, String>();
                    //获取每一行表头字段对应的值,此k为第几列
                    for (int k = 1; k < titles.size(); k++) {
                        Cell cell = row.getCell(k);
                        String key = titles.get(k);
                        String value = null;
                        if (cell != null) {
                            value = cell.toString();
                        }
                        //将字段名与值组成键值对存到map中
                        rowMap.put(key, value);
                    }
                    sheetList.add(rowMap);
                }
            }
            //释放资源
            wb.close();
            is.close();
            titles.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sheetList;
    }
}
