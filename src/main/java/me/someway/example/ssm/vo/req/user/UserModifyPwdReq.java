package me.someway.example.ssm.vo.req.user;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 更改密码
 * @version v1.0
 */
public class UserModifyPwdReq {
	
	/**
	 * 旧密码
	 */
	@NotBlank(message="旧密码不能为空")
	private String oldPwd;
	/**
	 * 新密码
	 */
	@Pattern(regexp="[A-Za-z0-9~!@#$%^&*.]{6,22}",message="密码必须由字母或数字或~!@#$%^&*.特殊字符组成的6到22位")
	@NotBlank(message="新密码不能为空")
	private String newPwd;
	/**
	 * 确认密码
	 */
	@Pattern(regexp="[A-Za-z0-9~!@#$%^&*.]{6,22}",message="密码必须由字母或数字或~!@#$%^&*.特殊字符组成的6到22位")
	@NotBlank(message="确认新密码不能为空")
	private String confirmPwd;
	/**
	 * @return the oldPwd
	 */
	public String getOldPwd() {
		return oldPwd;
	}
	/**
	 * @param oldPwd the oldPwd to set
	 */
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	/**
	 * @return the newPwd
	 */
	public String getNewPwd() {
		return newPwd;
	}
	/**
	 * @param newPwd the newPwd to set
	 */
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	/**
	 * @return the confirmPwd
	 */
	public String getConfirmPwd() {
		return confirmPwd;
	}
	/**
	 * @param confirmPwd the confirmPwd to set
	 */
	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

}
