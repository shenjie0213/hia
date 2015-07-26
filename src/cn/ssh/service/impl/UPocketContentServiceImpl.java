package cn.ssh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ssh.dao.UPocketContentDao;
import cn.ssh.domain.TGGoods;
import cn.ssh.domain.TUPocket;
import cn.ssh.domain.TUPocketContent;
import cn.ssh.service.UPocketContentService;
import cn.ssh.service.UpocketService;
@Service
@Transactional
public class UPocketContentServiceImpl implements UPocketContentService {
	@Resource
	UPocketContentDao upocketcontentDao;

	public void save(TUPocketContent pc) {
		// TODO Auto-generated method stub
		upocketcontentDao.save(pc);
	}

	public TUPocketContent findByPocketGoods(TUPocket pocket, TGGoods g) {
		// TODO Auto-generated method stub
		return  upocketcontentDao.findByPocketGoods(pocket,g);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		upocketcontentDao.delete(id);
	}
}
