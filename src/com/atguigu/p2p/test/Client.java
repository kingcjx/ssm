package com.atguigu.p2p.test;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.atguigu.p2p.dao.ICustomerDAO;
import com.atguigu.p2p.vo.CustomerModel;
import com.atguigu.p2p.vo.CustomerQueryModel;


@Service
public class Client 
{
	//@Autowired	//按照类型装配,有它的话，applicationContext.xml文件里不需要再配置<property name="userDao" ref="userDao"/>
					//java类里面也不再需要配置setter方法
					//applicationContext.xml配置文件里面无property属性+类里面无setter方法le
	
	@Resource		//按照名称装配，找不到名称后再按照类型装配
	private ICustomerDAO dao = null;
	
	public static void main(String[] args) 
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Client t = (Client)ctx.getBean("client");
		
		int i = 66;
		CustomerModel cm = new CustomerModel();
		cm.setCustomerId("cid"+RandomUtils.nextInt());
		cm.setPwd(UUID.randomUUID().toString().substring(26));
		cm.setRegisterTime(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		cm.setShowName("CA:"+i);
		cm.setTrueName("张"+i);		
		
		t.dao.create(cm);

		//===============================================
		CustomerQueryModel cqm2 = new CustomerQueryModel();
		cqm2.getPage().setNowPage(2);
		List<CustomerModel> list2 = t.dao.getByConditionPage(cqm2);
		System.out.println(cqm2.getPage());
		for (CustomerModel customerModel : list2) 
		{
			System.out.println(customerModel.toString());
		}
		
	}
}
