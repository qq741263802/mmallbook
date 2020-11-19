package com.lc.mmallbook.service;

import com.lc.mmallbook.common.ServerResponse;



/**
 * @author lhm
 * @date 2020/11/18 23:58
 */

public interface ICartService {


    ServerResponse add(Integer count, Integer productId);

}
