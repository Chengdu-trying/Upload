package com.lee.Upload;

import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       //测试github分线上传
    	Object girl=new Nvpengyou("女神");
    	System.out.println(((Nvpengyou) girl).getName());
    	
    }
    
    public static class Nvpengyou{
    	
    	public Nvpengyou() {
			super();
			// TODO Auto-generated constructor stub
		}

		private String name;

		public Nvpengyou(String name) {
			super();
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
    }
}

