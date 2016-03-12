package com.lee.Upload.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;

import com.lee.Upload.util.HibernateUtil;

public class BaseDao<T> {

	protected static Session s;
	static{
		s=HibernateUtil.currentSession();
		
	}
	private T t;
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	public List<T> getList(String hql) throws SQLException{		 
		@SuppressWarnings("unchecked")
		List<T>  list = s.createQuery(hql).list();
		return list;
	}
	
}
