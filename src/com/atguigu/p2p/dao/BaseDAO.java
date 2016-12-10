package com.atguigu.p2p.dao;

import java.util.List;

public interface BaseDAO<M,QM> 
{
	public void create(M m);
	
	public void delete(int uuid);
	
	public void update(M m);

	public M getByUuid(int uuid);
	
	public List<M> getByConditionPage(QM qm);
}
