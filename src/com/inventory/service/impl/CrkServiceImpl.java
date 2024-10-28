package com.inventory.service.impl;

import com.inventory.dao.CrkDao;
import com.inventory.entity.Crk;
import com.inventory.service.CrkService;

import java.util.List;
import java.util.Map;

public class CrkServiceImpl implements CrkService{
	
	CrkDao dao = new CrkDao();


	public List<Crk> selectCrkList() {
		// TODO Auto-generated method stub
		return  dao.selectCrkList();
	}

	public Crk findCrkById(String id) {
		// TODO Auto-generated method stub
		return dao.findCrkById(id);
	}

	public void deleteCrk(String id) {
		// TODO Auto-generated method stub
		dao.deleteCrk(id);
	}
	
	
	public void addCrk(Crk Crk) {
		// TODO Auto-generated method stub
		dao.addCrk(Crk);
	}

	public void updateCrk(Crk Crk) {
		// TODO Auto-generated method stub
		dao.updateCrk(Crk);
	}

	public List<Crk> getCrkPage(int pageNum, int pageSize, Map map) {
		// TODO Auto-generated method stub
		return dao.getCrkPage(pageNum,pageSize,map);
	}

	public int queryCrkCount(Map map) {
		// TODO Auto-generated method stub
		return  dao.queryCrkCount(map);
	}

	public List<Crk> selectCrkByMap(Map map) {
		// TODO Auto-generated method stub
		return dao.selectCrkByMap(map);
	}


}
