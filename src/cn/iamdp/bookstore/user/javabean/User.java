package cn.iamdp.bookstore.user.javabean;
/**
 * User���������
 * @author wl
 *
 */
public class User {
	//��Ա������Ӧ���ݿ��е��ֶ�
	private String uid;//����
	private String username;//�û���
	private String password;//����
	private String email;//����
	private String code;//������
	private boolean state;//״̬���Ѽ����δ���
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
