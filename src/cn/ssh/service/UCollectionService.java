package cn.ssh.service;

import java.util.Collection;
import java.util.List;

import cn.ssh.domain.TUCollection;
import cn.ssh.domain.TUUser;

public interface UCollectionService {

	Collection<TUCollection> findByUser(TUUser user);

	TUCollection findById(Integer collectionId);

	void save(TUCollection c);

	TUCollection findByUserAndName(TUUser user, String name);

	void update(TUCollection c);

	TUCollection findByUserAndCnPn(TUUser user, String name, String catName);

	List<TUCollection> findBycateName(String catName);

}
