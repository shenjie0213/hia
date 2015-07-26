package cn.ssh.dao;

import cn.ssh.base.BaseDao;
import cn.ssh.domain.TGGoods;
import cn.ssh.domain.TUCollection;
import cn.ssh.domain.TUCollectionContent;

public interface UCollectionContentDao  extends BaseDao<TUCollectionContent>{

	TUCollectionContent findByCollGoods(TUCollection c, TGGoods g);

}
