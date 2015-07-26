package cn.ssh.dao.impl;

import org.springframework.stereotype.Repository;

import cn.ssh.base.BaseDaoImpl;
import cn.ssh.dao.UCollectionContentDao;
import cn.ssh.domain.TGGoods;
import cn.ssh.domain.TUCollection;
import cn.ssh.domain.TUCollectionContent;
import cn.ssh.domain.TUUser;
@Repository
public class UCollectionContentDaoImpl extends BaseDaoImpl<TUCollectionContent> implements UCollectionContentDao{

	public TUCollectionContent findByCollGoods(TUCollection c, TGGoods g) {
		// TODO Auto-generated method stub
		return (TUCollectionContent)getSession().createQuery(//
				"FROM TUCollectionContent ucc WHERE ucc.TUCollection=? and ucc.TGGoods=?")//
				.setParameter(0, c)//
				.setParameter(1, g)//
				.uniqueResult();
	}

}
