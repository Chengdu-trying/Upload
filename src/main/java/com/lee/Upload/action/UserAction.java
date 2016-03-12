package com.lee.Upload.action;


import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.dialect.function.VarArgsSQLFunction;

import com.lee.Upload.dao.UserDao;
import com.lee.Upload.entity.User;
import com.lee.Upload.util.ImageHelper;
import com.lee.Upload.util.UploadConfigurationRead;
import com.opensymphony.xwork2.Action;
/*
 * 1.接收表单的值
 * 	a.属性传值
 * 	b.对象传值
 * 	c.模型传值
 */
public class UserAction extends BaseAction{
	private static final long serialVersionUID = 5457268965108906071L;
	
	private String name;	//登录用户
	private String pwd;		//登录密码
	private String x;		//图片剪切需求参数
	private String y;
	private String w;
	private String h;
	private String realW;	//图片实际尺寸
	private String realH;
	/*
	 * 不在方法里面设置参数，是为了跟Servlet划清界限，解耦和是其替代方式
	 */
	public String doLogin(){
		System.out.println(name+"//"+pwd);
		UserDao dao=new UserDao();
		User u=dao.getObjByLogin(name,pwd);
		if(u!=null){			
			session.setAttribute("user", u);
			System.out.println("Success/n用户名:"+u.getUserName());
			return Action.SUCCESS;
		}else{
			session.setAttribute("msg", "登陆失败");
			System.out.println("fail");
			return Action.LOGIN;
		}
	}
	/**
	 * 保存头像
	 * @return
	 * @throws IOException	流未找到
	 */
	public String SaveImage() throws IOException{
		File file=(File) session.getAttribute("fileImage");	//得到源文件
		
		Double fileX=ImageIO.read(file).getWidth()/Double.parseDouble(realW);	//缩放比例X轴
		Double fileY=ImageIO.read(file).getHeight()/Double.parseDouble(realH);	//缩放比例Y轴
		
		int newX=(int) (Double.parseDouble(x)*fileX);
		int newY=(int) (Double.parseDouble(y)*fileY);
		int newW=(int) (Double.parseDouble(w)*fileX);
		int newH=(int) (Double.parseDouble(h)*fileY);		//通过缩放比例计算实际剪切尺寸
		
		UserDao dao=new UserDao();
		User user=(User) session.getAttribute("user");		//得到用户对象
		
		String src=UploadConfigurationRead.getInstance()
				.getConfigItem("uploadFilePath").trim()
				+"/"
				+(String)session.getAttribute("realName");		//剪切文件存放位置
		user.setUserImage(src);		//头像文件名称
	    String type=(String) session.getAttribute("type");		//文件后缀，根据不同图片格式调用不同图片文件流
	    //调用工具类剪切图片
		ImageHelper.cutImage(file.getAbsolutePath(),file.getAbsolutePath(),newX,newY,newW,newH,type);
		//向数据库中存入数据
		if (dao.UpdateUserImage(user)>0) {			
			return "SaveSUCCESS";
		}
		return Action.ERROR;
	}
	/**
	 * 得到用户json数据
	 * @return
	 */
	public String getUserObj(){
		ObjectMapper mapper=new ObjectMapper();
		User user=(User) session.getAttribute("user");
		response.setContentType("text/html;charset=utf-8");
		try {
			String json=mapper.writeValueAsString(user);
			System.out.println(json);
			response.getWriter().print(json);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.NONE;
	}
	
	public String getUserJson(){
		User user=(User) session.getAttribute("user");
		System.out.println(user.getUserEmail());
		return Action.SUCCESS;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getW() {
		return w;
	}
	public void setW(String w) {
		this.w = w;
	}
	public String getH() {
		return h;
	}
	public void setH(String h) {
		this.h = h;
	}
	public String getRealW() {
		return realW;
	}
	public void setRealW(String realW) {
		this.realW = realW;
	}
	public String getRealH() {
		return realH;
	}
	public void setRealH(String realH) {
		this.realH = realH;
	}
}
