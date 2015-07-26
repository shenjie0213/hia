package cn.ssh.service.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ssh.dao.UCollectionDao;
import cn.ssh.domain.TUCollection;
import cn.ssh.domain.TUUser;
import cn.ssh.service.UCollectionService;

@Service
@Transactional
public class UCollectionServiceImpl implements UCollectionService {
@Resource
UCollectionDao ucollectionDao;

public Collection<TUCollection> findByUser(TUUser user) {
	// TODO Auto-generated method stub
	return ucollectionDao.findByUser(user);
}

public TUCollection findById(Integer collectionId) {
	// TODO Auto-generated method stub
	return ucollectionDao.findById(collectionId);
}

public void save(TUCollection c) {
	// TODO Auto-generated method stub
	ucollectionDao.save(c);
}

public TUCollection findByUserAndName(TUUser user, String name) {
	// TODO Auto-generated method stub
	return ucollectionDao.findByUserAndName(user,name);
}

public void update(TUCollection c) {
	// TODO Auto-generated method stub
	ucollectionDao.udate(c);
}

public TUCollection findByUserAndCnPn(TUUser user, String name, String catName) {
	// TODO Auto-generated method stub
	return ucollectionDao.findByUserAndCnPn(user,name,catName);
}

public List<TUCollection> findBycateName(String catName) {
	// TODO Auto-generated method stub
	return ucollectionDao.findBycateName(catName);
}
}
