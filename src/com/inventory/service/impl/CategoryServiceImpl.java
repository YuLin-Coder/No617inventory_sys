package com.inventory.service.impl;

import java.util.List;
import java.util.Map;

import com.inventory.dao.CategoryDao;
import com.inventory.entity.Admin;
import com.inventory.entity.Category;
import com.inventory.service.CategoryService;

public class CategoryServiceImpl implements CategoryService{
	
	CategoryDao dao = new CategoryDao();


	public List<Category> selectCategoryList() {
		// TODO Auto-generated method stub
		return  dao.selectCategoryList();
	}

	public Category findCategoryById(String id) {
		// TODO Auto-generated method stub
		return dao.findCategoryById(id);
	}

	public void deleteCategory(String id) {
		// TODO Auto-generated method stub
		dao.deleteCategory(id);
	}
	
	
	public void addCategory(Category Category) {
		// TODO Auto-generated method stub
		dao.addCategory(Category);
	}

	public void updateCategory(Category Category) {
		// TODO Auto-generated method stub
		dao.updateCategory(Category);
	}

	public List<Category> getCategoryPage(int pageNum, int pageSize, Map map) {
		// TODO Auto-generated method stub
		return dao.getCategoryPage(pageNum,pageSize,map);
	}

	public int queryCategoryCount(Map map) {
		// TODO Auto-generated method stub
		return  dao.queryCategoryCount(map);
	}

	public List<Category> selectCategoryByMap(Map map) {
		// TODO Auto-generated method stub
		return dao.selectCategoryByMap(map);
	}


}
