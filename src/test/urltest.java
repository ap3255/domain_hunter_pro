package test;

import java.net.*;

import burp.IHttpRequestResponse;

import java.io.*;
 
public class urltest
{
   public static void main(String [] args)
   {
	   System.out.println(getFullUrlStringWithoutDefaultPort());
   }
   public void test() {
	      try
	      {
	         URL url = new URL("https://www.runoob.com/index.html?language=cn#j2se");
	         URL url1 = new URL("https://www.runoob.com:443/index.html?language=cn#j2se");
	         System.out.println(url.equals(url1));
	         System.out.println("URL 为：" + url.toString());
	         System.out.println("协议为：" + url.getProtocol());
	         System.out.println("验证信息：" + url.getAuthority());
	         System.out.println("文件名及请求参数：" + url.getFile());
	         System.out.println("主机名：" + url.getHost());
	         System.out.println("路径：" + url.getPath());
	         System.out.println("端口：" + url.getPort());
	         System.out.println("默认端口：" + url.getDefaultPort());
	         System.out.println("请求参数：" + url.getQuery());
	         System.out.println("定位位置：" + url.getRef());
	      }catch(IOException e)
	      {
	         e.printStackTrace();
	      }
   }
   
	public static String getFullUrlStringWithoutDefaultPort() {
		URL fullUrl;
		try {
			fullUrl = new URL("https://www.runoob.com:443/index.html?language=cn#j2se");
			System.out.println(fullUrl.toString());
			if (fullUrl == null) {
				return null;
			}else {
				String protocol = fullUrl.getProtocol();
				String host = fullUrl.getHost();
				String file = fullUrl.getFile();
				try {
					return new URL(protocol,host,file).toString();
				} catch (MalformedURLException e) {
					e.printStackTrace();
					return null;
				}
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}

	}
}