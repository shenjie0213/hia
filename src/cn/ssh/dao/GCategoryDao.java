package cn.ssh.dao;

import java.util.List;

import cn.ssh.base.BaseDao;
import cn.ssh.domain.TGCategory;
import cn.ssh.domain.TGGoods;

public interface GCategoryDao extends BaseDao<TGCategory>{

	List<TGCategory> findTopList();

	List<TGCategory> findChildList(Integer parentId);

	List<TGCategory> findRootList();

	TGCategory findByName(String string);

	List<TGCategory> findListByUser(Integer authorId);

	List<TGCategory> findByParent(TGCategory fcate);

}
