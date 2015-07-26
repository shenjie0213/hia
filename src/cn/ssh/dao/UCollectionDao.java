package cn.ssh.dao;

import java.util.Collection;
import java.util.List;

import cn.ssh.base.BaseDao;
import cn.ssh.domain.TUCollection;
import cn.ssh.domain.TUUser;

public interface UCollectionDao  extends BaseDao<TUCollection>{

	Collection<TUCollection> findByUser(TUUser user);

	TUCollection findByUserAndName(TUUser user, String name);

	TUCollection findByUserAndCnPn(TUUser user, String name, String catName);

	List<TUCollection> findBycateName(String catName);

	

}
