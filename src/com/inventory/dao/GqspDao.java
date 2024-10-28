package com.inventory.dao;

import com.inventory.entity.Gqsp;
import com.inventory.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class GqspDao {
	
	private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());



	public List<Gqsp> selectGqspList() {
		String sql = "select * from gqsp  ";
		List<Gqsp> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Gqsp>(Gqsp.class));// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


	public Gqsp findGqspById(String id) {
		try {// 返回查询的信息Gqsp
			return runner.query("select * from gqsp where id=? ", new BeanHandler<Gqsp>(Gqsp.class),
					Integer.parseInt(id));
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


	public void deleteGqsp(String id) {
		Integer ids = Integer.parseInt(id);
		try {// 执行更改
			runner.update("delete from  gqsp  where id=?", ids);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}



	public void addGqsp(Gqsp Gqsp) {
		// TODO Auto-generated method stub
		try {
			// 执行插入sql
			runner.update("insert into gqsp(name,create_date,bzq,cid,sid,create_time,jlr,over_date) values (?,?,?,?,?,?,?,?)",
					Gqsp.getName(),Gqsp.getCreate_date(),Gqsp.getBzq(),
					Gqsp.getCid(),Gqsp.getSid(),Gqsp.getCreate_time(),Gqsp.getJlr(),Gqsp.getOver_date());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}


	public void updateGqsp(Gqsp Gqsp) {
		// TODO Auto-generated method stub
		try {// 执行更改
			runner.update("update gqsp set name=?,create_date=?,bzq=?,cid=?,sid=?,jlr=?,over_date=? where id=?",
					Gqsp.getName(),Gqsp.getCreate_date(),Gqsp.getBzq(),
					Gqsp.getCid(),Gqsp.getSid(),Gqsp.getJlr(),Gqsp.getOver_date(), Gqsp.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}



	public List<Gqsp> getGqspPage(int pageNum, int pageSize, Map map) {
		String sql = "select * from gqsp where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and name like  '%" + map.get("key").toString() + "%'";
		}
		sql += " limit ?,? ";
		int startNo = (pageNum - 1) * pageSize;
		List<Gqsp> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Gqsp>(Gqsp.class), new Object[] { startNo, pageSize });// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


	public int queryGqspCount(Map map) {
		String sql = "select count(*) from gqsp where 1 = 1 ";
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


	public List<Gqsp> selectGqspByMap(Map map) {
		String sql = "select * from gqsp where 1 = 1 ";
		List<Gqsp> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Gqsp>(Gqsp.class));// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


}
