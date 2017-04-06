package me.someway.example.ssm.constant;
/**
 * 提示信息
 * @author y.ni
 * @version 1.0
 */
public class ResultMsg {
	/**
	 * 操作成功
	 */
	public static final String OPERATE_SUCCESS = "操作成功";
	/**
	 * 操作失败
	 */
	public static final String OPERATE_FAIL = "操作失败";
	/**
	 * 更新出错
	 */
	public static final String UPDATE_ERROR = "更新出错";
	/**
	 * 保存出错
	 */
	public static final String SAVE_ERROR = "保存出错";
	/**
	 * 删除出错
	 */
	public static final String DELETE_ERROR = "删除出错";
	/**
	 * 系统异常
	 */
	public static final String SYSTEM_FAIL = "系统异常";
	/**
	 * 登录过期
	 */
	public static final String SESSION_USER_OVERDUE = "用户登录已过期";

	/***********************验证码相关**************************/
	/**
	 * 短信发送间隔时间小于60秒
	 */
	public static final String SMS_LG_ONESECOND = "短信发送间隔时间小于60秒";
	/**
	 * 短信发送失败
	 */
	public static final String SMS_SEND_ERROR = "短信发送失败";

	/**
	 * 验证码错误
	 */
	public static final String VALIDCODE_ERROR = "验证码错误";

	/**
	 * 验证码不存在
	 */
	public static final String VALIDCODE_NOT_EXIST = "验证码不存在";

	/**
	 * 验证码错误次数超过5此，请重新获取
	 */
	public static final String VALIDFAIL_COUNT_LG_FIVE = "验证码错误次数超过5此，请重新获取";

	/**
	 * 验证码类型错误
	 */
	public static final String SMS_NID_ERROR = "发送短信类型错误";
	/**
	 * 手机号码重复
	 */
	public static final String PHONE_REPEAT_ERROR = "手机号码重复";
	/**
	 * 用户名密码错误
	 */
	public static final String USERNAME_PASSWORD_ERROR = "用户名密码错误";
	/**
	 * 用户已冻结，请联系管理员
	 */
	public static final String USER_STATUS_FREEZE = "用户已冻结，请联系管理员";
	/**
	 * 用户信息为空
	 */
	public static final String SESSION_USER_EMPTY = "用户信息为空";

	/**
	 * 用户旧密码错误
	 */
	public static final String USER_PWD_ERROR = "旧密码错误";
	/**
	 * 用户新密码和确认密码不一致
	 */
	public static final String USER_PWD_ATYPISM = "新密码和确认密码不一致";
	/**
	 * 新密码不符合密码强度
	 */
	public static final String USER_PWD_NOSTRENGTH = "新密码不符合密码强度";
	/**
	 * 新密码不能原密码相同
	 */
	public static final String USER_PWD_IDENTICAL = "新密码不能和原密码相同";
	/**
	 * 用户信息不存在
	 */
	public static final String USER_ID_ERROR = "用户信息不存在";
	/**
	 * 用户名已存在
	 */
	public static final String USERNAME_EXISTS = "用户名已存在";
}
