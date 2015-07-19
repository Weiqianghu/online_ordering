package com.cn.apriorialgorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AprioriAlgorithm {
    private Map<Integer, Set<String>> txDatabase; // �������ݿ�
    private Float minSup; // ��С֧�ֶ�
    private Float minConf; // ��С���Ŷ�
    private Integer txDatabaseCount; // �������ݿ��е�������

    private Map<Integer, Set<Set<String>>> freqItemSet; // Ƶ�������
    private Map<Set<String>, Set<Set<String>>> assiciationRules; // Ƶ���������򼯺�

    public AprioriAlgorithm(Map<Integer, Set<String>> txDatabase,Float minSup,Float minConf) {
            this.txDatabase = txDatabase;
            this.minSup = minSup;
            this.minConf = minConf;
            this.txDatabaseCount = this.txDatabase.size();
            freqItemSet = new TreeMap<Integer, Set<Set<String>>>();
            assiciationRules = new HashMap<Set<String>, Set<Set<String>>>();
    }

    public Map<Set<String>, Float> getFreq1ItemSet() {
        Map<Set<String>, Float> freq1ItemSetMap = new HashMap<Set<String>, Float>();
        Map<Set<String>, Integer> candFreq1ItemSet = this.getCandFreq1ItemSet();
        Iterator<Map.Entry<Set<String>, Integer>> it = candFreq1ItemSet.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Set<String>, Integer> entry = it.next();
            // ����֧�ֶ�
            Float supported = new Float(entry.getValue().toString())/new Float(txDatabaseCount);
            if(supported>=minSup) {
                freq1ItemSetMap.put(entry.getKey(), supported);
            }
        }
        return freq1ItemSetMap;
    }

    public Map<Set<String>, Integer> getCandFreq1ItemSet() {
        Map<Set<String>, Integer> candFreq1ItemSetMap = new HashMap<Set<String>, Integer>();
        Iterator<Map.Entry<Integer, Set<String>>> it = txDatabase.entrySet().iterator();
        // ͳ��֧���������ɺ�ѡƵ��1-�
        while(it.hasNext()) {
            Map.Entry<Integer, Set<String>> entry = it.next();
            Set<String> itemSet = entry.getValue();
            for(String item : itemSet) {
                Set<String> key = new HashSet<String>();
                key.add(item.trim());
                if(!candFreq1ItemSetMap.containsKey(key)) {
                    Integer value = 1;
                    candFreq1ItemSetMap.put(key, value);
                }
                else {
                    Integer value = 1+candFreq1ItemSetMap.get(key);
                    candFreq1ItemSetMap.put(key, value);
                }
            }
        }
        return candFreq1ItemSetMap;
    }

  public Set<Set<String>> aprioriGen(int m, Set<Set<String>> freqMItemSet) {
    Set<Set<String>> candFreqKItemSet = new HashSet<Set<String>>();
    Iterator<Set<String>> it = freqMItemSet.iterator();
    Set<String> originalItemSet = null;
    while(it.hasNext()) {
      originalItemSet = it.next();
      Iterator<Set<String>> itr = this.getIterator(originalItemSet, freqMItemSet);
        while(itr.hasNext()) {
          Set<String> identicalSet = new HashSet<String>(); // �������ͬԪ�صļ���(���ϵĽ�����)
          identicalSet.addAll(originalItemSet);
          Set<String> set = itr.next();
          identicalSet.retainAll(set); // identicalSet��ʣ�µ�Ԫ����identicalSet��set�����й��е�Ԫ��
          if(identicalSet.size() == m-1) { // (k-1)-���k-2����ͬ
              Set<String> differentSet = new HashSet<String>(); // �������ͬԪ�صļ���(���ϵĲ�����)
            differentSet.addAll(originalItemSet);
            differentSet.removeAll(set); // ��Ϊ��k-2����ͬ����differentSet��һ��ʣ��һ��Ԫ�أ���differentSet��СΪ1
            differentSet.addAll(set); // �����ѡk-���һ��Ԫ��(set��СΪk-1,differentSet��СΪk)
            candFreqKItemSet.add(differentSet); // �����ѡk-�����
          }
        }
      }
    return candFreqKItemSet;
  }
  private Iterator<Set<String>> getIterator(Set<String> itemSet, Set<Set<String>> freqKItemSet) {
    Iterator<Set<String>> it = freqKItemSet.iterator();
    while(it.hasNext()) {
      if(itemSet.equals(it.next())) {
        break;
      }
    }
    return it;
  }

public Map<Set<String>, Float> getFreqKItemSet(int k, Set<Set<String>> freqMItemSet) {
	Map<Set<String>, Integer> candFreqKItemSetMap = new HashMap<Set<String>, Integer>();
	// ����aprioriGen�������õ���ѡƵ��k-�
	Set<Set<String>> candFreqKItemSet = this.aprioriGen(k-1, freqMItemSet);

	// ɨ���������ݿ�
	Iterator<Map.Entry<Integer, Set<String>>> it = txDatabase.entrySet().iterator();
	// ͳ��֧����
	while(it.hasNext()) {
		Map.Entry<Integer, Set<String>> entry = it.next();
		Iterator<Set<String>> kit = candFreqKItemSet.iterator();
		while(kit.hasNext()) {
			Set<String> kSet = kit.next();
			Set<String> set = new HashSet<String>();
			set.addAll(kSet);
			set.removeAll(entry.getValue()); // ��ѡƵ��k-����������ݿ���Ԫ������Ԫ��
			if(set.isEmpty()) { // �������setΪ�գ�֧������1
				if(candFreqKItemSetMap.get(kSet) == null) {
					Integer value = 1;
					candFreqKItemSetMap.put(kSet, value);
				}
				else {
					Integer value = 1+candFreqKItemSetMap.get(kSet);
					candFreqKItemSetMap.put(kSet, value);
				}
			}
		}
	}
	// ����֧�ֶȣ�����Ƶ��k-���������
	return support(candFreqKItemSetMap);
	}


	public Map<Set<String>, Float> support(Map<Set<String>, Integer> candFreqKItemSetMap) {
		Map<Set<String>, Float> freqKItemSetMap = new HashMap<Set<String>, Float>();
		Iterator<Map.Entry<Set<String>, Integer>> it = candFreqKItemSetMap.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<Set<String>, Integer> entry = it.next();
			// ����֧�ֶ�
			Float supportRate = new Float(entry.getValue().toString())/new Float(txDatabaseCount);
			if(supportRate<minSup) { // �����������С֧�ֶȣ�ɾ��
				it.remove();
			}
			else {
				freqKItemSetMap.put(entry.getKey(), supportRate);
			}
		}
		return freqKItemSetMap;
	}


	public void mineFreqItemSet() {
		// ����Ƶ��1-�
		Set<Set<String>> freqKItemSet = this.getFreq1ItemSet().keySet();
		freqItemSet.put(1, freqKItemSet);
		// ����Ƶ��k-�(k>1)
		int k = 2;
		while(true) {
			Map<Set<String>, Float> freqKItemSetMap = this.getFreqKItemSet(k, freqKItemSet);
			if(!freqKItemSetMap.isEmpty()) {
				this.freqItemSet.put(k, freqKItemSetMap.keySet());
				freqKItemSet = freqKItemSetMap.keySet();
			}
			else {
				break;
			}
			k++;
		}
	}


	public void mineAssociationRules() {
		freqItemSet.remove(1); // ɾ��Ƶ��1-�
		Iterator<Map.Entry<Integer, Set<Set<String>>>> it = freqItemSet.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<Integer, Set<Set<String>>> entry = it.next();
			for(Set<String> itemSet : entry.getValue()) {
				// ��ÿ��Ƶ������й���������ھ�
				mine(itemSet);
			}
		}
	}


	public void mine(Set<String> itemSet) {
		int n = itemSet.size()/2; // ���ݼ��ϵĶԳ��ԣ�ֻ��Ҫ�õ�һ������Ӽ�
		for(int i=1; i<=n; i++) {
			// �õ�Ƶ���Ԫ��itemSet����Ϊ���������Ӽ�����
			Set<Set<String>> properSubset = ProperSubsetCombination.getProperSubset(i, itemSet);
			// �����������Ӽ������е�ÿ�����������ȡ����Ӧ�Ľ�������Ӷ���һ���ھ�Ƶ����������
			for(Set<String> conditionSet : properSubset) {
				Set<String> conclusionSet = new HashSet<String>();
				conclusionSet.addAll(itemSet);
				conclusionSet.removeAll(conditionSet); // ɾ�������д��ڵ�Ƶ����
				confide(conditionSet, conclusionSet); // ���ü������Ŷȵķ����������ھ��Ƶ����������
			}
		}
	}


	public void confide(Set<String> conditionSet, Set<String> conclusionSet) {
		// ɨ���������ݿ�
		Iterator<Map.Entry<Integer, Set<String>>> it = txDatabase.entrySet().iterator();
		// ͳ�ƹ�������֧�ּ���
		int conditionToConclusionCnt = 0; // ��������(������Ƴ������)����
		int conclusionToConditionCnt = 0; // ��������(������Ƴ������)����
		int supCnt = 0; // ��������֧�ּ���
		while(it.hasNext()) {
			Map.Entry<Integer, Set<String>> entry = it.next();
			Set<String> txSet = entry.getValue();
			Set<String> set1 = new HashSet<String>();
			Set<String> set2 = new HashSet<String>();
			set1.addAll(conditionSet);

			set1.removeAll(txSet); // ���ϲ����㣺set-txSet
			if(set1.isEmpty()) { // ���setΪ�գ�˵���������ݿ��а�������Ƶ����conditionSet
				// ����
				conditionToConclusionCnt++;
			}
			set2.addAll(conclusionSet);
			set2.removeAll(txSet); // ���ϲ����㣺set-txSet
			if(set2.isEmpty()) { // ���setΪ�գ�˵���������ݿ��а�������Ƶ����conclusionSet
				// ����
				conclusionToConditionCnt++;

			}
			if(set1.isEmpty() && set2.isEmpty()) {
				supCnt++;
			}
		}
		// �������Ŷ�
		Float conditionToConclusionConf = new Float(supCnt)/new Float(conditionToConclusionCnt);
		if(conditionToConclusionConf>=minConf) {
			if(assiciationRules.get(conditionSet) == null) { // ����������Ը�����Ƶ���Ϊ�����Ĺ�������
				Set<Set<String>> conclusionSetSet = new HashSet<Set<String>>();
				conclusionSetSet.add(conclusionSet);
				assiciationRules.put(conditionSet, conclusionSetSet);
			}
			else {
				assiciationRules.get(conditionSet).add(conclusionSet);
			}
		}
		Float conclusionToConditionConf = new Float(supCnt)/new Float(conclusionToConditionCnt);
		if(conclusionToConditionConf>=minConf) {
			if(assiciationRules.get(conclusionSet) == null) { // ����������Ըý���Ƶ���Ϊ�����Ĺ�������
				Set<Set<String>> conclusionSetSet = new HashSet<Set<String>>();
				conclusionSetSet.add(conditionSet);
				assiciationRules.put(conclusionSet, conclusionSetSet);
			}
			else {
				assiciationRules.get(conclusionSet).add(conditionSet);
			}
		}
	}

	public Map<Integer, Set<Set<String>>> getFreqItemSet() {
		return freqItemSet;
	}

	public Map<Set<String>, Set<Set<String>>> getAssiciationRules() {
		return assiciationRules;
	}
}