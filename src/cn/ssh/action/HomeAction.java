package cn.ssh.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.ssh.base.BaseAction;
import cn.ssh.domain.TGCategory;
import cn.ssh.domain.TGGoods;
import cn.ssh.domain.TUCollection;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Controller("homeAction")
@Scope("prototype")
public class HomeAction extends BaseAction<TGGoods> {

	public  String indexList(){
		//准备分类数据
		TGCategory fcate=gcategoryService.findByName("时尚");
		TGCategory bcate=gcategoryService.findByName("美妆");
		TGCategory hcate=gcategoryService.findByName("家居");
		List<TGCategory> secFList=gcategoryService.findByParent(fcate);
		Map<TGCategory, List<TGCategory>> FMap=new LinkedHashMap<TGCategory, List<TGCategory>>();
		List<TGCategory> secBList=gcategoryService.findByParent(bcate);
		Map<TGCategory, List<TGCategory>> BMap=new LinkedHashMap<TGCategory, List<TGCategory>>();
		List<TGCategory> secHList=gcategoryService.findByParent(hcate);
		Map<TGCategory, List<TGCategory>> HMap=new LinkedHashMap<TGCategory, List<TGCategory>>();
		
		for(TGCategory cc:secFList){
			
			List<TGCategory> thirdFlist=gcategoryService.findByParent(cc);
			if(thirdFlist!=null&&thirdFlist.size()!=0){
				FMap.put(cc, thirdFlist);
				List<TGCategory> ll=FMap.get(cc);
			
			}
			else{
				FMap.put(cc, null);
			}
		}
		
		System.out.println();
		for(TGCategory cc:secBList){
			List<TGCategory> thirdBlist=gcategoryService.findByParent(cc);
			if(thirdBlist!=null&&thirdBlist.size()!=0){
				BMap.put(cc, thirdBlist);
			}
			else{
				BMap.put(cc, null);
			}
		}
		for(TGCategory cc:secHList){
			List<TGCategory> thirdHlist=gcategoryService.findByParent(cc);
			if(thirdHlist!=null&&thirdHlist.size()!=0){
				HMap.put(cc, thirdHlist);
			}
			else{
				HMap.put(cc, null);
			}
		}
		ActionContext.getContext().put("FMap", FMap);
		ActionContext.getContext().put("BMap", BMap);
		ActionContext.getContext().put("HMap", HMap);
		
		//准备collection
		List<TUCollection> fuc=ucollectionService.findBycateName(fcate.getCatName());
		List<TUCollection> buc=ucollectionService.findBycateName(bcate.getCatName());
		List<TUCollection> huc=ucollectionService.findBycateName(hcate.getCatName());
		
		ActionContext.getContext().put("fuc", fuc);
		ActionContext.getContext().put("buc", buc);
		ActionContext.getContext().put("huc", huc);
		//准备产品
		List<Integer> fcateIdList=new ArrayList<Integer>();
		List<Integer> bcateIdList=new ArrayList<Integer>();
		List<Integer> hcateIdList=new ArrayList<Integer>();
		for(TGCategory cc:secFList){
			List<TGCategory> thirdFlist=gcategoryService.findByParent(cc);
			if(thirdFlist!=null&&thirdFlist.size()!=0){
				for(TGCategory ccc:thirdFlist){
					fcateIdList.add(ccc.getCatId());
				}
			}
			else{
				fcateIdList.add(cc.getCatId());
			}
		}
		for(TGCategory cc:secBList){
			List<TGCategory> thirdBlist=gcategoryService.findByParent(cc);
			if(thirdBlist!=null&&thirdBlist.size()!=0){
				for(TGCategory ccc:thirdBlist){
					bcateIdList.add(ccc.getCatId());
				}
			}
			else{
				bcateIdList.add(cc.getCatId());
			}
		}
		for(TGCategory cc:secHList){
			List<TGCategory> thirdHlist=gcategoryService.findByParent(cc);
			if(thirdHlist!=null&&thirdHlist.size()!=0){
				for(TGCategory ccc:thirdHlist){
					hcateIdList.add(ccc.getCatId());
				}
			}
			else{
				hcateIdList.add(cc.getCatId());
			}
		}
		List<TGGoods> fgoosList=ggoodsService.findBycatIdList(fcateIdList);
		for(TGGoods goods:fgoosList){
			System.out.println(goods.getGoodsName()+"000000000000000");
		}
		List<TGGoods> bgoosList=ggoodsService.findBycatIdList(bcateIdList);
		List<TGGoods> hgoosList=ggoodsService.findBycatIdList(hcateIdList);
		ActionContext.getContext().put("fgoosList", fgoosList);
		ActionContext.getContext().put("bgoosList", bgoosList);
		ActionContext.getContext().put("hgoosList", hgoosList);
		//展示产品
		return "indexList";
	}
	
}
