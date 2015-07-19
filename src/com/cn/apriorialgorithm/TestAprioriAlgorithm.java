package com.cn.apriorialgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.cn.store.RecommendBean;

import junit.framework.TestCase;


public class TestAprioriAlgorithm extends TestCase {

	private AprioriAlgorithm apriori;
	private Map<Integer, Set<String>> txDatabase;
	private Float minSup = new Float("0.01");
	private Float minConf = new Float("0.02");
	public  void setUp() throws Exception {
		
		//create(); // 构造事务数据库
		apriori = new AprioriAlgorithm(txDatabase, minSup, minConf);
	}

	public void create(List<List<RecommendBean>> creatlist) {
		
		txDatabase = new HashMap<Integer, Set<String>>();
		for(int i=1;i<creatlist.size();i++){
			Set<String> set = new TreeSet<String>();
			for(int j=0;j<creatlist.get(i).size();j++){
				set.add(creatlist.get(i).get(j).getFood_ID());
			}
			txDatabase.put(i, set);
		}
	}


	public void testFreq1ItemSet() {
		System.out.println("挖掘频繁1-项集 : " + apriori.getFreq1ItemSet());
	}


	public void testAprioriGen() {
		System.out.println(
				"候选频繁2-项集 ： " +
						this.apriori.aprioriGen(1, this.apriori.getFreq1ItemSet().keySet())
				);
	}


	public List<String> testGetFreq2ItemSet() {
		List<String> list=new ArrayList<String>();
		for (Set<String> key : this.apriori.getFreqKItemSet(2, this.apriori.getFreq1ItemSet().keySet()).keySet()) {  
		    for (String str : key) {  
		    	 list.add(str);
		  }  
		} 
		return list;
	}


	public void testGetFreq3ItemSet() {
		System.out.println(
				"挖掘频繁3-项集 ：" +
						this.apriori.getFreqKItemSet(
								3,
								this.apriori.getFreqKItemSet(2, this.apriori.getFreq1ItemSet().keySet()).keySet()
								)
				);
	}


	public void testGetFreqItemSet() {
		this.apriori.mineFreqItemSet(); // 挖掘频繁项集
		System.out.println("挖掘频繁项集 ：" + this.apriori.getFreqItemSet());
	}


	public void testMineAssociationRules() {
		this.apriori.mineFreqItemSet(); // 挖掘频繁项集
		this.apriori.mineAssociationRules();
		System.out.println("挖掘频繁关联规则 ：" + this.apriori.getAssiciationRules());
	}
}