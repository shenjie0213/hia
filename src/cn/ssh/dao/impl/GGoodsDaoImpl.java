package cn.ssh.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import cn.ssh.base.BaseDaoImpl;
import cn.ssh.dao.GGoodsDao;
import cn.ssh.domain.PageBean;
import cn.ssh.domain.TGAttrRange;
import cn.ssh.domain.TGCategory;
import cn.ssh.domain.TGGoods;
import cn.ssh.domain.TUCollection;
import cn.ssh.util.QueryHelper;
@Repository
public class GGoodsDaoImpl extends BaseDaoImpl<TGGoods> implements GGoodsDao {

	public List<TGGoods> findListByCat(Integer catId) {
		// TODO Auto-generated method stub
		return getSession().createQuery(//
				"FROM TGGoods good WHERE good.TGCategory.catId=?")//
				.setParameter(0, catId)//
				.list();
	}
	
	

	public List<TGGoods> findListByAttr(String s) {
		// TODO Auto-generated method stub
		//return null;
		
		
		//sql  ok!
		
		if(s == null || s.equals("")) {
			return this.findAll();
		}
		
		String[] array = s.split("_");
		String sq = "select * from t_g_goods where not EXISTS(select * from (select * from t_g_attr_range where cat_range_id in (";
		String addition = "";
		for(int i = 0; i < array.length; ++i) {
			addition += "," + array[i]; 
		}
		addition = addition.substring(1);
		
		sq = sq + addition + ")) as attribute where not EXISTS( select * from t_g_goods_attr  where goods_id=t_g_goods.goods_id and attr_range_id=attribute.cat_range_id))";
		
		return getSession().createSQLQuery(sq).addEntity(TGGoods.class).list();
		
		
	}
	
	public List<Integer> findListByAttr2(String s) {
		
		String[] array = s.split("_");
		String sq = "select goods_id from t_g_goods where not EXISTS(select * from (select * from t_g_attr_range where cat_range_id in (";
		String addition = "";
		for(int i = 0; i < array.length; ++i) {
			addition += "," + array[i]; 
		}
		addition = addition.substring(1);
		sq = sq + addition + ")) as attribute where not EXISTS( select * from t_g_goods_attr  where goods_id=t_g_goods.goods_id and attr_range_id=attribute.cat_range_id))";
		return getSession().createSQLQuery(sq).list();
	}

	public List<TGGoods> findListByCatList(List<TGCategory> soncatlist) {
		// TODO Auto-generated method stub
		String hql = "FROM TGGoods good WHERE good.TGCategory.catId in (";
		String addition = "";
		for(TGCategory tc : soncatlist) {
			addition += "," + tc.getCatId();
		}
		addition = addition.substring(1);
		hql = hql + addition + ")";
		//System.out.println(hql);
		return getSession().createQuery(hql).list();
	}



	public PageBean getPageBean2(int pageNum, int pageSize,
			String attrnow) {
		// TODO Auto-generated method stub
		String s = attrnow;
		String[] array = s.split("_");
		String sq1 = "select * from t_g_goods where not EXISTS(select * from (select * from t_g_attr_range where cat_range_id in (";
		String addition = "";
		for(int i = 0; i < array.length; ++i) {
			addition += "," + array[i]; 
		}
		addition = addition.substring(1);
		
		String sql = sq1 + addition + ")) as attribute where not EXISTS( select * from t_g_goods_attr  where goods_id=t_g_goods.goods_id and attr_range_id=attribute.cat_range_id))";
		
		SQLQuery listQuery = getSession().createSQLQuery(sql).addEntity(TGGoods.class);
		listQuery.setFirstResult((pageNum - 1) * pageSize);
		listQuery.setMaxResults(pageSize);
		List list = listQuery.list(); // 执行查询
		
		
		String sq2 = "select count(*) from t_g_goods where not EXISTS(select * from (select * from t_g_attr_range where cat_range_id in (";
		sql = sq2 + addition + ")) as attribute where not EXISTS( select * from t_g_goods_attr  where goods_id=t_g_goods.goods_id and attr_range_id=attribute.cat_range_id))";
		SQLQuery countQuery = getSession().createSQLQuery(sql);
		BigInteger count =  (BigInteger)countQuery.uniqueResult(); // 执行查询
		
		return new PageBean(pageNum, pageSize, count.intValue(), list);
		//return getSession().createSQLQuery(sq).addEntity(TGGoods.class).list();
	}



	public PageBean getPageBean(int pageNum, int pageSize, Integer authorId,String pocketName) {
		// TODO Auto-generated method stub
		String sql1="select g.* from(select c.goods_id from(select * from t_u_pocket where user_id=";
		String sql11=" and pocket_name='";
		String sql2="')as p,t_u_pocket_content c where p.pocket_id =c.pocket_id)as pg,t_g_goods g  where g.goods_id=pg.goods_id ";
		String sql=sql1+authorId+sql11+pocketName+sql2;
		SQLQuery listQuery = getSession().createSQLQuery(sql).addEntity(TGGoods.class);
		listQuery.setFirstResult((pageNum - 1) * pageSize);
		listQuery.setMaxResults(pageSize);
		List list = listQuery.list(); // 执行查询
		
		String sql3="select count(*)  from(select c.goods_id from(select * from t_u_pocket where user_id=";
		sql=sql3+authorId+sql11+pocketName+sql2;
		SQLQuery countQuery = getSession().createSQLQuery(sql);
		BigInteger count =  (BigInteger)countQuery.uniqueResult(); // 执行查询
		
		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}



	public PageBean getPageBeanC(int pageNum, int pageSize, Integer authorId,
			Integer cateId,String pocketName) {
		// TODO Auto-generated method stub
		String sql1="select * from(select g.* from(select c.goods_id from(select * from t_u_pocket where user_id=";
		String sql11=" and pocket_name='";
		String sql2="')as p,t_u_pocket_content c where p.pocket_id =c.pocket_id)as pg,t_g_goods g  where g.goods_id=pg.goods_id)as goods where goods.cat_id=";
		String sql=sql1+authorId+sql11+pocketName+sql2+cateId;
		SQLQuery listQuery = getSession().createSQLQuery(sql).addEntity(TGGoods.class);
		listQuery.setFirstResult((pageNum - 1) * pageSize);
		listQuery.setMaxResults(pageSize);
		List list = listQuery.list(); // 执行查询
		
		String sql3="select count(*) from(select g.* from(select c.goods_id from(select * from t_u_pocket where user_id=";
		sql=sql3+authorId+sql11+pocketName+sql2+cateId;
		SQLQuery countQuery = getSession().createSQLQuery(sql);
		BigInteger count =  (BigInteger)countQuery.uniqueResult(); // 执行查询
		
		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}



	public PageBean getPageBeanB(int pageNum, int pageSize, Integer authorId,
			Integer brandId, String pocketName) {
		// TODO Auto-generated method stub
		String sql1="select * from(select g.* from(select c.goods_id from(select * from t_u_pocket where user_id=";
		String sql11=" and pocket_name='";
		String sql2="')as p,t_u_pocket_content c where p.pocket_id =c.pocket_id)as pg,t_g_goods g  where g.goods_id=pg.goods_id)as goods where goods.brand_id=";
		String sql=sql1+authorId+sql11+pocketName+sql2+brandId;
		SQLQuery listQuery = getSession().createSQLQuery(sql).addEntity(TGGoods.class);
		listQuery.setFirstResult((pageNum - 1) * pageSize);
		listQuery.setMaxResults(pageSize);
		List list = listQuery.list(); // 执行查询
		
		String sql3="select count(*) from(select g.* from(select c.goods_id from(select * from t_u_pocket where user_id=";
		sql=sql3+authorId+sql11+pocketName+sql2+brandId;
		SQLQuery countQuery = getSession().createSQLQuery(sql);
		BigInteger count =  (BigInteger)countQuery.uniqueResult(); // 执行查询
		
		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}



	public PageBean getPageBeanBC(int pageNum, int pageSize, Integer authorId,
			Integer cateId, Integer brandId, String pocketName) {
		// TODO Auto-generated method stub
		String sql1="select * from(select g.* from(select c.goods_id from(select * from t_u_pocket where user_id=";
		String sql11=" and pocket_name='";
		String sql2="')as p,t_u_pocket_content c where p.pocket_id =c.pocket_id)as pg,t_g_goods g  where g.goods_id=pg.goods_id)as goods where goods.brand_id=";
		String sql22=" and goods.cat_id=";
		String sql=sql1+authorId+sql11+pocketName+sql2+brandId+sql22+cateId;
		SQLQuery listQuery = getSession().createSQLQuery(sql).addEntity(TGGoods.class);
		listQuery.setFirstResult((pageNum - 1) * pageSize);
		listQuery.setMaxResults(pageSize);
		List list = listQuery.list(); // 执行查询
		
		String sql3="select count(*) from(select g.* from(select c.goods_id from(select * from t_u_pocket where user_id=";
		sql=sql3+authorId+sql11+pocketName+sql2+brandId+sql22+cateId;
		SQLQuery countQuery = getSession().createSQLQuery(sql);
		BigInteger count =  (BigInteger)countQuery.uniqueResult(); // 执行查询
		
		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}



	public PageBean getPageBeanColl(int pageNum, int pageSize,
			Integer authorId, Integer collId) {
		// TODO Auto-generated method stub
		String sql1="select g.* from(select uc.goods_id from(select * from t_u_collection where coll_id=";
		String sql2=")as coll,t_u_collection_content uc where coll.coll_id=uc.coll_id)as cg,t_g_goods g where cg.goods_id=g.goods_id";
		String sql=sql1+collId+sql2;
		SQLQuery listQuery = getSession().createSQLQuery(sql).addEntity(TGGoods.class);
		
		
		listQuery.setFirstResult((pageNum - 1) * pageSize);
		listQuery.setMaxResults(pageSize);
		List list = listQuery.list(); // 执行查询
		
		String sql11="select count(*) from(select uc.goods_id from(select * from t_u_collection where coll_id=";
		sql=sql11+collId+sql2;
		SQLQuery countQuery = getSession().createSQLQuery(sql);
		BigInteger count =  (BigInteger)countQuery.uniqueResult(); // 执行查询
		
		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}



	public List<TGGoods> findBycatIdList(List<Integer> fcateIdList) {
		// TODO Auto-generated method stub
		String sql = "select * from t_g_goods  where cat_id in(";
		String addition = "";
		for(Integer tc : fcateIdList) {
			addition += "," +tc ;
		}
		addition = addition.substring(1);
		sql = sql + addition + ") order by eav_count desc,dream_count desc,share_count desc limit 5";
		//System.out.println(hql);
		return getSession().createSQLQuery(sql).addEntity(TGGoods.class).list();
	}
	

	
}
