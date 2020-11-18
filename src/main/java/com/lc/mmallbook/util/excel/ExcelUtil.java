package com.lc.mmallbook.util.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * @author lhm
 * @date 2020/8/10 23:48
 */
public class ExcelUtil {


    // 产生要储存的集合
    public static List<User> getUsers() {
        long id = 1000;
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(id);
        user1.setName("liuxing");
        users.add(user1);

        return users;
    }


    /**
     * 把数据写入到Excel文件
     *
     * @param fileName 自动生成的Excel文件的全路径文件名称
     */
    public static void writeExcel(String fileName) throws IOException {


        //第一步，创建一个workbook对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //第二部，在workbook中创建一个sheet对应excel中的sheet
        HSSFSheet sheet = workbook.createSheet("用户表一");
        //第三部，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
        HSSFRow row = sheet.createRow(0);
        //第四步，创建单元格，设置表头
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("用户名");
        cell = row.createCell(1);
        cell.setCellValue("密码");


        //第五步，写入实体数据，实际应用中这些数据从数据库得到,对象封装数据，集合包对象。对象的属性值对应表的每行的值
        List<User>  users = new ArrayList<>();
        User user = new User();
        user.setAge();
        user.setName("liuxing");
        users.add(user1);
        for (int i = 0; i < users.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            User user = users.get(i);
            //创建单元格设值
            row1.createCell(0).setCellValue(user.getId());
            row1.createCell(1).setCellValue(user.getName());


            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
            }
            //将文件保存到指定的位置
            try {
                file.createNewFile();
                workbook.write(file);
                System.out.println("写入成功");
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}

