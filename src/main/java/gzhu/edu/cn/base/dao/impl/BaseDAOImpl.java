package gzhu.edu.cn.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.exception.SQLGrammarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gzhu.edu.cn.base.dao.BaseDAO;
import gzhu.edu.cn.base.model.PageData;

@Repository
public class BaseDAOImpl<T, ID extends Serializable> implements BaseDAO<T, ID> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PersistenceContext
	private EntityManager entityManager;

	private Class<T> clz;

	private String className;

	/**
	 * 构造方法，根据实例类自动获取实体类类型
	 */
	@SuppressWarnings("unchecked")
	public BaseDAOImpl() {
		this.clz = null;
		Class<? extends BaseDAOImpl> c = getClass();
		Type t = c.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			this.clz = (Class<T>) p[0];
			this.className = this.clz.getName();
		}
	}
	
	@Override
	@Transactional
	public boolean save(T entity) {
		boolean flag = false;
		try {
			entityManager.persist(entity);
			flag = true;
		} catch (Exception e) {
			logger.error("保存" + clz.getName() + "出错:" + e);
			throw e;
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(T t, Long id) {
		return (T) entityManager.find(t.getClass(), id);
	}

	@Override
	public T findById(Serializable id) {
		// TODO Auto-generated method stub
		return entityManager.find(clz, id);
	}

	@Override
	public List<T> findBysql(String tablename, String filed, Object o) {
		String sql = "from " + tablename + " u WHERE u." + filed + "=?";
		Query query = entityManager.createQuery(sql);
		query.setParameter(1, o);
		@SuppressWarnings("unchecked")
		List<T> list = query.getResultList();
		entityManager.close();
		return list;
	}

	public List<T> findBySql(String filed, Object o) {
		String sql = "from " + className + " u WHERE u.delFlag=0 and u." + filed + "=?";
		Query query = entityManager.createQuery(sql);
		query.setParameter(1, o);
		@SuppressWarnings("unchecked")
		List<T> list = query.getResultList();
		entityManager.close();
		return list;
	}
	
	/**
	 * 根据sql获取一个对象
	 * 如有参数，需要加上and
	 * @param hql
	 * @return
	 */
	public T getByHql(String hql)  {
		try {
			Query query = entityManager.createQuery(" from " + className + " where delFlag=0 " + hql);
			query.setMaxResults(1);
			T t =  (T) query.getSingleResult();
			entityManager.close();
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	public T findOne(String filed, Object o) {
		try {
		String sql = "from " + className + " u WHERE u.delFlag=0 and u." + filed + "=?";
		Query query = entityManager.createQuery(sql);
		query.setMaxResults(1);
		query.setParameter(1, o);
		@SuppressWarnings("unchecked")
		T t = (T) query.getSingleResult();
		entityManager.close();
		return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findOneBySql(String filed, Object o) {
		String sql = "from " + className + " u WHERE u.delFlag=0 and u." + filed + "=?1";
		Query query = entityManager.createQuery(sql);
		query.setParameter(1, o);
		entityManager.close();
		try{
			return (T) query.getSingleResult();
		}catch(Exception exception){
			return null;
		}
		
	}

	@Override
	public Object findObjiectBysql(String tablename, String filed, Object o) {
		String sql = "from " + tablename + " u WHERE u." + filed + "=?";
		Query query = entityManager.createQuery(sql);
		query.setParameter(1, o);

		entityManager.close();
		return query.getSingleResult();
	}

	@Override
	public List<T> findByMoreFiled(String tablename, LinkedHashMap<String, Object> map) {
		String sql = "from " + tablename + " u WHERE u.delFlag=0 and ";
		Set<String> set = null;
		set = map.keySet();
		List<String> list = new ArrayList<>(set);
		List<Object> filedlist = new ArrayList<>();
		for (String filed : list) {
			sql += "u." + filed + "=? and ";
			filedlist.add(filed);
		}
		//去掉最后一个and
		sql = sql.substring(0, sql.length() - 4);
		Query query = entityManager.createQuery(sql);
		for (int i = 0; i < filedlist.size(); i++) {
			query.setParameter(i + 1, map.get(filedlist.get(i)));
		}
		List<T> listRe = query.getResultList();
		entityManager.close();
		return listRe;
	}

	public List<T> findByMoreFiled(LinkedHashMap<String, Object> map) {
		String sql = "from " + className + " u WHERE u.delFlag=0 and ";
		Set<String> set = null;
		set = map.keySet();
		List<String> list = new ArrayList<>(set);
		List<Object> filedlist = new ArrayList<>();
		for (String filed : list) {
			sql += "u." + filed + "=? and ";
			filedlist.add(filed);
		}
		sql = sql.substring(0, sql.length() - 4);
		Query query = entityManager.createQuery(sql);
		for (int i = 0; i < filedlist.size(); i++) {
			query.setParameter(i + 1, map.get(filedlist.get(i)));
		}
		List<T> listRe = query.getResultList();
		entityManager.close();
		return listRe;
	}

	@Override
	public List<T> findByMoreFiledpages(String tablename, LinkedHashMap<String, Object> map, int start,
			int pageNumber) {
		String sql = "from " + tablename + " u WHERE u.delFlag=0 and";
		Set<String> set = null;
		set = map.keySet();
		List<String> list = new ArrayList<>(set);
		List<Object> filedlist = new ArrayList<>();
		for (String filed : list) {
			sql += "u." + filed + "=? and ";
			filedlist.add(filed);
		}
		sql = sql.substring(0, sql.length() - 4);
		Query query = entityManager.createQuery(sql);
		for (int i = 0; i < filedlist.size(); i++) {
			query.setParameter(i + 1, map.get(filedlist.get(i)));
		}
		query.setFirstResult((start - 1) * pageNumber);
		query.setMaxResults(pageNumber);
		List<T> listRe = query.getResultList();
		entityManager.close();
		return listRe;
	}

	public List<T> findByMoreFiledpages(LinkedHashMap<String, Object> map, int start, int pageNumber) {
		String sql = "from " + className + " u WHERE u.delFlag=0 and ";
		Set<String> set = null;
		set = map.keySet();
		List<String> list = new ArrayList<>(set);
		List<Object> filedlist = new ArrayList<>();
		for (String filed : list) {
			sql += "u." + filed + "=? and ";
			filedlist.add(filed);
		}
		sql = sql.substring(0, sql.length() - 4);
		//System.out.println(sql + "--------sql语句-------------");
		Query query = entityManager.createQuery(sql);
		for (int i = 0; i < filedlist.size(); i++) {
			query.setParameter(i + 1, map.get(filedlist.get(i)));
		}
		query.setFirstResult((start - 1) * pageNumber);
		query.setMaxResults(pageNumber);
		List<T> listRe = query.getResultList();
		entityManager.close();
		return listRe;
	}

	@Override
	public List<T> findpages(String tablename, String filed, Object o, int start, int pageNumer) {
		String sql = "from " + tablename + " u WHERE u.delFlag=0 and u." + filed + "=?";
		List<T> list = new ArrayList<>();
		try {
			Query query = entityManager.createQuery(sql);
			query.setParameter(1, o);
			query.setFirstResult((start - 1) * pageNumer);
			query.setMaxResults(pageNumer);
			list = query.getResultList();
			entityManager.close();
		} catch (Exception e) {
			e.printStackTrace();
			//后期要加入到错误日志中
			System.out.println("------------分页错误---------------");
		}

		return list;
	}
	
	public List<T> find(String hql) {
		if(hql!=null&&hql.length()>0){
			hql = " from " + className +" WHERE delFlag=0 and "+hql;
		}
		else{
			hql = " from " + className +" WHERE delFlag=0";
		}
		Query query = entityManager.createQuery(hql);
		return query.getResultList();
	}

	@Override
	@Transactional
	public boolean update(T entity) {
		boolean flag = false;
		try {
			entityManager.merge(entity);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---------------更新出错---------------");
		}
		return flag;
	}

	@Override
	public Integer updateMoreFiled(String tablename, LinkedHashMap<String, Object> map) {
		String sql = "UPDATE " + tablename + " AS u SET ";
		Set<String> set = null;
		set = map.keySet();
		List<String> list = new ArrayList<>(set);
		for (int i = 0; i < list.size() - 1; i++) {
			if (map.get(list.get(i)).getClass().getTypeName() == "java.lang.String") {
				System.out.println("-*****" + map.get(list.get(i)) + "------------" + list.get(i));
				sql += "u." + list.get(i) + "='" + map.get(list.get(i)) + "' , ";
			} else {
				sql += "u." + list.get(i) + "=" + map.get(list.get(i)) + " , ";
			}
		}
		sql = sql.substring(0, sql.length() - 2);
		sql += "where u.id=? ";
		System.out.println(sql + "--------sql语句-------------");
		int resurlt = 0;
		try {
			Query query = entityManager.createQuery(sql);
			query.setParameter(1, map.get("id"));
			resurlt = query.executeUpdate();
		} catch (Exception e) {
			System.out.println("更新出错-----------------------");
			e.printStackTrace();

		}
		return resurlt;
	}

	@Override
	public boolean delete(T entity) {
		boolean flag = false;
		try {
			entityManager.remove(entity);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---------------删除出错---------------");
		}
		return flag;
	}
	
	@Override
	@Transactional
	public int softDelete(Serializable id){
		int resurlt = 0;
		try {
			Query query = entityManager.createQuery("update  " + className +" set delFlag=1  where id=" +id);
			resurlt = query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---------------删除出错---------------");
		}
		return resurlt;
	}

	@Override
	public Object findCount(LinkedHashMap<String, Object> map) {
		String sql = "select count(u) from " + className + " u WHERE u.delFlag=0 and ";
		Set<String> set = null;
		set = map.keySet();
		List<String> list = new ArrayList<>(set);
		List<Object> filedlist = new ArrayList<>();
		int k = 0;//占位符
		for (String filed : list) {
			sql += "u." + filed + "=?"+k+" and ";
			filedlist.add(filed);
			k++;
		}
		sql = sql.substring(0, sql.length() - 4);
		Query query = entityManager.createQuery(sql);
		for (int i = 0; i < filedlist.size(); i++) {
			query.setParameter(i, map.get(filedlist.get(i)));
		}
		return query.getSingleResult();
	}

	@Override
	public List<T> findAll() throws SQLException  {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("FROM " + className +" where delFlag=0");
		List<T> result = query.getResultList();
		return result;
	}

	@Override
	public PageData<T> getPageData(int pageIndex, int pageSize, Map<String, Object> paramMap) {
		PageData<T> pageData = new PageData<T>(pageIndex, pageSize);

		int totalCount = this.queryDataCount(paramMap);

		List<T> list = this.queryPageData(pageData.getStartRow(), pageSize, paramMap);

		pageData.setTotalCount(totalCount);

		pageData.setPageData(list);

		return pageData;
	}

	@Override
	public int queryDataCount(Map<String, Object> params) {
		StringBuffer chql = new StringBuffer("SELECT COUNT(*) FROM ").append(className).append(" u WHERE u.delFlag=0 ");
		if (params != null) {
			for (String key : params.keySet()) {
				chql.append(" AND ").append(key).append("=").append(params.get(key));
			}
		}
		Query query = entityManager.createQuery(chql.toString());
		return  Integer.parseInt(query.getSingleResult().toString());
	}

	@Override
	public Long queryPageTotalCount(String hql, final Map<String, Object> params) {
		StringBuffer chql = new StringBuffer("SELECT COUNT(*) FROM ").append(className).append(" u WHERE u.delFlag=0 ");
		if (params != null) {
			for (String key : params.keySet()) {
				chql.append(" AND ").append(key).append("=").append(params.get(key));
			}
		}
		Query query = entityManager.createQuery(chql.toString());
		return (Long) query.getSingleResult();

	}

	@Override
	public List<T> queryPageData(int start, int maxSize, Map<String, Object> paramMap) {
		StringBuilder hql = new StringBuilder();
		hql.append("from " + className + "  where delFlag=0");
		if (paramMap != null) {
			for (String key : paramMap.keySet()) {
				hql.append(" and " + key + " = " + paramMap.get(key));
			}
		}
		hql.append(" order by id desc");
		return this.queryPageList(hql.toString(), paramMap, start, maxSize);
	}

	public PageData<T> getPageData(int page, int pageSize, String hql) {
		// 创建一个pagedata
		PageData<T> pageData = new PageData<T>(page, pageSize);

		// 求得总记录
		int totalCount = this.count(hql);
		pageData.setTotalCount(totalCount);
		pageData.setPageData(this.getPageList(page, pageSize, hql));

		return pageData;
	}
	
	public List<T> getPageList(int page, int pageSize, String hql)  {
		if(hql!=null&&hql.length()>0){
			hql = " from " + className +" WHERE delFlag=0 and "+hql;
		}
		else{
			hql = " from " + className +" WHERE delFlag=0";
		}
		Query query = entityManager.createQuery(hql);
		query.setFirstResult((page - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
	
	public int count(String hql) throws SQLGrammarException {
		if(hql!=null&&hql.length()>0){
			hql = "select count(*) from " + className +" WHERE delFlag=0 and "+hql;
		}
		else{
			hql = "select count(*) from " + className +" WHERE delFlag=0";
		}
		Query query = entityManager.createQuery(hql);

		return Integer.parseInt(query.getSingleResult().toString());
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryPageList(String hql, Map<String, Object> params, int start, int maxSize) {
		List<T> list = null;
		try {
			Query query = entityManager.createQuery(hql);
			if (params != null) {
				for (String key : params.keySet()) {
					query.setParameter(key, params.get(key));
				}
			}
			// 用于分页查询
			if (maxSize != 0) {
				query.setFirstResult(start);
				query.setMaxResults(maxSize);
			}
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Object> findObjectBySql(String sql) {
		Query query = entityManager.createNativeQuery(sql);
		return query.getResultList();
	}

	@Override
	public List<Object[]> findBySql(String sql) {
		Query query = entityManager.createNativeQuery(sql);
		return query.getResultList();
	}

	@Override
	public int getCountBySql(String sql) {
		Query query = entityManager.createNativeQuery(sql);
		return Integer.parseInt(query.getSingleResult().toString());
	}

	@Override
	public List<T> queryPageData(int start, int maxSize, String appendSql) {
		StringBuilder hql = new StringBuilder();
		if(appendSql!=null&&appendSql.length()>0){
			appendSql = " and "+appendSql;
		}
		hql.append("from " + className + "  where  delFlag=0 ").append(appendSql);
		
		hql.append(" order by id desc");
		return this.queryPageList(hql.toString(), null, start, maxSize);
	}

	@Override
	public List<Object[]> findByNaviteSql(String sql) {
		Query query = entityManager.createNativeQuery(sql);
		return query.getResultList();
	}
	
	@Override
	@Transactional
	public int executeSql(String sql) {
		Query query = entityManager.createNativeQuery(sql);
		return query.executeUpdate();
	}

	@Override
	@Transactional
	public void batchSave(List<T> entitys) {
		for (int i = 0; i < entitys.size(); i++) {
			entityManager.persist(entitys.get(i));
			if (i % 20 == 0) {
				// 20个对象后才清理缓存，写入数据库
				entityManager.flush();
				entityManager.clear();
			}
		}
		// 最后清理一下----防止大于20小于40的不保存
		entityManager.flush();
		entityManager.clear();
		
	}

	@Override
	public PageData<T> getPageData(int page, int pageSize, String hql, String order) {
		// 创建一个pagedata
				PageData<T> pageData = new PageData<T>(page, pageSize);

				// 求得总记录
				int totalCount = this.count(hql);
				pageData.setTotalCount(totalCount);
				pageData.setPageData(this.getPageList(page, pageSize, hql+order));

				return pageData;
	}
	
	@Override
	public PageData getDataByPrototypicalSQL(int pageIndex, int pageSize, String sql, String countSql)
			 {
		PageData<T> pageData = new PageData<T>(pageIndex, pageSize);
		int totalCount = this.queryPageTotalCountByPrototypicalSQLCount(countSql);
		List<T> list = this.queryPageDataByPrototypicalSQL(pageData.getStartRow(), pageSize, sql);
		pageData.setTotalCount(totalCount);
		pageData.setPageData(list);
		return pageData;
	}
	
	@Override
	public int queryPageTotalCountByPrototypicalSQLCount(String sql)  {
		return queryPageTotalCountByPrototypicalSQL(sql).intValue();
	}

	public List<T> queryPageDataByPrototypicalSQL(int start, int maxSize, String sql)  {
		return this.queryPageListByPrototypicalSQL(sql, start, maxSize);
	}

	@Override
	public List queryPageListByPrototypicalSQL(final String sql)  {
		List<T> list = null;
		try {
			Query query = entityManager.createNativeQuery(sql);
			// System.out.println(sql);
			// 用于分页查询
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List queryPageListByPrototypicalSQL(String sql, int start, int maxSize)  {
		List<T> list = null;
		Query query = entityManager.createNativeQuery(sql);
		// System.out.println(sql);
		// 用于分页查询
		if (maxSize != 0) {
			query.setFirstResult(start);
			query.setMaxResults(maxSize);
		}
		list = query.getResultList();
		return list;
	}

	@Override
	public Long queryPageTotalCountByPrototypicalSQL(final String sql)  {

		long size = 0l;
		try {
			Query query = entityManager.createNativeQuery(sql);
			size = Long.parseLong(query.getSingleResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return size;
	}
}
