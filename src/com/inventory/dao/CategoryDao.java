package com.inventory.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.inventory.entity.Admin;
import com.inventory.entity.Category;
import com.inventory.utils.C3p0Utils;

public class CategoryDao {
	
	private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());



	public List<Category> selectCategoryList() {
		String sql = "select * from category  ";
		List<Category> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Category>(Category.class));// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


	public Category findCategoryById(String id) {
		try {// 返回查询的信息Category
			return runner.query("select * from category where id=? ", new BeanHandler<Category>(Category.class),
					Integer.parseInt(id));
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


	public void deleteCategory(String id) {
		Integer ids = Integer.parseInt(id);
		try {// 执行更改
			runner.update("delete from  category  where id=?", ids);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}



	public void addCategory(Category Category) {
		// TODO Auto-generated method stub
		try {
			// 执行插入sql
			runner.update("insert into category(name,note) values (?,?)",
					Category.getName(),Category.getNote());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}


	public void updateCategory(Category Category) {
		// TODO Auto-generated method stub
		try {// 执行更改
			runner.update("update category set name=?,note=? where id=?",
					Category.getName(),Category.getNote(), Category.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}



	public List<Category> getCategoryPage(int pageNum, int pageSize, Map map) {
		String sql = "select * from category where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and name like  '%" + map.get("key").toString() + "%'";
		}
		sql += " limit ?,? ";
		int startNo = (pageNum - 1) * pageSize;
		List<Category> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Category>(Category.class), new Object[] { startNo, pageSize });// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


	public int queryCategoryCount(Map map) {
		String sql = "select count(*) from category where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and name like  '%" + map.get("key").toString() + "%'";
		}
		try {
			Long count = (Long) runner.query(sql, new ScalarHandler());
			// 将long的类型转成int类型
			return count.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	public List<Category> selectCategoryByMap(Map map) {
		String sql = "select * from category where 1 = 1 ";
		List<Category> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Category>(Category.class));// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


}
