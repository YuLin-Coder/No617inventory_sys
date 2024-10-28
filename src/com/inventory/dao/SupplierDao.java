package com.inventory.dao;

import com.inventory.entity.Supplier;
import com.inventory.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SupplierDao {

	private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());



	public List<Supplier> selectSupplierList() {
		String sql = "select * from supplier  ";
		List<Supplier> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Supplier>(Supplier.class));// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


	public Supplier findSupplierById(String id) {
		try {// 返回查询的信息Supplier
			return runner.query("select * from supplier where id=? ", new BeanHandler<Supplier>(Supplier.class),
					Integer.parseInt(id));
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


	public void deleteSupplier(String id) {
		Integer ids = Integer.parseInt(id);
		try {// 执行更改
			runner.update("delete from  supplier  where id=?", ids);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}



	public void addSupplier(Supplier Supplier) {
		// TODO Auto-generated method stub
		try {
			// 执行插入sql
			runner.update("insert into supplier(name,charger,phone,address,note) values (?,?,?,?,?)",
					Supplier.getName(),Supplier.getCharger(),Supplier.getPhone(),Supplier.getAddress()
			,Supplier.getNote());


		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}


	public void updateSupplier(Supplier Supplier) {
		// TODO Auto-generated method stub
		try {// 执行更改
			runner.update("update supplier set name=?,charger=?,phone=?,address=?,note=? where id=?",
					Supplier.getName(),Supplier.getCharger(),Supplier.getPhone(),Supplier.getAddress()
					,Supplier.getNote(), Supplier.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}



	public List<Supplier> getSupplierPage(int pageNum, int pageSize, Map map) {
		String sql = "select * from supplier where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and name like  '%" + map.get("key").toString() + "%'";
		}
		sql += " limit ?,? ";
		int startNo = (pageNum - 1) * pageSize;
		List<Supplier> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Supplier>(Supplier.class), new Object[] { startNo, pageSize });// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


	public int querySupplierCount(Map map) {
		String sql = "select count(*) from supplier where 1 = 1 ";
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


	public List<Supplier> selectSupplierByMap(Map map) {
		String sql = "select * from supplier where 1 = 1 ";
		List<Supplier> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Supplier>(Supplier.class));// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


    public Supplier selectSipplier(String username, String password, String type) {
		try {// 返回查询的信息Supplier
			return runner.query("select * from supplier where eno=? and pwd=? and role=? ", new BeanHandler<Supplier>(Supplier.class),
					username,password,type);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
    }
}
