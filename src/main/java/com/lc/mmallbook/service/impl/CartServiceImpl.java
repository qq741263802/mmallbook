package com.lc.mmallbook.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.lc.mmallbook.common.Const;
import com.lc.mmallbook.common.ResponseCode;
import com.lc.mmallbook.common.ServerResponse;
import com.lc.mmallbook.dao.CartMapper;
import com.lc.mmallbook.dao.ProductMapper;
import com.lc.mmallbook.pojo.Cart;
import com.lc.mmallbook.pojo.Product;
import com.lc.mmallbook.service.ICartService;
import com.lc.mmallbook.util.BigDecimalUtil;
import com.lc.mmallbook.util.PropertiesUtil;
import com.lc.mmallbook.vo.CartProductVo;
import com.lc.mmallbook.vo.CartVo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author lhm
 * @date 2020/11/19 0:00
 */
@Service("iCartService")
public class CartServiceImpl implements ICartService {

    private Logger logger= LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;






    @Override
    public ServerResponse<CartVo> add(Integer userId, Integer count, Integer productId) {

        if (count == null || productId == null) {

            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());

        }
        Product product=productMapper.selectByPrimaryKey(productId);
        if (product==null)
        {


            return ServerResponse.createByErrorMessage("商品id不存在");
        }

        Cart cart = cartMapper.selectCartByUserIdProductId(userId, productId);
        if (cart == null) {

            Cart cartItem = new Cart();
            cartItem.setProductId(productId);
            cartItem.setQuantity(count);
            cartItem.setChecked(Const.Cart.CHECKED);
            cartItem.setUserId(userId);
            cartMapper.insert(cartItem);

        } else {
            //这个产品已经在购物车里了.
            //如果产品已存在,数量相加
            count = cart.getQuantity() + count;
            cart.setQuantity(count);
            cartMapper.updateByPrimaryKeySelective(cart);
        }

        return this.list(userId);

    }

    public ServerResponse<CartVo> update(Integer userId,Integer productId,Integer count){
        if(productId == null || count == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Cart cart = cartMapper.selectCartByUserIdProductId(userId,productId);
        if(cart != null){
            cart.setQuantity(count);
        }
        cartMapper.updateByPrimaryKey(cart);
        return this.list(userId);
    }

    public ServerResponse<CartVo> deleteProduct(Integer userId,String productIds){
        List<String> productList = Splitter.on(",").splitToList(productIds);
        if(CollectionUtils.isEmpty(productList)){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        cartMapper.deleteByUserIdProductIds(userId,productList);
        return this.list(userId);
    }


    public ServerResponse<CartVo> list (Integer userId){
        CartVo cartVo = this.getCartVoLimit(userId);
        return ServerResponse.createBySuccess(cartVo);
    }



    public ServerResponse<CartVo> selectOrUnSelect (Integer userId,Integer productId,Integer checked){
        cartMapper.checkedOrUncheckedProduct(userId,productId,checked);
        return this.list(userId);
    }

    public ServerResponse<Integer> getCartProductCount(Integer userId){
        if(userId == null){
            return ServerResponse.createBySuccess(0);
        }
        return ServerResponse.createBySuccess(cartMapper.selectCartProductCount(userId));
    }



    private CartVo getCartVoLimit(Integer userId){
            CartVo cartVo = new CartVo();
            List<Cart> cartList = cartMapper.selectCartByUserId(userId);
            List<CartProductVo> cartProductVoList = Lists.newArrayList();

            BigDecimal cartTotalPrice = new BigDecimal("0");

            if(CollectionUtils.isNotEmpty(cartList)){
                for(Cart cartItem : cartList){
                    CartProductVo cartProductVo = new CartProductVo();
                    cartProductVo.setId(cartItem.getId());
                    cartProductVo.setUserId(userId);
                    cartProductVo.setProductId(cartItem.getProductId());

                    Product product = productMapper.selectByPrimaryKey(cartItem.getProductId());
                    if(product != null){
                        cartProductVo.setProductMainImage(product.getMainImage());
                        cartProductVo.setProductName(product.getName());
                        cartProductVo.setProductSubtitle(product.getSubtitle());
                        cartProductVo.setProductStatus(product.getStatus());
                        cartProductVo.setProductPrice(product.getPrice());
                        cartProductVo.setProductStock(product.getStock());
                        //判断库存
                        int buyLimitCount = 0;
                        if(product.getStock() >= cartItem.getQuantity()){
                            //库存充足的时候
                            buyLimitCount = cartItem.getQuantity();
                            cartProductVo.setLimitQuantity(Const.Cart.LIMIT_NUM_SUCCESS);
                        }else{
                            buyLimitCount = product.getStock();
                            cartProductVo.setLimitQuantity(Const.Cart.LIMIT_NUM_FAIL);
                            //购物车中更新有效库存
                            Cart cartForQuantity = new Cart();
                            cartForQuantity.setId(cartItem.getId());
                            cartForQuantity.setQuantity(buyLimitCount);
                            cartMapper.updateByPrimaryKeySelective(cartForQuantity);
                        }
                        cartProductVo.setQuantity(buyLimitCount);
                        //计算总价
                        cartProductVo.setProductTotalPrice(BigDecimalUtil.mul(product.getPrice().doubleValue(),cartProductVo.getQuantity()));
                        cartProductVo.setProductChecked(cartItem.getChecked());
                    }

                    if(cartItem.getChecked() == Const.Cart.CHECKED){
                        //如果已经勾选,增加到整个的购物车总价中
                        cartTotalPrice = BigDecimalUtil.add(cartTotalPrice.doubleValue(),cartProductVo.getProductTotalPrice().doubleValue());
                    }
                    cartProductVoList.add(cartProductVo);
                }
            }
            cartVo.setCartTotalPrice(cartTotalPrice);
            cartVo.setCartProductVoList(cartProductVoList);
            cartVo.setAllChecked(this.getAllCheckedStatus(userId));
            cartVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix"));

            return cartVo;
        }


        private boolean getAllCheckedStatus(Integer userId)
        {
            if(userId == null){
                return false;
            }
            return cartMapper.selectCartProductCheckedStatusByUserId(userId) == 0;

        }


}
