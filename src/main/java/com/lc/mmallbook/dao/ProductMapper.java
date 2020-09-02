package com.lc.mmallbook.dao;

import com.lc.mmallbook.pojo.Product;
import com.lc.mmallbook.vo.ProductListVo;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> selectList();
}