/**
 * @description 在每行从左到右递增，每列从上到下递增排序的二维数组中，判断数组中是否含有输入的整数
 */
package chapter2;

public class FindInTwoDimensionArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] twoDimIntArray = new int[][]{{1,2,8,9}, {2,4,9,12}, {4,7,10,13}, {6,8,11,15}};
		boolean result = findNum(twoDimIntArray, 1111);
		System.out.println("查询结果：" + result);
	}
	
	/**
	 * 
	 * @param twoDimIntArray 二维数组
	 * @param inputNum 输入数字
	 * @return
	 * @description 类似于动态规划的思想，令右上角顶点为判断条件通过剔除行或者列不断简化问题（关键在于确定该拐点）
	 */
	public static boolean findNum(int[][] twoDimIntArray, int inputNum) {
		//输入验证
		if(twoDimIntArray == null || twoDimIntArray.length <= 0 || twoDimIntArray[0].length <= 0) {
			return false;
		}
		
		//变量初始化
		boolean result = false;
		int row = 0;
		int column = twoDimIntArray[0].length - 1;
		
		//根据条件选择使用while循环或者递归
		while(row <= (twoDimIntArray.length - 1) && column >= 0) {
			//根据输入值与拐点值的比较结果，选择返回结果或者对数组的行或列进行剔除进而简化问题
			if(twoDimIntArray[row][column] == inputNum) {
				result = true;
				break;
			} else if(twoDimIntArray[row][column] > inputNum) {
				column--;
			} else {
				row++;
			}
		}
		return result;
	}

}
