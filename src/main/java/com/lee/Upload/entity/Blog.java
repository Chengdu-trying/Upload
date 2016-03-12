package com.lee.Upload.entity;

import java.util.Date;

import javax.persistence.*;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.lee.Upload.util.CustomDateSerializer;

@Entity
@Table(name="blog_upload")
public class Blog {
	@Id
	@Column(name="blog_id")
	@GeneratedValue(strategy = GenerationType.AUTO)	//针对不同数据库
	private int blogId;
	@Column(name="blog_Name")
	private String blogName;
	@Column(name="blog_Context")
	@Lob
	private String blogContext;
	@Column(name="date")
	@Temporal(TemporalType.DATE)
	private Date date;
	@ManyToOne(cascade={CascadeType.ALL})
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name="user_id")
	private User user;
	
	public Blog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Blog(int blogId, String blogName, String blogContext, User user) {
		super();
		this.blogId = blogId;
		this.blogName = blogName;
		this.blogContext = blogContext;
		this.user = user;
	}
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getBlogName() {
		return blogName;
	}
	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}
	public String getBlogContext() {
		return blogContext;
	}
	public void setBlogContext(String blogContext) {
		this.blogContext = blogContext;
	}
	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
