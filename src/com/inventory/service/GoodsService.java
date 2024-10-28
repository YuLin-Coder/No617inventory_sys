package com.inventory.service;

import com.inventory.entity.Goods;
import com.inventory.entity.Statics;

import java.util.List;
import java.util.Map;

public interface GoodsService {

	Goods findGoodsById(String id);

	void deleteGoods(String id);

	void addGoods(Goods Goods);

	List<Goods> getGoodsPage(int pageNum, int pageSize, Map map);

	int queryGoodsCount(Map map);

	void updateGoods(Goods Goods);

	List<Goods> selectGoodsByMap(Map map);

    void updateGoodsAmout(Goods goods);

	List<Statics> selectGoodsStatic();

	List<Statics> selectCrkCkStatic();

	List<Statics> selectCrkRkStatic();
}
