package me.someway.example.ssm.controller;

import me.someway.example.ssm.constant.Constant;
import me.someway.example.ssm.constant.ResultMsg;
import me.someway.example.ssm.domain.User;
import me.someway.example.ssm.exception.BusinessException;
import me.someway.example.ssm.exception.ParamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

public class BaseController {
	private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);
	/**
	 * 获取用户
	 */
	public User getSessionUser(HttpSession session){
		Object obj = session.getAttribute(Constant.SESSION_USER);
		if (null == obj) {
			throw new BusinessException(ResultMsg.SESSION_USER_OVERDUE);
		}
		return (User) obj;
	}

	/**
	 * 校验验证码是否正确
	 * @param validCode
	 * @param session
	 * @return
	 * @throws ParamException
	 */
	public boolean checkValidCode(String validCode, HttpSession session) throws ParamException {
		Object sessionValidCode = session.getAttribute(Constant.VALID_CODE);
		Object count =  session.getAttribute(Constant.VALID_ERROR_COUNT);
		if(sessionValidCode == null || count == null){
			throw new ParamException(ResultMsg.VALIDCODE_NOT_EXIST);
		}
		
		Integer errorCount = Integer.parseInt(count+"");
		if(errorCount >5){
			//验证码错误5此需要重新获取，图形验证码
			session.removeAttribute(Constant.VALID_CODE);
			throw new BusinessException(ResultMsg.VALIDFAIL_COUNT_LG_FIVE);
		}
		
		if(validCode.equals(sessionValidCode+"")){
			session.removeAttribute(Constant.VALID_CODE);
			return true;
		}else{
			session.setAttribute(Constant.VALID_ERROR_COUNT, errorCount+1);
		}
		
		throw new ParamException(ResultMsg.VALIDCODE_ERROR);
	}
	
}
