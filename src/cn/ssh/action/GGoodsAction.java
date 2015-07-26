package cn.ssh.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import cn.ssh.base.BaseAction;
import cn.ssh.domain.PageBean;
import cn.ssh.domain.TGAttrRange;
import cn.ssh.domain.TGAttribute;
import cn.ssh.domain.TGBrand;
import cn.ssh.domain.TGBrandCate;
import cn.ssh.domain.TGCategory;
import cn.ssh.domain.TUCollection;
import cn.ssh.domain.TUCollectionContent;
import cn.ssh.domain.TUDream;
import cn.ssh.domain.TUDreamContent;
import cn.ssh.domain.TUPocket;
import cn.ssh.domain.TUPocketContent;
import cn.ssh.domain.TUUser;

import cn.ssh.domain.TGGoods;
import cn.ssh.domain.TGGoodsAttr;
import cn.ssh.domain.TGGoodsImg;


import cn.ssh.service.GBrandCateService;
import cn.ssh.util.GAttributeUtil;
import cn.ssh.util.GCategoryUtil;
import cn.ssh.util.QueryHelper;
import cn.ssh.util.UploadUtil;


@Controller("gGoodsAction")
@Scope("prototype")
public class GGoodsAction extends BaseAction<TGGoods> {
	private String selectRange;//顶替al_gattrrangeList;
	private Integer collectionId;
	private  boolean is_dreamed;
	
	public String  show(){

		TUUser user=(TUUser) ActionContext.getContext().getSession().get("user");
		TUDream dreamBig=udreamService.findBDreamByUser(user,TUDream.BIGDREAM);
		
		model=ggoodsService.findGoodsById(model.getGoodsId());	
		
		TUDreamContent dc=udreamcontentService.findByDreamAndGoods(dreamBig,model);
		if(dc!=null){
			is_dreamed=true;//已经添加到心愿单
		}
		else{
			is_dreamed=false;
		}
		Collection< TGGoodsImg>imgs=model.getTGGoodsImgs();
		
		
		TGCategory cate=model.getTGCategory();
		while(cate.getParent()!=null){
			cate=cate.getParent();
		}
		if(user!=null){
			TUPocket pocket=upocketService.findByUserAndName(user, cate.getCatName());
			Collection<TUCollection>collectionList=pocket.getTUCollections();
			ActionContext.getContext().put("collectionList", collectionList);
		}
		List<String> urls=new ArrayList<String>();
		for(TGGoodsImg img:imgs)
		{
			urls.add(img.getValue());
			
		}
		ActionContext.getContext().put("urls", urls);
			return "show";
	}
	public String collection(){
		
		TGGoods g=ggoodsService.findGoodsById(model.getGoodsId());
		TUCollection c =ucollectionService.findById(collectionId);
		TUCollectionContent ucc=ucollectioncontentService.findByCollGoods(c,g);
		if(ucc==null){
			TUCollectionContent cc=new TUCollectionContent();
			cc.setTGGoods(g);
			cc.setTUCollection(c);
			ucollectioncontentService.save(cc);
		}
		
		TUPocket pocket=c.getTUPocket();
		TUPocketContent upc=upocketcontentService.findByPocketGoods(pocket,g);
		if(upc==null){
			TUPocketContent pc=new TUPocketContent();
			pc.setTGGoods(g);
			pc.setTUPocket(pocket);
			upocketcontentService.save(pc);
		}
		
		
		
		
		return "collection";
	}
	public String dream(){
		TUUser user=(TUUser) ActionContext.getContext().getSession().get("user");
		TUDream dreamBig=udreamService.findBDreamByUser(user,TUDream.BIGDREAM);
		if(dreamBig==null){
			dreamBig=new TUDream();
			dreamBig.setTUUser(user);
			dreamBig.setType(TUDream.BIGDREAM);
			udreamService.save(dreamBig);
		}
		TUDreamContent dc=new TUDreamContent();
		TGGoods goods=ggoodsService.findGoodsById(model.getGoodsId());
		dc.setTGGoods(goods);
		dc.setTUDream(dreamBig);
		udreamcontentService.save(dc);
		is_dreamed=true;
		
		return "dream";
	}
	public String Nodream(){
		TUUser user=(TUUser) ActionContext.getContext().getSession().get("user");
		TUDream dreamBig=udreamService.findBDreamByUser(user,TUDream.BIGDREAM);
		
		TGGoods goods=ggoodsService.findGoodsById(model.getGoodsId());
		TUDreamContent dc=udreamcontentService.findByDreamAndGoods(dreamBig, goods);
		
		udreamcontentService.delete(dc);
		is_dreamed=false;
		
		return "Nodream";
	}
	public String ajaxCollection() throws IOException{

		HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpServletResponse response = ServletActionContext.getResponse();
		String nameColl="";
		try {
			nameColl = IOUtils.toString(request.getInputStream());
			System.out.println("nameColl"+nameColl);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			
			e1.printStackTrace();
		}
		nameColl = URLDecoder.decode(nameColl,"UTF-8");  //传递中文解码
	
		String [] nameId=nameColl.split("_");
		TUUser user=(TUUser) ActionContext.getContext().getSession().get("user");
		String name=nameId[0];
		
		TUCollection uc=ucollectionService.findByUserAndName(user, name);
		if(uc!=null){
			response.setCharacterEncoding("utf-8");
			response.getWriter().print("");
			System.out.println("weikong ");
		}
		else{
			Integer id=Integer.parseInt(nameId[1]);
			System.out.println("name="+name);
			TUCollection c=new TUCollection();
			
			TGGoods goods=ggoodsService.findGoodsById(id);
			TGCategory cate=goods.getTGCategory();
			System.out.println(cate.getCatName()+"前");
			while(cate.getParent()!=null){
				cate=cate.getParent();
			}
			System.out.println(cate.getCatName()+"后");
			TUPocket pocket=new TUPocket();
			if(cate.getCatName().equals("时尚")){
				pocket=upocketService.findByUserAndName(user,"时尚");
				System.out.println(pocket.getPocketId()+"id");
			}
			if(cate.getCatName().equals("美妆")){
				pocket=upocketService.findByUserAndName(user,"美妆");
			}
			if(cate.getCatName().equals("家居")){
				pocket=upocketService.findByUserAndName(user,"家居");
			}
			
			c.setCollTime(new Date());
			c.setName(name);
			c.setTUPocket(pocket);
			c.setTUUser(user);
			c.setUpdateTime(new Date());
			System.out.println("zhe");
			
			ucollectionService.save(c);
			TUCollection cc=ucollectionService.findByUserAndName(user,name);
			
			System.out.println("na ");
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(name+"_"+cc.getCollId());
		}
		
		
	
		return null;
	}
	
	
	public String test(){
		
		return "test";
	}

	public void Ajaxtest() throws IOException{
		PageBean pagebean=new PageBean();
		QueryHelper qp=new QueryHelper(TGGoods.class, "good").addCondition("good.TGBrand.brandId=?",15);//---
		pagebean = gbrandService.getPageBean( getPageNum(),  10, qp);
		List<TGGoods> lgg=pagebean.getRecordList();
		List<TGGoods> tepmnew =new ArrayList<TGGoods>();
		
		for(TGGoods g:lgg){
			System.out.println(g.getTGGoodsImgs().size()+"==============");
		//	tepmnew.add(g);
			TGGoods ggg=new TGGoods();
			ggg.setGoodsId(g.getGoodsId());
			ggg.setImgSmall(g.getImgSmall());
			tepmnew.add(ggg);
		}
		pagebean.setRecordList(tepmnew);
	//	JSONArray jsonlgg  = JSONArray.fromObject( tepmnew ); 
		//JSONArray jsonArray  = JSONArray.fromObject( tepmnew ); 
		//System.out.println("jsonArray"+jsonArray);
		System.out.println(pagebean.getRecordList().size());
	JSONObject jsonObject = JSONObject.fromObject( pagebean );  
 
		/*List<TGGoods> lgoods=new ArrayList<TGGoods>();
		
		TGGoods goods=new TGGoods();
		TGGoods g2=new TGGoods();
		goods.setGoodsName("json");
		g2.setGoodsName("list");
		lgoods.add(goods);
		lgoods.add(g2);
		JSONArray jsonArray  = JSONArray.fromObject( lgoods ); 
		jsonArray.get(1);
		System.out.println( jsonArray.get(1));   */
	HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpServletResponse response = ServletActionContext.getResponse();
		String pageNumber="";
		try {
			pageNumber = IOUtils.toString(request.getInputStream());
			System.out.println("pageNumber="+pageNumber);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			
			e1.printStackTrace();
		}
//		System.out.println(jsonlgg);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(jsonObject);
		
		
	}
	
	
		
	//----------------------------------------------------
	private Integer brandId;

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getSelectRange() {
		return selectRange;
	}

	public void setSelectRange(String selectRange) {
		this.selectRange = selectRange;
	}
	public Integer getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}
	public boolean isIs_dreamed() {
		return is_dreamed;
	}
	public void setIs_dreamed(boolean is_dreamed) {
		this.is_dreamed = is_dreamed;
	}



	
	
	
	
}
