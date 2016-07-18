package cn.iamdp.bookstore.user.javabean;
/**
 * User的领域对象
 * @author wl
 *
 */
public class User {
	//成员变量对应数据库中的字段
	private String uid;//主键
	private String username;//用户名
	private String password;//密码
	private String email;//邮箱
	private String code;//激活码
	private boolean state;//状态（已激活和未激活）
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
}
