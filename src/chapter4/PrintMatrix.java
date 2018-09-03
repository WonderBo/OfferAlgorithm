/**
 * @description 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
 */
package chapter4;

public class PrintMatrix {

	/**
	 * 
	 * @param matrix
	 * @description 分解复杂问题：打印矩阵 -> 打印一圈
	 * 				注意从外圈到内圈的顺序依次打印的循环起始条件和结束条件
	 */
	public static void printMatrixClockwisely(int[][] matrix) {
		// 输入验证
		if(matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
			return;
		}
		
		// 从外到内打印各圈，注意循环起始条件和结束条件
		int startIndex = 0;
		while(matrix.length > startIndex * 2 && matrix[0].length > startIndex * 2) {
			printMatrixInCircle(matrix, startIndex);
			startIndex ++;
		}
	}
	
	/**
	 * 
	 * @param matrix
	 * @param startIndex
	 * @description 分解复杂问题：打印一圈 -> 打印各边
	 * 				注意顺时针打印一圈各边的前提条件
	 */
	public static void printMatrixInCircle(int[][] matrix, int startIndex) {
		// 确定横向打印和纵向打印的起始下标和结束下标
		int endXIndex = matrix[0].length - 1 - startIndex;
		int endYIndex = matrix.length - 1 - startIndex;
		
		// 从左到右打印一行（总是需要的，无前提条件）
		for(int i = startIndex; i <= endXIndex; i++) {
			System.out.print(matrix[startIndex][i] + " ");
		}
		
		// 从上到下打印一列（终止行号大于起始行号）
		if(endYIndex > startIndex) {
			for(int i = startIndex + 1; i <= endYIndex; i++) {
				System.out.print(matrix[i][endXIndex] + " ");
			}
		}
		
		// 从右到左打印一行（圈内至少有两行两列：终止行号大于起始行号，且终止列号大于起始列号）
		if(endXIndex > startIndex && endYIndex > startIndex) {
			for(int i = endXIndex - 1; i >= startIndex; i--) {
				System.out.print(matrix[endYIndex][i] + " ");
			}
		}
		
		// 从下到上打印一列（圈内至少有三行两列：终止行号比起始行号至少大2，且终止列号大于起始列号）
		if(endYIndex > startIndex + 1 && endXIndex > startIndex) {
			for(int i = endYIndex - 1; i >= startIndex + 1; i--) {
				System.out.print(matrix[i][startIndex] + " ");
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] matrix = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
		System.out.print("顺时针打印矩阵：");
		printMatrixClockwisely(matrix);
	}

}
