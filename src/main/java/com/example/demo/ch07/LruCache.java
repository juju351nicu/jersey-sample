package com.example.demo.ch07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LruCache<E> {
	final List<String> keys = new ArrayList<>();
	private final Map<String, E> entries = new HashMap<>();

	public void put(String key, E item) {
		entries.put(key, item);
		keys.remove(key);
		keys.add(key);
	}

	public E get(String key) {
		if (entries.containsKey(key)) {
			keys.remove(key);
			keys.add(key);
		}
		return entries.get(key);
	}

	public int size() {
		return entries.size();
	}
}
