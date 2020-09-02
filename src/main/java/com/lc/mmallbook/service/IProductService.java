package com.lc.mmallbook.service;

import com.github.pagehelper.PageInfo;
import com.lc.mmallbook.common.ServerResponse;
import com.lc.mmallbook.pojo.Product;
import com.lc.mmallbook.vo.ProductDetailVo;
import com.lc.mmallbook.vo.ProductListVo;


/**
 * @author lhm
 * @date 2020/8/30 23:34
 */
public interface IProductService {

    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse  setSaleStatus(Integer productId,Integer status);

    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);


    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);


}

