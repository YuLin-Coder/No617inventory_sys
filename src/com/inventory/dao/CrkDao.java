package com.inventory.dao;

import com.inventory.entity.Crk;
import com.inventory.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CrkDao {
	
	private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());



	public List<Crk> selectCrkList() {
		String sql = "select * from crk  ";
		List<Crk> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Crk>(Crk.class));// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


	public Crk findCrkById(String id) {
		try {// 返回查询的信息Crk
			return runner.query("select * from crk where id=? ", new BeanHandler<Crk>(Crk.class),
					Integer.parseInt(id));
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


	public void deleteCrk(String id) {
		Integer ids = Integer.parseInt(id);
		try {// 执行更改
			runner.update("delete from  crk  where id=?", ids);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}



	public void addCrk(Crk Crk) {
		// TODO Auto-generated method stub
		try {
			// 执行插入sql
			runner.update("insert into crk(gid,type,charger,nums,create_time) values (?,?,?,?,?)",
					Crk.getGid(),Crk.getType(),Crk.getCharger(),Crk.getNums(),Crk.getCreate_time());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}


	public void updateCrk(Crk Crk) {
		// TODO Auto-generated method stub
//		try {// 执行更改
//			runner.update("update crk set title=?,content=?,editor=? where id=?",
//					Crk.getTitle(),Crk.getContent(),Crk.getEditor(), Crk.getId());
//		} catch (SQLException e) {
//			throw new RuntimeException(e);// 抛出运行异常
//		}
	}



	public List<Crk> getCrkPage(int pageNum, int pageSize, Map map) {
		String sql = "select * from crk where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and charger like  '%" + map.get("key").toString() + "%'";
		}
		if (map.get("type") != null && !map.get("type").toString().equals("")) {
			sql += " and type =  '" + map.get("type").toString() + "'";
		}
		sql += " limit ?,? ";
		int startNo = (pageNum - 1) * pageSize;
		List<Crk> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Crk>(Crk.class), new Object[] { startNo, pageSize });// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


	public int queryCrkCount(Map map) {
		String sql = "select count(*) from crk where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and charger like  '%" + map.get("key").toString() + "%'";
		}
		if (map.get("type") != null && !map.get("type").toString().equals("")) {
			sql += " and type =  '" + map.get("type").toString() + "'";
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


	public List<Crk> selectCrkByMap(Map map) {
		String sql = "select * from crk where 1 = 1 ";
		List<Crk> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Crk>(Crk.class));// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


}
