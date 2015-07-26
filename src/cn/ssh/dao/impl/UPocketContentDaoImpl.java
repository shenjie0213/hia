package cn.ssh.dao.impl;

import org.springframework.stereotype.Repository;

import cn.ssh.base.BaseDaoImpl;
import cn.ssh.dao.UPocketContentDao;
import cn.ssh.domain.TGGoods;
import cn.ssh.domain.TUCollectionContent;
import cn.ssh.domain.TUPocket;
import cn.ssh.domain.TUPocketContent;
@Repository
public class UPocketContentDaoImpl extends BaseDaoImpl<TUPocketContent>implements UPocketContentDao{

	public TUPocketContent findByPocketGoods(TUPocket pocket, TGGoods g) {
		// TODO Auto-generated method stub
		return (TUPocketContent)getSession().createQuery(//
				"FROM TUPocketContent ucc WHERE ucc.TUPocket=? and ucc.TGGoods=?")//
				.setParameter(0, pocket)//
				.setParameter(1, g)//
				.uniqueResult();
	}

}
