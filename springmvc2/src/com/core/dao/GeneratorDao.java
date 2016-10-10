package com.core.dao;
/**
 * 主键生成器接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014年10月13日 下午2:29:09
 * @version 1.0
 */
public interface GeneratorDao extends HibernateDao {
	/**
	 * 主键生成方法
	 * @param entity 实体
	 * @param field 字段(主键)
	 * @param parentCode 父级编号
	 * @param codeLen 位数
	 * @return 生成的主键
	 */
	String generatorCode(Class<?> entity, String field, String parentCode, int codeLen);
}
