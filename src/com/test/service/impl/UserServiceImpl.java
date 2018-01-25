package com.test.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.test.bean.User;
import com.test.dao.UserDAO;
import com.test.service.UserService;

public class UserServiceImpl implements UserService
{
	private UserDAO userDao;

	public UserDAO getUserDao()
	{
		return userDao;
	}

	public void setUserDao(UserDAO userDao)
	{
		this.userDao = userDao;
	}

	public void delete(User user)
	{
		this.userDao.removeUser(user);
	}

	public List<User> findAll()
	{
		return this.userDao.findAllUsers();
	}

	public User findById(Integer id)
	{
		return this.userDao.findUserById(id);
	}

	public void save(User user)
	{
		this.userDao.saveUser(user);
	}

	public void update(User user)
	{
		this.userDao.updateUser(user);
	}
	
	//����excel�ļ�
	public InputStream getInputStream()
	{
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet1");

		HSSFRow row = sheet.createRow(0);

		HSSFCell cell = row.createCell((short) 0);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue("���");

		cell = row.createCell((short) 1);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue("��");

		cell = row.createCell((short) 2);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue("��");

		cell = row.createCell((short) 3);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue("����");

		List<User> list = this.findAll();

		for (int i = 0; i < list.size(); ++i)
		{
			User user = list.get(i);

			row = sheet.createRow(i + 1);

			cell = row.createCell((short) 0);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(i + 1);

			cell = row.createCell((short) 1);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(user.getFirstname());

			cell = row.createCell((short) 2);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(user.getLastname());

			cell = row.createCell((short) 3);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(user.getAge());
		}
		
		/*
		 * ����ʹ��������ʱ�ļ��ķ�ʽ����excel�ļ���Ȼ��ɾ����ʱ�ļ���������ڷ������쳣ֹͣ��δɾ���ģ���
		 * ��дservlet�ķ�ʽ�ڷ�������ʱɾ���ļ�
		 * 
		 */
		/*
		//����org.apache.commons.lang���µ�RandomStringUtils��randomAlphanumeric��������10�ַ�����������ļ���
		String filename = RandomStringUtils.randomAlphanumeric(10);
		
		filename = new StringBuffer(filename).append(".xls").toString();

		final File file = new File(filename);

		try
		{
			OutputStream os = new FileOutputStream(file);
			wb.write(os);
			os.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		InputStream is = null;
		
		try
		{
			is = new FileInputStream(file);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		//�ں�̨newһ���̣߳������ڲ���ķ�ʽ��ʱɾ����ʱ�ļ�
		new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					Thread.sleep(10000);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				file.delete(); //ɾ����ʱ�ļ�
			}
		}).start();
		
		return is;
	*/
		
		/*
		 * ���·�ʽ��������ʱ�ļ���ֱ�ӽ��ļ��������ֽ�����ķ�ʽ�ķ����ڴ��У�Ȼ����ʾ�ļ�����
		 * 
		 */
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		try
		{
			wb.write(bos);
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		byte[] inputbyte = bos.toByteArray();
		
		InputStream is = new ByteArrayInputStream(inputbyte);
		
		return is;
		
	}

}