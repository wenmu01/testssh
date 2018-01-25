package com.test.servlet;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class DestroyFile extends HttpServlet
{

	public void destroy()
	{
		
	}

	public void init() throws ServletException
	{
		File file = new File(".");
		
		File[] subFile = file.listFiles();
		
		for(File f : subFile)
		{
			if(f.getName().endsWith("xls"))
			{
				f.delete();
			}
		}
	}

}
