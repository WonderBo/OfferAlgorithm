/**
 * @description 判断在一个矩阵中是否存在一条包含某字符串所有字符的路径(可以从矩阵任一格开始，能上下左右移动一格，路径不能重复进入矩阵一格子)
 */
package chapter2;

public class StringPathInMatrix {

	/**
	 * 
	 * @param charMatrix	字符数组
	 * @param rows			行数
	 * @param columns		列数
	 * @param str			字符串
	 * @return boolean		是否匹配
	 * @throws Exception
	 * @description 回溯法求解问题(递归实现)
	 */
	public static boolean hasPath(char[] charMatrix, int rows, int columns, String str) throws Exception {
		//输入验证
		if(charMatrix == null || rows < 1 || columns < 1 || str == null) {
			throw new Exception("参数错误");
		}

		//由于路径不能重复进入矩阵的格子，因此需要定义和字符矩阵一样大小的的布尔值矩阵，以标识路径是否已经进入每个格子
		boolean[] visitedMatrix = new boolean[rows * columns];
		//字符串下标
		int pathIndex = 0;
		//双重循环：row和column为矩阵下标
		for(int row = 0; row < rows; row++) {
			for(int column = 0; column < columns; column++) {
				if(hasPathCore(charMatrix, rows, columns, row, column, str, pathIndex, visitedMatrix)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 
	 * @param charMatrix	字符数组
	 * @param rows			行数
	 * @param columns		列数
	 * @param row			矩阵行下标
	 * @param column		矩阵列下标
	 * @param str			字符串
	 * @param pathIndex		字符串下标
	 * @param visitedMatrix	是否进入布尔值矩阵
	 * @return boolean		是否匹配
	 * @description 递归实现回溯法问题，关键在于：什么条件下执行递归，回溯的目的是什么
	 */
	public static boolean hasPathCore(char[] charMatrix, int rows, int columns, int row, int column, String str, int pathIndex, boolean[] visitedMatrix) {
		//递归结束条件/边界条件
		if(pathIndex == str.length()) {
			return true;
		}

		boolean hasPath = false;
		//符合条件则前进 -> 递归
		if(row >= 0 && row < rows && column >= 0 && column < columns && charMatrix[row * columns + column] == str.charAt(pathIndex) && !visitedMatrix[row * columns + column]) {
			pathIndex++;
			visitedMatrix[row * columns + column] = true;

			// || 表示回溯问题该步骤的多个选项
			hasPath = hasPathCore(charMatrix, rows, columns, row - 1, column, str, pathIndex, visitedMatrix) 
					|| hasPathCore(charMatrix, rows, columns, row, column - 1, str, pathIndex, visitedMatrix) 
					|| hasPathCore(charMatrix, rows, columns, row + 1, column, str, pathIndex, visitedMatrix) 
					|| hasPathCore(charMatrix, rows, columns, row, column + 1, str, pathIndex, visitedMatrix);
			
			//前进后不符合条件则后退 -> 回溯(若不回溯，执行 || 后面的递归会产生错误的结果)
			if(!hasPath) {
				pathIndex--;
				visitedMatrix[row * columns + column] = false;
			}
		}

		return hasPath;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] charMatrix = new char[]{'a', 'b', 't', 'g', 'c', 'f', 'c', 's', 'j', 'd', 'e', 'h'};
		try {
			System.out.println(hasPath(charMatrix, 3, 4, "bfce"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
