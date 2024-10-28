package com.inventory.service.impl;

import com.inventory.dao.GqspDao;
import com.inventory.entity.Gqsp;
import com.inventory.service.GqspService;

import java.util.List;
import java.util.Map;

public class GqspServiceImpl implements GqspService{
	
	GqspDao dao = new GqspDao();


	public List<Gqsp> selectGqspList() {
		// TODO Auto-generated method stub
		return  dao.selectGqspList();
	}

	public Gqsp findGqspById(String id) {
		// TODO Auto-generated method stub
		return dao.findGqspById(id);
	}

	public void deleteGqsp(String id) {
		// TODO Auto-generated method stub
		dao.deleteGqsp(id);
	}
	
	
	public void addGqsp(Gqsp Gqsp) {
		// TODO Auto-generated method stub
		dao.addGqsp(Gqsp);
	}

	public void updateGqsp(Gqsp Gqsp) {
		// TODO Auto-generated method stub
		dao.updateGqsp(Gqsp);
	}

	public List<Gqsp> getGqspPage(int pageNum, int pageSize, Map map) {
		// TODO Auto-generated method stub
		return dao.getGqspPage(pageNum,pageSize,map);
	}

	public int queryGqspCount(Map map) {
		// TODO Auto-generated method stub
		return  dao.queryGqspCount(map);
	}

	public List<Gqsp> selectGqspByMap(Map map) {
		// TODO Auto-generated method stub
		return dao.selectGqspByMap(map);
	}


}
