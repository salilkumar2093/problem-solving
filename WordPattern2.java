package com.manager.parking;

import java.util.HashMap;

public class WordPatternII {

	public static void main(String... args) {
		System.out.println(wordPatternMatch("abab", "reblrebl"));
	}

	public static boolean wordPatternMatch(String pattern, String str) {
		if (pattern.length() == 0 && str.length() == 0)
			return true;
		if (pattern.length() == 0)
			return false;

		HashMap<Character, String> map = new HashMap<Character, String>();
		return helper(pattern, str, 0, 0, map);
	}

	public  static boolean helper(String pattern, String str, int i, int j, HashMap<Character, String> map) {
		if (i == pattern.length() && j == str.length()) {
			return true;
		}

		if (i >= pattern.length() || j >= str.length())
			return false;
		char ch = pattern.charAt(i);
		for (int k = j + 1; k <= str.length(); k++) {
			String sub = str.substring(j, k);
			if (!map.containsKey(ch) && !map.containsValue(sub)) {
				map.put(ch, sub);
				if (helper(pattern, str, i + 1, k, map))
					return true;
				map.remove(ch);
			} else if (map.containsKey(ch) && map.get(ch).equals(sub)) {
				if (helper(pattern, str, i + 1, k, map))
					return true;
			}
		}
		return false;
	}
}

/*
 * 
 * 
 * pattern = "abab", str = "redblueredblue" should return true. pattern =
 * "aaaa", str = "asdasdasdasd" should return true. pattern = "aabb", str =
 * "xyzabcxzyabc" should return false.
 */
