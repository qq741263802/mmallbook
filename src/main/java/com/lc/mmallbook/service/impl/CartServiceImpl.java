package com.lc.mmallbook.service.impl;

import com.lc.mmallbook.common.ServerResponse;
import com.lc.mmallbook.dao.CartMapper;
import com.lc.mmallbook.pojo.Cart;
import com.lc.mmallbook.service.ICartService;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author lhm
 * @date 2020/11/19 0:00
 */
@Service("iCartService")
public class CartServiceImpl implements ICartService {

    private Logger logger= LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private CartMapper cartMapper;




    @Override
    public ServerResponse add(Integer count, Integer productId) {

        if (count==null || productId==null)
        {
            return ServerResponse.createByErrorMessage("参数不能为空");

        }

        Cart cart=new Cart();
        cart.setProductId(productId);
        cart.setQuantity(count);
        cart.setChecked(1);
        cart.setUserId(1);

     int Row= cartMapper.insert(cart);
     if (Row>0)
     {
         return ServerResponse.createBySuccessMessage("新增成功");

     }
        return ServerResponse.createByErrorMessage("新增失败");
    }
}
