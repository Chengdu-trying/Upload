package com.lee.Upload.fliter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lee.Upload.util.HibernateUtil;

public class OpenSessionInViewFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filter) throws IOException, ServletException {

		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			
			filter.doFilter(request, response);//doFilterλ�ò��ܴ�
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			// session.close();
			HibernateUtil.closeSession();
		}

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
