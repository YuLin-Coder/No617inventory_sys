package com.inventory.service;

import java.util.List;
import java.util.Map;

import com.inventory.entity.Category;

public interface CategoryService {

	Category findCategoryById(String id);

	void deleteCategory(String id);

	void addCategory(Category Category);

	List<Category> getCategoryPage(int pageNum, int pageSize, Map map);

	int queryCategoryCount(Map map);

	void updateCategory(Category Category);

	List<Category> selectCategoryByMap(Map map);
}
