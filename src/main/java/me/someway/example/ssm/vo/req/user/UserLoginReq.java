package me.someway.example.ssm.vo.req.user;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户登录
 * @version 1.0
 */
public class UserLoginReq{
	/**
	 * 用户名
	 */
	@NotBlank(message="用户名不能为空")
	private String username;
	/**
	 * 密码
	 */
	@NotBlank(message="密码不能为空")
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
