/**
 * @description 机器人的运动范围（m行n列方格，从(0，0)开始出发，可上下左右移动，但不能进入行坐标和列坐标的数位之和大于k的格子）
 */
package chapter2;

public class RobotMove {

	/**
	 * 
	 * @param rows		行数
	 * @param columns	列数
	 * @param k			数位之和阈值
	 * @return int
	 * @description 参数验证等其他额外操作后执行核心操作
	 */
	public static int movingCount(int rows, int columns, int k) {
		//输入/参数验证
		if(rows <= 0 || columns <= 0 || k < 0) {
			return 0;
		}
		
		//声明并动态初始化遍历参考数组
		int[] visitedArr = new int[rows * columns];
		for(int i = 0; i < rows * columns; i++) {
			visitedArr[i] = 0;
		}
		
		//回溯核心
		int count = 0;
		count = movingCountCore(rows, columns, 0, 0, k, visitedArr);
		
		return count;
	}
	
	/**
	 * 
	 * @param rows		行数
	 * @param columns	列数
	 * @param row		当前格子行坐标
	 * @param column	当前格子列坐标
	 * @param k			数位之和阈值
	 * @param visitedArr遍历参考数组
	 * @return int
	 * @description 递归实现回溯法，回溯法关键在于构造树形结构（根，节点， 边/选择， 叶），再进行编程解答
	 */
	public static int movingCountCore(int rows, int columns, int row, int column, int k, int[] visitedArr) {
		int count = 0;
		if(check(rows, columns, row, column, k, visitedArr)) {
			visitedArr[row * columns + column] = 1;
			
			//递归选择
			count = 1 + movingCountCore(rows, columns, row + 1, column, k, visitedArr)
					+ movingCountCore(rows, columns, row, column + 1, k, visitedArr)
					+ movingCountCore(rows, columns, row - 1, column, k, visitedArr)
					+ movingCountCore(rows, columns, row, column - 1, k, visitedArr);
		}
		
		return count;
	}
	
	/**
	 * 
	 * @param rows		行数
	 * @param columns	列数
	 * @param row		当前格子行坐标
	 * @param column	当前格子列坐标
	 * @param k			数位之和阈值
	 * @param visitedArr遍历参考数组
	 * @return boolean
	 * @description 递归必需终结条件，为递归边界条件与递归执行条件的其一或组合，否则将死循环（此处递归执行条件为递归终结条件）
	 */
	public static boolean check(int rows, int columns, int row, int column, int k, int[] visitedArr) {
		if(row >= 0 && column >= 0 && row < rows && column < columns && visitedArr[row * columns + column] != 1 
				&& getDigitSum(row) + getDigitSum(column) <= k) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param num	数字
	 * @return int
	 * @description 求解数字的数位之和
	 */
	public static int getDigitSum(int num) {
		int sum = 0;
		while(num > 0) {
			sum += num % 10;
			num /= 10;
		}
		
		return sum;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count = movingCount(8, 24, 7);
		System.out.println("机器人的运动范围格子数：" + count);
	}

}
