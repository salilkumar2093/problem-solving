import java.util.Comparator;
import java.util.PriorityQueue;

public class HuffmanCoding {
	public static void main(String... args) {
		PriorityQueue<HuffmanNode> queue = new PriorityQueue<HuffmanNode>(new MyComparator());
		String str = "11131124444444211333321";
		String s1 = "1234";
		String freq = "8356";
		for (int i = 0; i < s1.length(); i++) {
			// creating a Huffman node object
			HuffmanNode hn = new HuffmanNode();
			hn.ch = s1.charAt(i);
			hn.freq = Character.getNumericValue(freq.charAt(i));
			hn.left = null;
			hn.right = null;
			queue.add(hn);
		}
		while (queue.size() > 1) {
			HuffmanNode first = queue.poll();
			HuffmanNode second = queue.poll();

			HuffmanNode third = new HuffmanNode();
			third.freq = first.freq + second.freq;
			third.ch = '$';
			third.left = first;
			third.right = second;
			queue.add(third);
		}
		System.out.println(queue.peek().freq);
		printRecur(queue.peek(), "");
		huffManDecode(queue.peek(), "0");
		// create a root node F
	}

	static void printRecur(HuffmanNode root, String s) {
		if (root == null)
			return;
		if (root.left == null && root.right == null) {
			System.out.println(root.ch + " " + s);
			return;
		}
		printRecur(root.left, s + "0");
		printRecur(root.right, s + "1");
	}

	static void huffManDecode(HuffmanNode root, String s) {
		if (root == null)
			return;

		if (root.left == null && root.right == null) {
			System.out.println(root.ch + " " + root.freq);
			return;
		}
		if (s.charAt(0) == '0')
			huffManDecode(root.left, s.substring(1));
		else
			huffManDecode(root.right, s.substring(1));
	}

}

class HuffmanNode {

	int freq;
	char ch;

	HuffmanNode left;
	HuffmanNode right;
}
