package com.core.dao.impl;

import com.core.dao.GeneratorDao;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 主键生成器接口的实现类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014年10月13日 下午2:30:18
 * @version 1.0
 */
public class GeneratorDaoImpl extends HibernateDaoImpl implements GeneratorDao {
	/**
	 * 主键生成方法
	 * @param entity 实体
	 * @param field 字段(主键)
	 * @param parentCode 父级编号
	 * @param codeLen 位数
	 * @return 生成的主键
	 */
	public String generatorCode(Class<?> entity, String field, String parentCode, int codeLen){
		StringBuilder hql = new StringBuilder();
		// SELECT MAX(CODE) FROM oa_id_module WHERE LENGTH(CODE) = 4 (没有父级code)
		// SELECT MAX(CODE) FROM oa_id_module WHERE LENGTH(CODE) = 12 AND CODE LIKE '00010001%'    // 00010001
		/** 对父级code做判断 */
		parentCode = StringUtils.hasText(parentCode) ? parentCode : "";
		hql.append("select max(" + field + ")")
		   .append(" from " +  entity.getSimpleName())
		   .append(" where length(" + field + ") = ?");
		
		/** 定义集合封装查询参数 */
		List<Object> params = new ArrayList<Object>();
		params.add(parentCode.length() + codeLen);
		if (StringUtils.hasText(parentCode)){
			hql.append(" and " + field + " like ?");
			params.add(parentCode + "%");
		}
		
		String maxCode = this.findUniqueEntity(hql.toString(), params.toArray());
		if (!StringUtils.hasText(maxCode)){
			// 0001
			// 0003 00030001
			String prefix = "";
			for (int i = 1; i < codeLen; i++){
				prefix += "0";
			}
			return parentCode + prefix + 1;
		}else{
			// 0002 --> 0003
			// 00010004 --> 00010005
			/** 取后面四位 */
			String suffix = maxCode.substring(maxCode.length() - codeLen, maxCode.length());
			// 0002  --> 3
			// 0004  --> 5
			String tempCode = String.valueOf(Integer.valueOf(suffix) + 1);
			// 3|3
			if (tempCode.length() > codeLen){
				throw new RuntimeException("主键生成已越界。。。。！");
			}else{
				return parentCode + suffix.substring(0, suffix.length() - tempCode.length()) + tempCode;
			}
		}
	}
}