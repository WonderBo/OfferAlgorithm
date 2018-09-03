/**
 * @description 字符流中第一个只出现一次的字符
 */
package chapter5;

public class FirstCharInStream {
	int[] firstPositionArray = new int[256];	// 辅助数组，存储字符第一次出现的位置
	int index;		// 字符下标（从0开始递增）
	
	public FirstCharInStream() {
		// 初始化辅助数组
		for(int i = 0; i < firstPositionArray.length; i++) {
			firstPositionArray[i] = -1;
		}
	}
	
	/**
	 * 
	 * @param c
	 * @description 字符第一次出现则存储第一次出现位置，第二次出现则更新标记为-2，后面更多次出现则不变
	 */
	public void insert(char c) {
		if(firstPositionArray[c] == -1) {
			firstPositionArray[c] = index;
		} else if(firstPositionArray[c] >= 0) {
			firstPositionArray[c] = -2;
		}
		
		index ++;
	}
	
	/**
	 * 
	 * @return
	 * @description 由于字符流的动态性，因此字符流不可用于遍历，因此使用辅助数组存储字符第一次出现的位置，遍历辅助数组来获取第一个只出现一次的字符
	 */
	public char getFirstCharInStream() {
		char firstChar = '\0';
		int minIndex = Integer.MAX_VALUE;
		for(int i = 0; i < firstPositionArray.length; i++) {
			if(firstPositionArray[i] >= 0 && firstPositionArray[i] < minIndex) {
				minIndex = firstPositionArray[i];
				firstChar = (char)i;
			}
		}
		
		return firstChar;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FirstCharInStream firstCharInStream = new FirstCharInStream();
		firstCharInStream.insert('a');
		firstCharInStream.insert('b');
		firstCharInStream.insert('a');
		firstCharInStream.insert('c');
		firstCharInStream.insert('c');
		firstCharInStream.insert('d');
		firstCharInStream.insert('e');
		firstCharInStream.insert('f');
		firstCharInStream.insert('f');
		
		System.out.println("第一次出现的字符为：" + firstCharInStream.getFirstCharInStream());
	}

}
