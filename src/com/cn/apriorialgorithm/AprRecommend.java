package com.cn.apriorialgorithm;

import java.util.List;

import com.cn.store.RecommendBean;

public class AprRecommend {
	public List<RecommendBean> aprrecommend(){
		AprioriRecommend AprioriRecommend=new AprioriRecommend();
		TestAprioriAlgorithm testapriorialgorithm=new TestAprioriAlgorithm();
		List<AprioriRecommendBean> order_ID_list=AprioriRecommend.getOrder_ID();
		List<List<RecommendBean>> creatlist=AprioriRecommend.getCreatList(order_ID_list);
		testapriorialgorithm.create(creatlist);
		 try {
			 testapriorialgorithm.setUp();
		 } catch (Exception e) {
		// TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		 List<String> outlist=testapriorialgorithm.testGetFreq2ItemSet();
		 List<RecommendBean> recommendlist=AprioriRecommend.getRecommendList(outlist);
		return recommendlist;
	}

}
