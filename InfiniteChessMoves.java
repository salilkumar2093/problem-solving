  int[] row = { 1, 2, -1, -2, 1, 2, -1, -2 };
	int[] col = { 2, 1, 2, 1, -2, -1, -2, -1 };

	int minKnightMoves(int x, int y) {

		Queue<Node> queue = new LinkedList<>();
		Node root = new Node(0, 0, 0);
		queue.add(root);
		Set<int[]> visited = new HashSet<>();
		Set<String> visitedStr = new HashSet<>();
		visitedStr.add(0 + "$" + 0);
		visited.add(new int[] { 0, 0 });

		while (!queue.isEmpty()) {
			Node pop = queue.poll();
			if (pop.x == x && pop.y == y)
				return pop.move;
			for (int i = 0; i < row.length; i++) {
				int x1 = pop.x + row[i];
				int y1 = pop.y + col[i];

				if (!visitedStr.contains(x1 + "$" + y1)) {
					// visited.add(new int[] { x1, y1 });
					visitedStr.add(x1 + "$" + y1);
					queue.add(new Node(x1, y1, pop.move + 1));
				}

			}
		}
		return -1;
	}

}

class Node {
	int x;
	int y;
	int move;

	public Node(int x, int y, int move) {
		super();
		this.x = x;
		this.y = y;
		this.move = move;
	}

}
