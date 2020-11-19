package com.lc.mmallbook.controller.portal;

import com.lc.mmallbook.common.Const;
import com.lc.mmallbook.common.ServerResponse;
import com.lc.mmallbook.pojo.User;
import com.lc.mmallbook.service.ICartService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpSession;

/**
 * @author lhm
 * @date 2020/11/18 23:51
 */
public class CartController {

    private ICartService iCartService;

      public ServerResponse add(HttpSession session, Integer count,Integer productId)
      {

          User user = (User)session.getAttribute(Const.CURRENT_USER);

          return iCartService.add(count,productId);



      }


}
