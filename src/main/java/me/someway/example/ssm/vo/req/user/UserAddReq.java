package me.someway.example.ssm.vo.req.user;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 注册用户
 * @version 1.0
 */
public class UserAddReq {
	/**
	 * 密码
	 */
    @Pattern(regexp="[A-Za-z0-9~!@#$%^&*.]{6,22}",message="密码必须由字母或数字或~!@#$%^&*.特殊字符组成的6到22位")
	private String password;
	/**
	 * 用户名
	 */
	@NotBlank
	private String username;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
