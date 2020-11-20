package com.lc.mmallbook.util.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
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
    public static void writeExcel(String fileName,User user) throws IOException {


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
        users.add(user);
        for (int i = 0; i < users.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            //创建单元格设值
            row1.createCell(0).setCellValue(user.getAge());
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




    /**
     * 读取txt文件的内容
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static String readTxt(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);

            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }


    /**使用FileOutputStream来写入txt文件
     * @param txtPath txt文件路径
     * @param content 需要写入的文本
     */
    public static void writeTxt(String txtPath,String content){
        FileOutputStream fileOutputStream = null;
        File file = new File(txtPath);
        try {
            if(file.exists()){
                //判断文件是否存在，如果不存在就新建一个txt
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
         writeTxt("D:/errlog.txt","测试");


    }


}

