package cn.ssh.dao;

import cn.ssh.base.BaseDao;
import cn.ssh.domain.TGGoods;
import cn.ssh.domain.TUPocket;
import cn.ssh.domain.TUPocketContent;

public interface UPocketContentDao extends BaseDao<TUPocketContent>{

	TUPocketContent findByPocketGoods(TUPocket pocket, TGGoods g);

}
