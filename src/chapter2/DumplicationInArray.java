/**
 * @description 长度为n的数组里所有数字都在0~n-1的范围内，找出数组中重复数字
 */
package chapter2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DumplicationInArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] intArray = new int[]{2,3,1,0,2,5,3};
		Set<Integer> returnSet = getDumplications(intArray);
		if(returnSet != null) {
			for(Iterator<Integer> iterator = returnSet.iterator();iterator.hasNext();) {
				System.out.print(iterator.next() + " ");
			}
			System.out.println("所有数字均是重复的····");
		}
	}

	/**
	 * 
	 * @param intArray 数组
	 * @return
	 * @description 数组的hash表特性：按数值与位置的对应关系交换数组内元素（不使用额外的空间），获取数组中全部重复数字
	 */
	public static Set<Integer> getDumplications(int[] intArray) {
		//函数输入验证
		if(intArray == null || intArray.length <= 0) {
			return null;
		}
		
		//用户输入验证
		for(int i=0; i<intArray.length; i++) {
			if(intArray[i] < 0 || intArray[i] > intArray.length -1) {
				return null;
			}
		}
		
		//返回集合（HashSet底层使用HashMap来保存所有元素，通过重写其equals和hashCode方法添加不重复元素）
		Set<Integer> returnSet = new HashSet<Integer>();
		
		for(int i=0; i<intArray.length; i++) {
			//数值与其位置不对应（相等） ->先判断待交换的两数字是否相等（避免死循环）后进行交换
			while(intArray[i] != i) {
				//判断交换位置的数值是否相等
				if(intArray[i] == intArray[intArray[i]]) {
					returnSet.add(intArray[i]);
					break;
				} else {
					//交换位置
					int temp = intArray[i];
					intArray[i] = intArray[temp];
					intArray[temp] = temp;
				}
			}
		}
		return returnSet;
	}
}
