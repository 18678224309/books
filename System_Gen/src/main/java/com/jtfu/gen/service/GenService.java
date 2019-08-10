package com.jtfu.gen.service;


import com.jtfu.gen.util.LayUiObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class GenService {
    @Autowired
    DataSource dataSource;

    public LayUiObj getTables(int page,int limit){
        LayUiObj layUiObj=new LayUiObj();
        Connection conn=null;
        String sql="select table_name,table_comment from information_schema.tables where table_schema='books' limit "+(page-1)*limit+","+limit;
        String sql1="select count(1) from information_schema.tables where table_schema='books'";
        try {
            conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Map map=new HashMap();
                map.put("tablename",rs.getString(1));
                map.put("tablecomment",rs.getString(2));
                layUiObj.getData().add(map);
            }
            rs=ps.executeQuery(sql1);
            rs.next();
            layUiObj.setCount(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return layUiObj;
    }
}
