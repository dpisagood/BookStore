package cn.iamdp.bookstore.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.iamdp.bookstore.user.javabean.User;
import cn.itcast.jdbc.TxQueryRunner;

/**
 * User持久层
 * @author wl
 *
 */
public class UserDao {
	private QueryRunner qr=new TxQueryRunner();
	
	
	/**
	 * 按用户名查询
	 * @param username
	 * @return
	 */
	public User findByUserName(String username){
		try {
			String sql="select *from tb_user where username=?";
			return qr.query(sql, new BeanHandler<User>(User.class), username);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 按激活码查询
	 * @param code
	 * @return
	 */
	public User findByCode(String code){
		String sql="select *from tb_user where code=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class),code);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 按email查询
	 * @param email
	 * @return
	 */
	public User findByEmail(String email){
		try {
			String sql="select *from tb_user where email=?";
			return qr.query(sql, new BeanHandler<User>(User.class), email);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 注册成功添加用户
	 * @param user
	 */
	public void add(User user){
		try {
			String sql="insert into tb_user values(?,?,?,?,?,?)";
			Object[] params={user.getUid(),user.getUsername(),
							user.getPassword(),user.getEmail(),
							user.getCode(),user.isState()};
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 *	修改指定用户的指定状态
	 * @param uid
	 * @param state
	 */
	public void updateState(String uid,boolean state){
		String sql="update tb_user set state=? where uid=?";
		try {
			qr.update(sql, state, uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
