package cn.ssh.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.ssh.base.BaseAction;
import cn.ssh.domain.PageBean;
import cn.ssh.domain.TGBrand;
import cn.ssh.domain.TGCategory;
import cn.ssh.domain.TGGoods;
import cn.ssh.domain.TUCollection;
import cn.ssh.domain.TUCollectionContent;
import cn.ssh.domain.TUPocket;
import cn.ssh.domain.TUPocketContent;
import cn.ssh.domain.TUUser;
@Controller("uPocketAction")
@Scope("prototype")
public class UPocketAction extends BaseAction<TUPocket>{
		
	private Integer authorId;
	private  Integer brandId;
	private Integer cateId;
	private String pocketType;
	private Integer collId;//左边collection导航选择的collId
	private Integer goodsId;
	private Integer collectionId;//添加产品是选择的collectionId
	
	public String show(){
		if(getPocketType()==null||getPocketType().equals("")){
			setPocketType("时尚");
			System.out.println("null");
		}
		
		TUUser author=uuserService.findById(authorId);
		
		TUUser user=(TUUser) ActionContext.getContext().getSession().get("user");
		
			//准备访问者的coll
				TUPocket pocket=upocketService.findByUserAndName(user, pocketType);
				Collection<TUCollection>collectionList=pocket.getTUCollections();
				ActionContext.getContext().put("collectionList", collectionList);
			
			//准备品牌数据
				List<TGBrand> brandList =new ArrayList<TGBrand>();
				 brandList=gbrandService.findListByUser(authorId);//所有分类品牌
				Collections.sort(brandList);
				
				List<TGBrand>FListBrand=new ArrayList<TGBrand>();//时尚
				List<TGBrand>BListBrand=new ArrayList<TGBrand>();//美妆
				List<TGBrand>HListBrand=new ArrayList<TGBrand>();//家居
				TGCategory fc=gcategoryService.findByName("时尚");
				TGCategory bc=gcategoryService.findByName("美妆");
				TGCategory hc=gcategoryService.findByName("家居");
				gbrandService.findPbanddList(brandList,FListBrand,fc);
				gbrandService.findPbanddList(brandList,BListBrand,bc);
				gbrandService.findPbanddList(brandList,HListBrand,hc);
				
				ActionContext.getContext().put("FListBrand", FListBrand);
				ActionContext.getContext().put("BListBrand", BListBrand);
				ActionContext.getContext().put("HListBrand", HListBrand);
				//准备分类数据
				List<TGCategory> cateList =new ArrayList<TGCategory>();
				cateList=gcategoryService.findListByUser(authorId);//所有分类品牌
				List<TGCategory> FListCate=new ArrayList<TGCategory>();
				List<TGCategory> BListCate=new ArrayList<TGCategory>();
				List<TGCategory> HListCate=new ArrayList<TGCategory>();
				for(TGCategory gc:cateList ){
					TGCategory temp=gc;
					while(temp.getParent()!=null){
						temp=temp.getParent();
					}
					if(temp==fc){
						FListCate.add(gc);
						System.out.println("时尚");
					}
					if(temp==bc){
						BListCate.add(gc);
						System.out.println("美妆");
					}
					if(temp==hc){
						HListCate.add(gc);
						System.out.println("家居");
					}
				}
				ActionContext.getContext().put("FListCate", FListCate);
				ActionContext.getContext().put("BListCate", BListCate);
				ActionContext.getContext().put("HListCate", HListCate);
			//准备collection数据
			TUPocket fp=upocketService.findByUserAndName(author,"时尚");
			TUPocket bp=upocketService.findByUserAndName(author,"美妆");
			TUPocket hp=upocketService.findByUserAndName(author,"家居");
			Collection<TUCollection>listcoll=author.getTUCollections();
			List<TUCollection>FListColl=new ArrayList<TUCollection>();
			List<TUCollection>BListColl=new ArrayList<TUCollection>();
			List<TUCollection>HListColl=new ArrayList<TUCollection>();
			for(TUCollection c:listcoll){
				if(c.getTUPocket()==fp){
					FListColl.add(c);
				}
				if(c.getTUPocket()==bp){
					BListColl.add(c);
				}
				if(c.getTUPocket()==hp){
					HListColl.add(c);
				}

			}
			ActionContext.getContext().put("FListColl", FListColl);
			ActionContext.getContext().put("BListColl", BListColl);
			ActionContext.getContext().put("HListColl", HListColl);
			
			//准备产品数据
			PageBean pageBean=new PageBean();
			if(collId!=null){
				pageBean = ggoodsService.getPageBeanColl( getPageNum(),  getPageSize(),authorId,collId);
			}
			else{
				if(cateId==null&&brandId==null){		
					pageBean = ggoodsService.getPageBean( getPageNum(),  getPageSize(),authorId,getPocketType());
					List<TGGoods> lgs=pageBean.getRecordList();
					for(TGGoods g:lgs){
						System.out.println(g.getGoodsName()+"==============1");
					}
				}
				else{
					if(cateId!=null&&brandId==null){
						pageBean = ggoodsService.getPageBeanC( getPageNum(),  getPageSize(),authorId,cateId,getPocketType());
						List<TGGoods> lgs=pageBean.getRecordList();
						for(TGGoods g:lgs){
							System.out.println(g.getGoodsName()+"==============2");
						}
						TGCategory selectedC=gcategoryService.findById(cateId);
						ActionContext.getContext().put("selectedC", selectedC);
					}
					if(brandId!=null&&cateId==null){
						pageBean = ggoodsService.getPageBeanB( getPageNum(),  getPageSize(),authorId,brandId,getPocketType());
						List<TGGoods> lgs=pageBean.getRecordList();
						for(TGGoods g:lgs){
							System.out.println(g.getGoodsName()+"==============3");
						}
						TGBrand selectedB=gbrandService.findById(brandId);
						ActionContext.getContext().put("selectedB", selectedB);
					}
					if(cateId!=null&&brandId!=null){
						pageBean = ggoodsService.getPageBeanBC( getPageNum(),  getPageSize(),authorId,cateId,brandId,getPocketType());
						List<TGGoods> lgs=pageBean.getRecordList();
						for(TGGoods g:lgs){
							System.out.println(g.getGoodsName()+"==============4");
						}
						TGCategory selectedC=gcategoryService.findById(cateId);
						ActionContext.getContext().put("selectedC", selectedC);
						TGBrand selectedB=gbrandService.findById(brandId);
						ActionContext.getContext().put("selectedB", selectedB);
					}
					
				}
				
			}
			
			ActionContext.getContext().getValueStack().push(pageBean);
			
			if(user.getUserId().equals(author.getUserId())){
				
				return "meshow";
			}
			else{
				
				return "othersshow";
			}
		
		
	}
public String collection(){
		System.out.println(authorId);
		System.out.println(pocketType);
		TGGoods g=ggoodsService.findGoodsById(getGoodsId());
		TUCollection c =ucollectionService.findById(getCollectionId());
		TUCollectionContent ucc=ucollectioncontentService.findByCollGoods(c,g);
		if(ucc==null){
			TUCollectionContent cc=new TUCollectionContent();
			cc.setTGGoods(g);
			cc.setTUCollection(c);
			c.setUpdateTime(new Date());
			ucollectionService.update(c);
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
	
	Integer id=Integer.parseInt(nameId[1]);
	TUCollection c=new TUCollection();
	
	TGGoods goods=ggoodsService.findGoodsById(id);
	
	TGCategory cate=goods.getTGCategory();
	
	while(cate.getParent()!=null){
		cate=cate.getParent();
	}

	TUCollection uc=ucollectionService.findByUserAndCnPn(user, name,cate.getCatName());
	
	if(uc!=null){
		response.setCharacterEncoding("utf-8");
		response.getWriter().print("");
		System.out.println("weikong ");
	}
	else{
		
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
		

		response.setCharacterEncoding("utf-8");
		response.getWriter().print(name+"_"+cc.getCollId());
	}
	
	return null;
}
	public String remove(){
		TUUser user=(TUUser) ActionContext.getContext().getSession().get("user");
		TGGoods goods =ggoodsService.findGoodsById(goodsId);
		TGCategory gc=goods.getTGCategory();
		while(gc.getParent()!=null){
			gc=gc.getParent();
		}
		TUPocket po=upocketService.findByUserAndName(user, gc.getCatName());
		TUPocketContent pc=upocketcontentService.findByPocketGoods(po, goods);
		if(pc!=null){
			upocketcontentService.delete(pc.getId());
		}
		Collection<TUCollection> colls=po.getTUCollections();
		for(TUCollection c:colls){
			TUCollectionContent cc=ucollectioncontentService.findByCollGoods(c, goods);
			if(cc!=null){
				ucollectioncontentService.delete(cc.getId());
			}
		}
		return "remove";
	}

//=========================================================
	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getCateId() {
		return cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

	public String getPocketType() {
		return pocketType;
	}

	public void setPocketType(String pocketType) {
		this.pocketType = pocketType;
	}

	public Integer getCollId() {
		return collId;
	}

	public void setCollId(Integer collId) {
		this.collId = collId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}



}
