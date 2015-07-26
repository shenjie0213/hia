package cn.ssh.service;

import cn.ssh.domain.TGGoods;
import cn.ssh.domain.TUCollection;
import cn.ssh.domain.TUCollectionContent;

public interface UCollectionContentService {

	void save(TUCollectionContent cc);

	TUCollectionContent findByCollGoods(TUCollection c, TGGoods g);

	void delete(Integer id);



}
