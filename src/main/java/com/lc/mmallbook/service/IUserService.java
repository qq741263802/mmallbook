package com.lc.mmallbook.service;

import com.lc.mmallbook.common.ServerResponse;
import com.lc.mmallbook.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author lhm
 * @date 2020/7/3 23:29
 */

public interface IUserService {

    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String string, String type);

    ServerResponse selectQuestion(String username);

    ServerResponse<String> checkAnswer(String username, String question, String answer);

    ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken);

    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

    ServerResponse<User> updateInformation(User user);

    ServerResponse<User> getInformation(Integer userId);

    ServerResponse checkAdminRole(User user);


}
