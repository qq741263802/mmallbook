package com.lc.mmallbook.service;

import com.github.pagehelper.PageInfo;
import com.lc.mmallbook.common.ServerResponse;
import com.lc.mmallbook.pojo.Shipping;

/**
 * @author lhm
 * @date 2020/11/30 22:56
 */
public interface IShippingService {


    ServerResponse add(Integer userId, Shipping shipping);
    ServerResponse<String> del(Integer userId,Integer shippingId);
    ServerResponse update(Integer userId, Shipping shipping);
    ServerResponse<Shipping> select(Integer userId, Integer shippingId);
    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);

}
