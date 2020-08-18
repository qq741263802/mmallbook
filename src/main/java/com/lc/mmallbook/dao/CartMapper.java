package com.lc.mmallbook.dao;

import com.lc.mmallbook.pojo.Cart;
import com.lc.mmallbook.pojo.Category;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);


}