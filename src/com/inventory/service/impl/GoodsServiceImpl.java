package com.inventory.service.impl;

import com.inventory.dao.GoodsDao;
import com.inventory.entity.Goods;
import com.inventory.entity.Statics;
import com.inventory.service.GoodsService;

import java.util.List;
import java.util.Map;

public class GoodsServiceImpl implements GoodsService{
	
	GoodsDao dao = new GoodsDao();


	public List<Goods> selectGoodsList() {
		// TODO Auto-generated method stub
		return  dao.selectGoodsList();
	}

	@Override
	public Goods findGoodsById(String id) {
		// TODO Auto-generated method stub
		return dao.findGoodsById(id);
	}

	@Override
	public void deleteGoods(String id) {
		// TODO Auto-generated method stub
		dao.deleteGoods(id);
	}

	@Override
	public void addGoods(Goods Goods) {
		// TODO Auto-generated method stub
		dao.addGoods(Goods);
	}

	@Override
	public void updateGoods(Goods Goods) {
		// TODO Auto-generated method stub
		dao.updateGoods(Goods);
	}

	@Override
	public List<Goods> getGoodsPage(int pageNum, int pageSize, Map map) {
		// TODO Auto-generated method stub
		return dao.getGoodsPage(pageNum,pageSize,map);
	}

	@Override
	public int queryGoodsCount(Map map) {
		// TODO Auto-generated method stub
		return  dao.queryGoodsCount(map);
	}

	@Override
	public List<Goods> selectGoodsByMap(Map map) {
		// TODO Auto-generated method stub
		return dao.selectGoodsByMap(map);
	}

	@Override
	public void updateGoodsAmout(Goods goods) {
		dao.updateGoodsAmout(goods);
	}

	@Override
	public List<Statics> selectGoodsStatic() {
		return dao.selectGoodsStatic();
	}

	@Override
	public List<Statics> selectCrkCkStatic() {
		return dao.selectCrkCkStatic();
	}

	@Override
	public List<Statics> selectCrkRkStatic() {
		return dao.selectCrkRkStatic();
	}


}
