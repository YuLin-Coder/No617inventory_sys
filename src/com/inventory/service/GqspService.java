package com.inventory.service;

import com.inventory.entity.Gqsp;

import java.util.List;
import java.util.Map;

public interface GqspService {

	Gqsp findGqspById(String id);

	void deleteGqsp(String id);

	void addGqsp(Gqsp Gqsp);

	List<Gqsp> getGqspPage(int pageNum, int pageSize, Map map);

	int queryGqspCount(Map map);

	void updateGqsp(Gqsp Gqsp);

	List<Gqsp> selectGqspByMap(Map map);
}
