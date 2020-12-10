package com.lc.mmallbook.controller.portal;

import com.github.pagehelper.PageInfo;
import com.lc.mmallbook.common.Const;
import com.lc.mmallbook.common.ResponseCode;
import com.lc.mmallbook.common.ServerResponse;
import com.lc.mmallbook.pojo.Shipping;
import com.lc.mmallbook.pojo.User;
import com.lc.mmallbook.service.IShippingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author lhm
 * @date 2020/11/30 22:55
 *
 */
    @Controller
    @RequestMapping("/shipping/")
    @Api(description = "收货地址管理")
    public class ShippingController {


        @Autowired
        private IShippingService iShippingService;


        @RequestMapping(value = "add.do",method = RequestMethod.POST)
        @ResponseBody
        public ServerResponse add(HttpSession session, Shipping shipping){
            User user = (User)session.getAttribute(Const.CURRENT_USER);
            if(user ==null){
                return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
            }
            return iShippingService.add(user.getId(),shipping);
        }


        @RequestMapping(value = "del.do",method = RequestMethod.POST)
        @ResponseBody
        public ServerResponse del(HttpSession session,Integer shippingId){
            User user = (User)session.getAttribute(Const.CURRENT_USER);
            if(user ==null){
                return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
            }
            return iShippingService.del(user.getId(),shippingId);
        }

        @RequestMapping(value = "update.do",method = RequestMethod.POST)
        @ResponseBody
        public ServerResponse update(HttpSession session,Shipping shipping){
            User user = (User)session.getAttribute(Const.CURRENT_USER);
            if(user ==null){
                return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
            }
            return iShippingService.update(user.getId(),shipping);
        }


        @RequestMapping(value = "select.do",method = RequestMethod.GET)
        @ResponseBody
        public ServerResponse<Shipping> select(HttpSession session,Integer shippingId){
            User user = (User)session.getAttribute(Const.CURRENT_USER);
            if(user ==null){
                return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
            }
            return iShippingService.select(user.getId(),shippingId);
        }


        @RequestMapping(value = "list.do",method = RequestMethod.GET)
        @ResponseBody
        public ServerResponse<PageInfo> list(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                             @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                                             HttpSession session){
            User user = (User)session.getAttribute(Const.CURRENT_USER);
            if(user ==null){
                return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
            }
            return iShippingService.list(user.getId(),pageNum,pageSize);
        }


    }
