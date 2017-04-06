package me.someway.example.ssm.service;

import me.someway.example.ssm.domain.User;
import me.someway.example.ssm.vo.req.user.UserLoginReq;
import me.someway.example.ssm.vo.req.user.UserModifyPwdReq;
import me.someway.example.ssm.base.BaseService;
import me.someway.example.ssm.exception.BusinessException;
import me.someway.example.ssm.vo.req.user.UserAddReq;

/**
 * @author y.ni
 * @version 1.0.0
 */
public interface UserService extends BaseService<User> {
    /**
     *
     * @param req 请求体
     * @throws BusinessException 业务异常
     */
    void addUser(UserAddReq req) throws BusinessException;

    /**
     * 用户登录
     * @param req 请求体
     * @throws BusinessException 业务异常
     */
    User doLogin(UserLoginReq req) throws BusinessException;

    /**
     * 修改密码
     * @param req 请求体
     * @param user 用户
     * @throws BusinessException 业务异常
     */
    void updatePwd(UserModifyPwdReq req, User user) throws BusinessException;
}
