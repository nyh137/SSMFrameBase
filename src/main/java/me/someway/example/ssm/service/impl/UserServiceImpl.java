package me.someway.example.ssm.service.impl;

import me.someway.example.ssm.util.ValidUtil;
import me.someway.example.ssm.base.BaseMapper;
import me.someway.example.ssm.base.BaseServiceImpl;
import me.someway.example.ssm.constant.ResultMsg;
import me.someway.example.ssm.domain.User;
import me.someway.example.ssm.exception.BusinessException;
import me.someway.example.ssm.mapper.UserMapper;
import me.someway.example.ssm.service.UserService;
import me.someway.example.ssm.util.MD5Util;
import me.someway.example.ssm.vo.req.user.UserAddReq;
import me.someway.example.ssm.vo.req.user.UserLoginReq;
import me.someway.example.ssm.vo.req.user.UserModifyPwdReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author y.ni
 * @version 1.0.0
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public BaseMapper<User> getMapper() {
        return userMapper;
    }

    @Override
    public void addUser(UserAddReq req) throws BusinessException {
        //校验用户名是否已存在
        HashMap<String, Object> map = new HashMap<>();
        map.put("username",req.getUsername());
        List<User> list = super.findList(map);
        if (null != list && !list.isEmpty()) {
            throw new BusinessException(ResultMsg.PHONE_REPEAT_ERROR);
        }
        User user = new User();
        MD5Util md5 = new MD5Util();
        user.setPassword(md5.getMD5ofStr(req.getPassword()));
        if (userMapper.save(user) != 1) {
            throw new BusinessException(ResultMsg.SAVE_ERROR);
        }
    }

    @Override
    public User doLogin(UserLoginReq req) throws BusinessException {
        HashMap<String, Object> map = new HashMap<>();
        map.clear();
        map.put("username", req.getUsername());
        List<User> list = userMapper.find(map);
        if (null == list || list.isEmpty()) {
            throw new BusinessException(ResultMsg.USERNAME_PASSWORD_ERROR);
        }
        MD5Util md5 = new MD5Util();
        String pwd = md5.getMD5ofStr(req.getPassword());
        User user = list.get(0);
        if (!pwd.equals(user.getPassword())) {
            throw new BusinessException(ResultMsg.USERNAME_PASSWORD_ERROR);
        }
        return user;
    }

    @Override
    public void updatePwd(UserModifyPwdReq req, User user)
            throws BusinessException {
        user = this.findById(user.getId());
        checkUserPwd(req, user);
        // 更新用户密码
        MD5Util md5 = new MD5Util();
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("password", md5.getMD5ofStr(req.getNewPwd()));
        this.update(map);

    }

    /**
     * 校验用户密码请求数据
     * @param req 更改密码请求体
     * @param user 用户类
     */
    private void checkUserPwd(UserModifyPwdReq req, User user) {
        MD5Util md5 = new MD5Util();
        String oldPwd = md5.getMD5ofStr(req.getOldPwd());
        String newPwd = md5.getMD5ofStr(req.getNewPwd());
        if (!oldPwd.equals(user.getPassword())) {
            throw new BusinessException(ResultMsg.USER_PWD_ERROR);
        }
        if (!req.getNewPwd().equals(req.getConfirmPwd())) {
            throw new BusinessException(ResultMsg.USER_PWD_ATYPISM);
        }
        if (!ValidUtil.validLoginPwd(req.getNewPwd())) {
            throw new BusinessException(ResultMsg.USER_PWD_NOSTRENGTH);
        }
        if (newPwd.equals(user.getPassword())) {
            throw new BusinessException(ResultMsg.USER_PWD_IDENTICAL);
        }
    }
}
