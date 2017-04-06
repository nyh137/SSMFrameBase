package me.someway.example.ssm.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import me.someway.example.ssm.constant.Constant;
import me.someway.example.ssm.controller.BaseController;
import me.someway.example.ssm.domain.User;
import me.someway.example.ssm.service.UserService;
import me.someway.example.ssm.util.JsonUtil;
import me.someway.example.ssm.vo.req.user.UserLoginReq;
import me.someway.example.ssm.vo.req.user.UserModifyPwdReq;
import me.someway.example.ssm.exception.BusinessException;
import me.someway.example.ssm.vo.req.user.UserAddReq;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * 用户Controller
 * @author y.ni
 * @version 1.0.0
 */
@Controller
@Scope("prototype")
public class UserController extends BaseController {
	@Resource
	private UserService userService;

	/**
	 * 用户注册
	 * @param req
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/user/register", method=RequestMethod.POST)
	@ResponseBody
	public JSON register(@RequestBody @Valid UserAddReq req, HttpSession session) throws Exception{
		//校验短信验证码
		userService.addUser(req);
		return JsonUtil.newJson().toJson();
	}

	/**
	 * 用户登录
	 * @param req
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/user/doLogin", method=RequestMethod.POST)
	@ResponseBody
	public JSON doLogin(@RequestBody @Valid UserLoginReq req, HttpSession session) throws BusinessException {
		User user = userService.doLogin(req);
		session.setAttribute(Constant.SESSION_USER, user);
		User newUser = new User();
		newUser.setId(user.getId());
		newUser.setUsername(user.getUsername());
		return JsonUtil.newJson().addData("data", newUser).toJson();
	}

	/**
	 * 用户登录校验
	 * @return
	 */
	@RequestMapping(value = "/user/loginCheck", method=RequestMethod.POST)
	@ResponseBody
	public JSON check(){
		int status = 1;
		try {

		} catch (Exception e) {
			status = -1;
		}
		Map<String, Object> map = new HashMap<>();
		map.put("status", status);
		return JsonUtil.newJson().addData("data", map).toJson();
	}


	/**
	 * 用户登出
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/user/logout", method=RequestMethod.POST)
	@ResponseBody
	public JSON logout(HttpSession session){
		session.removeAttribute(Constant.SESSION_USER);	
		return JsonUtil.newJson().toJson();
	}

	/**
	 * 修改密码
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/user/security/modifyPwd", method=RequestMethod.POST)
	@ResponseBody
	public JSON modifyPwd(@RequestBody @Valid UserModifyPwdReq req, HttpSession session) {
		User user = getSessionUser(session);
		userService.updatePwd(req, user);
		return JsonUtil.newJson().toJson();
	}
}
