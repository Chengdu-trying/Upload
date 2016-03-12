package com.lee.Upload.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.lee.Upload.dao.BlogDao;
import com.lee.Upload.entity.Blog;
import com.lee.Upload.entity.User;


public class BlogAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String blog_title;
	private String allhtml;
	private String alltext;
	private String formatText;
	
	public String newBlogPage(){
		response.setContentType("text/html;charset=utf-8");
		Blog blog=new Blog();
		PrintWriter out=null;
		try {
			out=response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(blog_title);
		System.out.println(allhtml);
		blog.setUser((User) session.getAttribute("user"));
		blog.setBlogName(blog_title);
		blog.setBlogContext(allhtml);
		blog.setDate(new Date());
		BlogDao dao=new BlogDao();
		if(dao.insertBlog(blog)>0){
			out.println("新建博客成功！");
		}else{
			
			out.println("新建博客失败！");
		}
		out.flush();
		out.close();
		return SUCCESS;
	}
	
	public String getBlogList() throws SQLException, JsonGenerationException, JsonMappingException, IOException{
		response.setContentType("text/html;charset=utf-8");
		Blog blog=new Blog();
		PrintWriter out=null;
		try {
			out=response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BlogDao dao=new BlogDao();
		ObjectMapper mapper=new ObjectMapper();
		List<Blog> blogs=dao.getList("from Blog");
		session.setAttribute("blogs", blogs);
		String json=mapper.writeValueAsString(blogs);
		out.println(json);
		out.flush();
		out.close();
		return SUCCESS;
	}
	public String postBlogContext() throws JsonGenerationException, JsonMappingException, IOException{
		response.setContentType("text/html;charset=utf-8");
		String blogid=request.getParameter("blogId");
		BlogDao dao=new BlogDao();
		Blog blog=dao.selectById(blogid);
		request.setAttribute("blog", blog);
		
		return "GetSuccess";
	}

	public String getBlog_title(){
		return blog_title;
	}

	public void setBlog_title(String blog_title){
		this.blog_title = blog_title;
	}

	public String getAllhtml() {
		return allhtml;
	}

	public void setAllhtml(String allhtml) throws UnsupportedEncodingException {
		this.allhtml = allhtml;
	}

	public String getAlltext() {
		return alltext;
	}

	public void setAlltext(String alltext) throws UnsupportedEncodingException {
		this.alltext = alltext;
	}

	public String getFormatText() {
		return formatText;
	}

	public void setFormatText(String formatText){
		this.formatText = formatText;
	}
	
}
