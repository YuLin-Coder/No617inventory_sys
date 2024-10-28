package com.inventory.dao;

import com.inventory.entity.Goods;
import com.inventory.entity.Statics;
import com.inventory.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class GoodsDao {
	
	private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());



	public List<Goods> selectGoodsList() {
		String sql = "select * from goods  ";
		List<Goods> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Goods>(Goods.class));// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


	public Goods findGoodsById(String id) {
		try {// 返回查询的信息Goods
			return runner.query("select * from goods where id=? ", new BeanHandler<Goods>(Goods.class),
					Integer.parseInt(id));
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


	public void deleteGoods(String id) {
		Integer ids = Integer.parseInt(id);
		try {// 执行更改
			runner.update("delete from  goods  where id=?", ids);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}



	public void addGoods(Goods Goods) {
		// TODO Auto-generated method stub
		try {
			// 执行插入sql
			runner.update("insert into goods(name,price,amount,create_date,bzq,cid,sid,create_time) values (?,?,?,?,?,?,?,?)",
					Goods.getName(),Goods.getPrice(),Goods.getAmount(),Goods.getCreate_date(),Goods.getBzq(),
					Goods.getCid(),Goods.getSid(),Goods.getCreate_time());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}


	public void updateGoods(Goods Goods) {
		// TODO Auto-generated method stub
		try {// 执行更改
			runner.update("update goods set name=?,price=?,amount=?,create_date=?,bzq=?,cid=?,sid=? where id=?",
					Goods.getName(),Goods.getPrice(),Goods.getAmount(),Goods.getCreate_date(),Goods.getBzq(),
					Goods.getCid(),Goods.getSid(), Goods.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}



	public List<Goods> getGoodsPage(int pageNum, int pageSize, Map map) {
		String sql = "select * from goods where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and name like  '%" + map.get("key").toString() + "%'";
		}
		sql += " limit ?,? ";
		int startNo = (pageNum - 1) * pageSize;
		List<Goods> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Goods>(Goods.class), new Object[] { startNo, pageSize });// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


	public int queryGoodsCount(Map map) {
		String sql = "select count(*) from goods where 1 = 1 ";
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


	public List<Goods> selectGoodsByMap(Map map) {
		String sql = "select * from goods where 1 = 1 ";
		List<Goods> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Goods>(Goods.class));// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


	public void updateGoodsAmout(Goods goods) {
		try {// 执行更改
			runner.update("update goods set amount=? where id=?",
					goods.getAmount(), goods.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	public List<Statics> selectGoodsStatic() {
		String sql=" select count(1) as counts , c.name as message  from goods g left join category c on g.cid = c.id  GROUP BY message    ";
		List<Statics> list=null;
		try {
			list= runner.query(sql, new BeanListHandler<Statics>(Statics.class));//添加实体类的适配器进行类的反�?
			return list;
		} catch (Exception e) {//捕获异常
			throw new RuntimeException(e);//抛出运行异常
		}

	}

	public List<Statics> selectCrkCkStatic() {
		String sql="select sum(nums) as counts , LEFT(create_time,10)  as message   from crk where type = '2' GROUP BY message     ";
		List<Statics> list=null;
		try {
			list= runner.query(sql, new BeanListHandler<Statics>(Statics.class));
			return list;
		} catch (Exception e) {//捕获异常
			throw new RuntimeException(e);//抛出运行异常
		}
	}

	public List<Statics> selectCrkRkStatic() {
		String sql="select sum(nums) as counts , LEFT(create_time,10)  as message   from crk where type = '1' GROUP BY message     ";
		List<Statics> list=null;
		try {
			list= runner.query(sql, new BeanListHandler<Statics>(Statics.class));
			return list;
		} catch (Exception e) {//捕获异常
			throw new RuntimeException(e);//抛出运行异常
		}
	}
}
