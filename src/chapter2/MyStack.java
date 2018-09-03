/**
 * @description 栈实现
 */
package chapter2;

public class MyStack<E> {
	//数据域
	private Object[] data = null;
	//栈顶指针，初始化为-1
	private int top = -1;
	//栈最大容量
	private int maxSize = 0;
	
	/**
	 * 
	 * @param initialSize 栈初始化空间大小
	 * @description 栈初始化
	 */
	public MyStack(int initialSize) {
		if(initialSize >= 0) {
			//初始化数据域数组
			this.data = new Object[initialSize];
			//设置栈最大容量
			this.maxSize = initialSize;
			this.top = -1;
		}else {
			System.out.println("栈初始化错误");
		}
	}
	
	/**
	 * @description 栈初始化并默认设置栈容量为10
	 */
	public MyStack() {
		this(10);
	}
	
	/**
	 * 
	 * @return
	 * @description 根据栈顶值判断,如果栈顶指针没有更新，则为空栈
	 */
	public boolean isEmpty() {
		if(top == -1) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return
	 * @description //根据栈顶值判断，如果栈顶指针大于最大容量，则为满栈
	 */
	public boolean isMax() {
		if(top >= maxSize - 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param e 元素
	 * @return
	 * @description 入栈
	 */
	public boolean push(E e) {
		//先检查是否满栈
		if(isMax()) {
			System.out.println("已经满栈，无法入栈");
			return false;
		}
		//再移动栈顶指针
		top++;
		//最后将元素添加到域中
		data[top] = e;
		return true;
	}
	
	/**
	 * 
	 * @return 栈顶元素
	 * @description 出栈
	 */
	@SuppressWarnings("unchecked")
	public E pop() {
		//先检查是否空栈
		if(isEmpty()) {
			System.out.println("已经空栈，无法出栈");
			return null;
		}
		//再取出域中元素
		E e = (E) data[top];
		//最后移动栈顶指针
		top--;
		return e;
	}
	
	/**
	 * 
	 * @return 栈顶元素
	 * @description 获取栈顶元素值
	 */
	@SuppressWarnings("unchecked")
	public E getTop() {
		if(isEmpty()) {
			System.out.println("空栈无任何元素");
			return null;
		}
		return (E) data[top];
	}
	
	/**
	 * 
	 * @param e 元素
	 * @return 元素位置
	 * @description 根据栈顶至栈底(-1)遍历栈，获取最先出现元素e的位置
	 */
	public int indexOf(E e) {
		while(top != -1) {
			if(getTop().equals(e)) {
				return top;
			}
			top--;
		}
		return -1;
	}
	
	/**
	 * 
	 * @return
	 * @description 栈内元素数量
	 */
	public int size() {
		return top + 1;
	}
	
	/**
	 * 
	 * @return
	 * @description 栈最大容量
	 */
	public int getStackSize() {
		return maxSize;
	}
	
	/**
	 * @description 遍历并打印栈内元素
	 */
	public void display() {
		while(top != -1) {
			System.out.println(data[top]);
			top--;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyStack<Integer> stack = new MyStack<Integer>(5);
		for(int i=0; i<5; i++) {
			stack.push(i);
		}
		System.out.println(stack.pop());
		System.out.println(stack.getTop());
		System.out.println(stack.size());
		System.out.println(stack.getStackSize());
	}

}
