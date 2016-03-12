package com.lee.Upload.dao;

import com.lee.Upload.entity.User;
import com.lee.Upload.util.HibernateUtil;

public class UserDao extends BaseDao<User> {
	
	
	public UserDao() {
		super();
		this.s=HibernateUtil.currentSession();	
		// TODO Auto-generated constructor stub
	}
	/**
	 * 通过姓名查询
	 * @param hql
	 * @param str 姓名
	 * @return user对象
	 */
	public User getObjByName(String hql,String str) {
		User user=(User) s.createQuery(hql).setString(0, str).uniqueResult();
		
		return user;
	}
	public User getObjByLogin(String email,String pwd){
		User user=(User) s.createQuery("from User where userEmail=? and userPwd=?").setString(0, email).setString(1, pwd).uniqueResult();
		return user;
	}
	
	public int insertUser(User user){
		int result=(Integer) s.save(user);
		return result;
	}
	/**
	 * 更新用户头像
	 * @param imageUrl	头像地址
	 * @param user	当前用户
	 * @return	Int
	 */
	public int UpdateUserImage(User user){
		//s.saveOrUpdate(user);
		int result=s.createQuery("update User u set userImage=? where u.userId=?")
				.setString(0, user.getUserImage())
				.setInteger(1, user.getUserId())
				.executeUpdate();
		return result;
	}
}
