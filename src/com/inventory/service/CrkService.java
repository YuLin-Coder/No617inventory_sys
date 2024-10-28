package com.inventory.service;

import com.inventory.entity.Crk;

import java.util.List;
import java.util.Map;

public interface CrkService {

	Crk findCrkById(String id);

	void deleteCrk(String id);

	void addCrk(Crk crk);

	List<Crk> getCrkPage(int pageNum, int pageSize, Map map);

	int queryCrkCount(Map map);

	void updateCrk(Crk crk);

}
