package com.lc.mmallbook.service;

import com.lc.mmallbook.common.ServerResponse;
import com.lc.mmallbook.vo.CartVo;


/**
 * @author lhm
 * @date 2020/11/18 23:58
 */

public interface ICartService {

    ServerResponse<CartVo> add(Integer userId, Integer count, Integer productId);
    ServerResponse<CartVo> update(Integer userId,Integer productId,Integer count);
    ServerResponse<CartVo> deleteProduct(Integer userId, String productIds);

    ServerResponse<CartVo> list (Integer userId);
    ServerResponse<CartVo> selectOrUnSelect (Integer userId,Integer productId,Integer checked);
    ServerResponse<Integer> getCartProductCount(Integer userId);
}
