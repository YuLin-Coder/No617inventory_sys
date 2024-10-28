package com.inventory.service.impl;

import com.inventory.dao.SupplierDao;
import com.inventory.entity.Supplier;
import com.inventory.service.SupplierService;

import java.util.List;
import java.util.Map;

public class SupplierServiceImpl implements SupplierService{
	
	SupplierDao dao = new SupplierDao();



	public List<Supplier> selectSupplierList() {
		// TODO Auto-generated method stub
		return  dao.selectSupplierList();
	}

	@Override
	public Supplier findSupplierById(String id) {
		// TODO Auto-generated method stub
		return dao.findSupplierById(id);
	}

	@Override
	public void deleteSupplier(String id) {
		// TODO Auto-generated method stub
		dao.deleteSupplier(id);
	}

	@Override
	public void addSupplier(Supplier Supplier) {
		// TODO Auto-generated method stub
		dao.addSupplier(Supplier);
	}

	@Override
	public void updateSupplier(Supplier Supplier) {
		// TODO Auto-generated method stub
		dao.updateSupplier(Supplier);
	}

	@Override
	public List<Supplier> getSupplierPage(int pageNum, int pageSize, Map map) {
		// TODO Auto-generated method stub
		return dao.getSupplierPage(pageNum,pageSize,map);
	}

	@Override
	public int querySupplierCount(Map map) {
		// TODO Auto-generated method stub
		return  dao.querySupplierCount(map);
	}

	@Override
	public List<Supplier> selectSupplierByMap(Map map) {
		// TODO Auto-generated method stub
		return dao.selectSupplierByMap(map);
	}

	@Override
	public Supplier selectSipplier(String username, String password, String type) {
		return dao.selectSipplier(username,password,type);
	}


}
