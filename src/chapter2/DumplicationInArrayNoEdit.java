/**
 * @description 长度为n+1的数组里所有数字都在1~n的范围内，不修改数组找出重复数字
 */
package chapter2;

import java.util.ArrayList;
import java.util.List;

public class DumplicationInArrayNoEdit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] intArray = new int[]{2,3,5,4,3,2,6,7};
		
		int dumpNum = getDumplication1(intArray);
		System.out.println(dumpNum + " 数字是重复的····");
		
		List<Integer> returnList = getDumplication2(intArray);
		if(returnList != null) {
			for(int i=0; i<returnList.size(); i++) {
				System.out.print(returnList.get(i) + " ");
			}
			System.out.println("所有数字均是重复的····");
		}
	}

	/**
	 * 
	 * @param intArray数组
	 * @return
	 * @description 鸽笼定理 + 分治法 获取重复数字（并非获取全部重复数字）
	 */
	public static int getDumplication1(int[] intArray) {
		//输入验证
		if(intArray.length <= 0 || intArray == null) {
			return -1;
		}

		//变量声明 + 初始化
		int startNum = 1;
		int endNum = intArray.length - 1;
		int middleNum = 0;
		
		//while循环和递归均可
		while(startNum <= endNum) {
			//middleNum是由startNum与endNum决定的，因此middleNum需要在循环内赋值
			middleNum = ((endNum - startNum) >> 1) + startNum;
			//循环结束判断
			if(startNum == endNum) {
				if(getCount(intArray, startNum, endNum) > 1) {
					return endNum;
				} else {
					break;
				}
			}
			
			//动态改变判断区间边界，用于二分查找
			if(getCount(intArray, startNum, middleNum) > (middleNum - startNum + 1)) {
				endNum = middleNum;
			} else {
				startNum = middleNum + 1;
			}
		}
		return -1;
	}
	
	/**
	 * 
	 * @param intArray 数组
	 * @param startNum 区间起始位置
	 * @param endNum 区间结束位置
	 * @return
	 * @description 获取在区间内的数组数字个数
	 */
	public static int getCount(int[] intArray, int startNum, int endNum) {
		//输入验证
		if(intArray == null) {
			return 0;
		}
		
		int count = 0;
		for(int i=0; i<intArray.length; i++) {
			if(intArray[i] >= startNum && intArray[i] <= endNum) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * 
	 * @param intArray 数组
	 * @return
	 * @description 辅助数组 -> 特殊的哈希表（key：下标；value：数值）（获取全部重复数字）
	 */
	public static List<Integer> getDumplication2(int[] intArray) {
		if(intArray == null) {
			return null;
		}
		//辅助数组（空间换时间）
		int[] tempArray = new int[intArray.length];
		//返回列表
		List<Integer> returnList = new ArrayList<Integer>();
		for(int i=0; i<intArray.length; i++) {
			if(tempArray[intArray[i]] == 0) {
				tempArray[intArray[i]] = 1;
			} else {
				returnList.add(intArray[i]);
			}
		}
		return returnList;
	}
}
