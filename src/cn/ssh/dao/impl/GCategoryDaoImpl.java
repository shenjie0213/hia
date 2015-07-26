package cn.ssh.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.ssh.base.BaseDaoImpl;
import cn.ssh.dao.GCategoryDao;
import cn.ssh.domain.TGBrand;
import cn.ssh.domain.TGCategory;
import cn.ssh.domain.TGGoods;
@Repository
public class GCategoryDaoImpl extends BaseDaoImpl<TGCategory> implements GCategoryDao {

	public List<TGCategory> findTopList() {
		// TODO Auto-generated method stub
		return getSession().createQuery(//
				"FROM TGCategory gc WHERE gc.parent is null")//
				.list();
	}

	public List<TGCategory> findChildList(Integer parentId) {
		// TODO Auto-generated method stub
		
		return getSession().createQuery(//
				"FROM TGCategory gc WHERE gc.parent.catId=?")//
				.setParameter(0, parentId)//
				.list();
		
	}

	public List<TGCategory> findRootList() {
		// TODO Auto-generated method stub
		return getSession().createQuery(//
				"FROM TGCategory gc  ")//
				.list();
	}
	
	public TGCategory findByName(String string) {
		// TODO Auto-generated method stub
		return (TGCategory) getSession().createQuery(//
				"FROM TGCategory gc WHERE gc.catName=?")//
				.setParameter(0, string).uniqueResult();//
				
	}

	public List<TGCategory> findListByUser(Integer authorId) {
		// TODO Auto-generated method stub
String sql1="SELECT * FROM t_g_category ca where exists(	select * from 	(select pg.goods_id,g.cat_id from (select p.pocket_id,c.goods_id from (select * from t_u_pocket where user_id =";
		
		String sql2=") as p,t_u_pocket_content c where p.pocket_id =c.pocket_id)as pg,t_g_goods g where pg.goods_id=g.goods_id)as pb where pb.cat_id=ca.cat_id)";
		
		String sql=sql1+authorId+sql2;
		return getSession().createSQLQuery(sql).addEntity(TGCategory.class).list();
	}

	public List<TGCategory> findByParent(TGCategory fcate) {
		// TODO Auto-generated method stub
		return (List<TGCategory>) getSession().createQuery(//
				"FROM TGCategory gc WHERE gc.parent=?")//
				.setParameter(0, fcate).list();
	}

}
