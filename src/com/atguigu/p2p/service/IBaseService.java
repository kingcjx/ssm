package com.atguigu.p2p.service;

import com.atguigu.p2p.util.Page;
import com.atguigu.p2p.vo.BaseModel;

public interface IBaseService<M,QM extends BaseModel>
{
	public void create(M m);
	public void update(M m);
	public void delete(int uuid);
	
	public M getByUuid(int uuid);
	public Page<M> getByConditionPage(QM qm);	
}
