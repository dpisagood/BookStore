package cn.iamdp.bookstore.user.service;


import cn.iamdp.bookstore.user.dao.UserDao;
import cn.iamdp.bookstore.user.javabean.User;

/**
 * Userҵ���
 * @author wl
 *
 */
public class UserService {
	private UserDao userdao=new UserDao();
	
	
	/**
	 * ע��У�飬�ɹ�����û�������ӵ����ݿ�
	 * @param form �û��ύ�ı�
	 * @throws UserException
	 */
	public void regist(User form) throws UserException{
		//У���û��������ݲ���ΪʲôҪ����User�أ�����һ��boolean������
		User user =userdao.findByUserName(form.getUsername());
		if(user!=null) throw new UserException("用户名已被注册");
	
		//У������
		 user =userdao.findByEmail(form.getEmail());
		if(user!=null) throw new UserException("Email已被使用");
		
		//�����û����ݵ����ݿ�
		userdao.add(form);
	}
	
	
	/**
	 * �û��������ͨ��������У���Ƿ�������û����׳��쳣�����޸ļ���״̬
	 * @param code  ������
	 * @throws UserException
	 */
	public  void active(String code) throws UserException{
		//ʹ��code��ѯ���ݿ��Ƿ�������û��ļ�¼
		User user=userdao.findByCode(code);
		//�����Ӧ�����������û������ڣ�˵����������Ч
		if(user==null) throw new UserException("��������Ч");
		//У���û�״̬�Ƿ�Ϊδ����״̬������Ѽ��˵���Ƕ��μ���׳��쳣
		if(user.isState()) throw new UserException("���Ѿ���������ظ����");
		//У��ɹ����޸��û�״̬
		userdao.updateState(user.getUid(), true);
	}
	
	/**
	 * ��¼У�飬�����û���Ϣ
	 * @param form
	 * @return
	 * @throws UserException
	 */
	public User  login(User form) throws UserException{
		User user=userdao.findByUserName(form.getUsername());
		if(user==null) throw new UserException("�û���������");
		if(!user.getPassword().equals(form.getPassword())) 
			throw new UserException("�������");
		if(!user.isState()) throw new UserException("����δ����");
		return user;
	}
	
	public boolean ajaxCheck(String username){
		User user=userdao.findByUserName(username);
		if(user==null){
			return false;
		}else{
			return true;
		}
	}
}
