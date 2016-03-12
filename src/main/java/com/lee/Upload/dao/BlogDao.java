package com.lee.Upload.dao;

import com.lee.Upload.entity.Blog;
import com.lee.Upload.util.HibernateUtil;

public class BlogDao extends BaseDao<Blog> {
	
	
	public BlogDao() {
		super();
		this.s=HibernateUtil.currentSession();	
		// TODO Auto-generated constructor stub
	}
	
	
	public int insertBlog(Blog blog){
		int result=(Integer) s.save(blog);
		return result;
	}
	/**
	 * 更新用户头像
	 * @param imageUrl	头像地址
	 * @param user	当前用户
	 * @return	Int
	 */
	public int UpdateBlog(Blog blog){
		//s.saveOrUpdate(user);
		int result=s.createQuery("update Blog b set blogName=? and blogContext=? and date=? where b.blogId=?")
				.setString(0, blog.getBlogName())
				.setString(1, blog.getBlogContext())
				.setDate(2, blog.getDate())
				.setInteger(3, blog.getBlogId())
				.executeUpdate();
		return result;
	}
	public Blog selectById(String blogId){
		Blog blog=(Blog) s.createQuery("from Blog where blogId=?")
				.setInteger(0, Integer.parseInt(blogId)).uniqueResult();

		return blog;
	}
}
