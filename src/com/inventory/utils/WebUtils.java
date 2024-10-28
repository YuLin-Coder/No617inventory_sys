package com.inventory.utils;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import java.util.Map;

public class WebUtils {

	public static  <T> T copyParamToBean(Map value, T bean){//使用泛型来减少request.getParameter的重复代码，优化开发
		try {

			System.out.println("注入之前"+bean);
			BeanUtils.populate(bean,value);
			System.out.println("注入之后"+bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	/**
	 * 把map转换成对象
	 *
	 * @param map
	 * @param clazz
	 * @return
	 *
	 * 		把Map转换成指定类型
	 */
	@SuppressWarnings("rawtypes")
	public static <T> T toBean(Map map, Class<T> clazz) {
		try {
			/*
			 * 1. 通过参数clazz创建实例 2. 使用BeanUtils.populate把map的数据封闭到bean中
			 */
			T bean = clazz.newInstance();
			ConvertUtils.register(new DateConverter(), java.util.Date.class);
			BeanUtils.populate(bean, map);
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static int parseInt(String sid,int defalultvalue) {//把String类型的id转化Integer类型的
		  try {
	            return Integer.parseInt(sid);
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        return defalultvalue;
	}


}
