/**
 * @description 实现两个函数，分别用来序列化和反序列化二叉树
 */
package chapter4;

class BTreeNode {
	int val;
	BTreeNode left;
	BTreeNode right;
	public BTreeNode(int val) { this.val = val; }
}

public class SerializeBinaryTrees {

	// 由于int类型作为入参是值传递，需要使用成员变量来保存序列化数组的遍历信息
	private static int index;
	
	/**
	 * 
	 * @param rootNode
	 * @return
	 * @description 序列化二叉树：变形的二叉树前序遍历（需要对NULL做标记从而保存二叉树的结构信息，便于进行反序列化）
	 */
	public static String serialize(BTreeNode rootNode) {
		StringBuilder serializeStringBuilder = new StringBuilder();
		// 递归边界条件
		if(rootNode == null) {
			serializeStringBuilder.append("$,");
			return serializeStringBuilder.toString();
		}
		
		// 前序遍历
		serializeStringBuilder.append(rootNode.val + ",");
		serializeStringBuilder.append(serialize(rootNode.left));
		serializeStringBuilder.append(serialize(rootNode.right));
		
		return serializeStringBuilder.toString();
	}
	
	/**
	 * 
	 * @param serializeString
	 * @return
	 * @description 反序列化二叉树：递归方式（同二叉树的前序遍历），需要使用成员变量保存遍历的index信息
	 */
	public static BTreeNode deSerialize(String serializeString) {
		String[] serializeArray = serializeString.split(",");
		// 递归边界条件
		if(serializeArray[index].equals("$")) {
			index ++;
			return null;
		}
		
		// 前序遍历
		BTreeNode node = new BTreeNode(Integer.valueOf(serializeArray[index]));
		index ++;
		node.left = deSerialize(serializeString);
		node.right = deSerialize(serializeString);
		
		return node;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BTreeNode a = new BTreeNode(1);
		BTreeNode b = new BTreeNode(2);
		BTreeNode c = new BTreeNode(3);
		BTreeNode d = new BTreeNode(4);
		BTreeNode e = new BTreeNode(5);
		BTreeNode f = new BTreeNode(6);
		a.left = b;
		b.left = d;
		a.right = c;
		c.left = e;
		c.right = f;
		
		String serializeString = serialize(a);
		System.out.println("序列化结果：" + serializeString);
		
		BTreeNode root = deSerialize(serializeString);
		System.out.println("反序列化后再序列化：" + serialize(root));
	}

}
