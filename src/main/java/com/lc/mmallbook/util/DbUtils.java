package com.lc.mmallbook.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 /**
 * @author lhm
 * @date 2020/7/28 23:21
 */
public class DbUtils {

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String username = "root";
    private String password = "";
    private String url="jdbc:mysql://localhost:3306/mmall";
    /**
     * @描述:用来在类中最先执行数据库的驱动加载
     *
     */
    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println("找不到类，请检查驱动包是否导入");
            e.printStackTrace();
        }
    }
    /**
     * @描述：建立数据库连接并返回给开发者连接对象
     * @参数：@return
     * @返回值：Connection
     */
    public Connection getConn(){
        try{
            conn = DriverManager.getConnection(url,username,password);
            return conn;
        }catch(Exception e){
            System.out.println("连接不上数据库，请检查连接地址（url）,账号（username）,密码（password）");
            e.printStackTrace();
            return null;
        }
    }
    /**
     * @描述：执行更新数据库的sql语句
     * @参数：@param sql
     * @参数：@return
     * @返回值：int  = 影响表中记录行数  更新成功 = >0的整数  更新失败 = 0
     */
    public int updata(String sql){
        try{
            stmt = getConn().createStatement();
            return stmt.executeUpdate(sql);

        }catch(SQLException e){
            System.out.println("更新失败，请检查sql语法 及格式");
            e.printStackTrace();
            return -1;
        }finally{
            close();
        }
    }

    /**
     * @描述：执行查询数据库的SQL语句
     * @参数：@param sql
     * @参数：@return
     * @返回值：ArrayList<Map<String,Object>>
     */
    public ArrayList<Map<String,Object>> find (String sql){
        ArrayList<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        try{
            stmt = this.getConn().createStatement();
            rs = stmt.executeQuery(sql);
            //获得结构化的结果集对象包含表信息
            ResultSetMetaData rsmd =  rs.getMetaData();
            //获取我所查询的表的总列数
            int columnCount = rsmd.getColumnCount();
            while(rs.next()){
                Map<String,Object> map = new HashMap<String,Object>();
                for(int i = 1; i<=columnCount; i++){
                    String columnName = rsmd.getColumnName(i);
                    Object columnValue = rs.getObject(columnName);
                    map.put(columnName, columnValue);
                }
                list.add(map);
            }
            return list;

        } catch (Exception e) {
            System.out.println("查询失败，请检查sql语法 及格式");
            e.printStackTrace();
            return null;
        }finally{
            close();
        }

    }
    /**
     * @描述：释放连接时内存
     * @参数：无
     * @返回值：void
     */
    public void close(){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
