package cn.ssh.service;

import cn.ssh.domain.TGGoods;
import cn.ssh.domain.TUPocket;
import cn.ssh.domain.TUPocketContent;

public interface UPocketContentService {

	void save(TUPocketContent pc);

	TUPocketContent findByPocketGoods(TUPocket pocket, TGGoods g);

	void delete(Integer id);

}
