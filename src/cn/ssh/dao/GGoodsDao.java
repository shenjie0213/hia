package cn.ssh.dao;

import java.util.List;

import cn.ssh.base.BaseDao;
import cn.ssh.domain.PageBean;
import cn.ssh.domain.TGAttrRange;
import cn.ssh.domain.TGCategory;
import cn.ssh.domain.TGGoods;
import cn.ssh.util.QueryHelper;

public interface GGoodsDao extends BaseDao<TGGoods> {
	
	public List<TGGoods> findListByCat(Integer catId);
	
	public List<TGGoods> findListByAttr(String s);

	public List<TGGoods> findListByCatList(List<TGCategory> soncatlist);

	public PageBean getPageBean2(int pageNum, int pageSize,
			String attrnow);

	public PageBean getPageBean(int pageNum, int pageSize, Integer authorId,String pocketNString);

	public PageBean getPageBeanC(int pageNum, int pageSize, Integer authorId,
			Integer cateId, String pocketName);

	public PageBean getPageBeanB(int pageNum, int pageSize, Integer authorId,
			Integer cateId, String pocketName);

	public PageBean getPageBeanBC(int pageNum, int pageSize, Integer authorId,
			Integer cateId, Integer brandId, String pocketName);

	public PageBean getPageBeanColl(int pageNum, int pageSize,
			Integer authorId, Integer collId);

	public List<TGGoods> findBycatIdList(List<Integer> fcateIdList);
}
