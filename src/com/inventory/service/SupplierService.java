package com.inventory.service;

import com.inventory.entity.Supplier;

import java.util.List;
import java.util.Map;

public interface SupplierService {

	Supplier findSupplierById(String id);

	void deleteSupplier(String id);

	void addSupplier(Supplier supplier);

	List<Supplier> getSupplierPage(int pageNum, int pageSize, Map map);

	int querySupplierCount(Map map);

	void updateSupplier(Supplier supplier);

    List<Supplier> selectSupplierByMap(Map map);

    Supplier selectSipplier(String username, String password, String type);
}
