static int bestMeetingPoint() {
		int grid[][] = { { 1, 0, 1, 0, 1 }, { 0, 1, 0, 0, 0 }, { 0, 1, 1, 0, 0 } };
		int m = grid.length;
		int n = grid[0].length;
		List<Integer> vertical = new ArrayList<>();
		List<Integer> horizontal = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					vertical.add(i);
					horizontal.add(j);
				}
			}
		}
		Collections.sort(vertical);
		Collections.sort(horizontal);
		int mid = vertical.size() / 2;
		int distance = 0;
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				if (grid[i][j] == 1)
					distance += Math.abs(horizontal.get(mid) - j) + Math.abs(vertical.get(mid) - i);

		return distance;

	}
