package com.atguigu.p2p.service.impl;

import java.util.List;

import com.atguigu.p2p.util.Page;
import com.atguigu.p2p.dao.BaseDAO;
import com.atguigu.p2p.service.IBaseService;
import com.atguigu.p2p.vo.BaseModel;

public class BaseServiceImpl<M,QM extends BaseModel> implements IBaseService<M,QM>
{

	private BaseDAO dao = null;
	
	public void setDao(BaseDAO dao) 
	{
		this.dao = dao;
	}

	
	@Override
	public void create(M m) {
		dao.create(m);
	}

	@Override
	public void update(M m) {
		dao.update(m);
	}

	@Override
	public void delete(int uuid) {
		dao.delete(uuid);
	}

	@Override
	public M getByUuid(int uuid) {
		
		return (M) dao.getByUuid(uuid);
	}

	@Override
	public Page<M> getByConditionPage(QM qm) 
	{
		List<M> result = dao.getByConditionPage(qm);
		
		qm.getPage().setResult(result);
		
		return qm.getPage();
	}

}
