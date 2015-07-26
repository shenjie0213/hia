package cn.ssh.dao.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.ssh.base.BaseDaoImpl;
import cn.ssh.dao.UCollectionDao;
import cn.ssh.domain.TGGoods;
import cn.ssh.domain.TUCollection;
import cn.ssh.domain.TUUser;
@Repository
public class UCollectionDaoImpl extends BaseDaoImpl<TUCollection> implements UCollectionDao {

	public Collection<TUCollection> findByUser(TUUser user) {
		// TODO Auto-generated method stub
		return getSession().createQuery(//
				"FROM TUCollection c WHERE c.TUUser=?")//
				.setParameter(0, user)//
				.list();
	}

	public TUCollection findByUserAndName(TUUser user, String name) {
		// TODO Auto-generated method stub
		return (TUCollection)getSession().createQuery(//
				"FROM TUCollection c WHERE c.TUUser=? and c.name=?")//
				.setParameter(0, user)//
				.setParameter(1, name)//
				.uniqueResult();
	}

	public TUCollection findByUserAndCnPn(TUUser user, String name,
			String catName) {
		// TODO Auto-generated method stub
		return (TUCollection)getSession().createQuery(//
				"FROM TUCollection c WHERE c.TUUser=? and c.name=? and c.TUPocket.pocketName=?")//
				.setParameter(0, user)//
				.setParameter(1, name)//
				.setParameter(2, catName)//
				.uniqueResult();
	}

	public List<TUCollection> findBycateName(String catName) {
		// TODO Auto-generated method stub
		
		String sq1 = "select * from t_u_collection uc where exists(select * from(select * from t_u_pocket p where p.pocket_name='";
		String sq2="')as pt where pt.pocket_id=uc.pocket_id) order by focus_count desc limit 5";
		String sq=sq1+catName+sq2;
		return getSession().createSQLQuery(sq).addEntity(TUCollection.class).list();
	}



}
