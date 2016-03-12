package com.lee.Upload.util;


import java.lang.reflect.InvocationTargetException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class HibernateUtil {
	
	private static final ThreadLocal<Session> sessionTL = new ThreadLocal<Session>();
	private static Configuration configuration;
	private static ServiceRegistry serviceRegistry;
	
	private final static SessionFactory sessionFactory;
	static {
		try {
			configuration = new Configuration();
			configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(
		            configuration.getProperties()). buildServiceRegistry();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
		       
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static Session currentSession(){
		Session session = sessionTL.get();
		if (session==null) {
			session = sessionFactory.openSession();
			sessionTL.set(session);
		}
		return session;
		
	}
	
	public static void closeSession(){
		
		Session session = sessionTL.get();
		sessionTL.set(null);
		session.close();
	}
	
}
