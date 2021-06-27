package com.lc.mmallbook.controller.portal;

import com.lc.mmallbook.common.Const;
import com.lc.mmallbook.common.ServerResponse;
import com.lc.mmallbook.pojo.User;
import com.lc.mmallbook.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;


/**
 * @author lhm
 * @date 2020/6/21 23:50
 */
@Controller
@RequestMapping("/user/")
@Api(description = "前台用户管理")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value= "login.do",method = RequestMethod.POST )
    @ResponseBody
    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "username", value = "登录名", paramType = "query", required = true, dataType = "String"),
    @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse<User> login(String username, String password, HttpSession session)
            {
                ServerResponse<User> response =iUserService.login(username,password);
                if (response.isSuccess())
                {
                    session.setAttribute(Const.CURRENT_USER,response.getData());

                }

                return response;

            }

    @RequestMapping(value= "logout.do",method = RequestMethod.POST )
    @ResponseBody
            public ServerResponse<String> logout(HttpSession session)
            {

                session.removeAttribute(Const.CURRENT_USER);
                return ServerResponse.createBySuccess();

            }


    @RequestMapping(value= "register.do",method = RequestMethod.POST )
    @ResponseBody
    public ServerResponse<String> register(@RequestBody User user)

    {

        return iUserService.register(user);

    }

    @RequestMapping(value= "check_valid.do",method = RequestMethod.POST )
    @ResponseBody
    public ServerResponse<String> checkValid(String string,String type)
    {
        return iUserService.checkValid(string,type);


    }


    @RequestMapping(value = "get_user_info.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user != null){
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
    }


    @RequestMapping(value = "forget_get_question.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username){
        return iUserService.selectQuestion(username);
    }


    @RequestMapping(value = "forget_check_answer.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username,String question,String answer){
        return iUserService.checkAnswer(username,question,answer);
    }


    @RequestMapping(value = "forget_reset_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String username,String passwordNew,String forgetToken){
        return iUserService.forgetResetPassword(username,passwordNew,forgetToken);
    }



}
