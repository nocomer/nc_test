﻿package cube.table;

import java.util.HashMap;

//随机产生25个数字
public class logicNum {

	public static HashMap<Integer, String> getNumbers() {
		HashMap<Integer, String> mHashMap = new HashMap<Integer, String>();

		int i = 1;

		while (true) {
			if (mHashMap.size() >= 25) {
				return mHashMap;
			}

			int j = (int) (Math.random() * 25) + 1;
			String str = String.valueOf(j);
			
			if (mHashMap.containsValue(str)) {
				continue;
			} else {
				
				mHashMap.put(i, str);
				i += 1;
			}
		}
	}

}
