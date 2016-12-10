package com.atguigu.p2p.test;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.p2p.util.Page;
import com.atguigu.p2p.dao.ICustomerDAO;
import com.atguigu.p2p.service.ICustomerService;
import com.atguigu.p2p.vo.CustomerModel;
import com.atguigu.p2p.vo.CustomerQueryModel;


@Service
public class Client_Service 
{
	@Resource
	private ICustomerService service = null;
	
	
	
	public ICustomerService getServiceT() {
		return service;
	}



	public static void main(String[] args) 
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Client_Service t = (Client_Service)ctx.getBean("client_Service");
		
		/*for (int i = 16; i <17; i++) 
		{
			CustomerModel cm = new CustomerModel();
			cm.setCustomerId("cid"+RandomUtils.nextInt());
			cm.setPwd(UUID.randomUUID().toString().substring(26));
			cm.setRegisterTime(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
			cm.setShowName("CA:"+i);
			cm.setTrueName("李"+i);		
			
			t.getServiceT().create(cm);
		}
		*/
		CustomerQueryModel cqm = new CustomerQueryModel();
		cqm.getPage().setNowPage(3);
		cqm.getPage().setPageShow(10);
		
		Page<CustomerModel> p = t.getServiceT().getByConditionPage(cqm);

		List<CustomerModel> list = p.getResult();
		
		for (CustomerModel customerModel : list) 
		{
			System.out.println(customerModel);
		}
	}
}
