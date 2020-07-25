package br.com.ultracodeultracodejpa.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static String urlDecode(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static List<Integer> decodeListInt(String ids){
		String[] listIdsString = ids.split(",");
		List<Integer> listIdsInt = new ArrayList<>();
		for(int i = 0; i < listIdsString.length; i++) {
			listIdsInt.add(Integer.parseInt(listIdsString[i]));
		}
		
		return listIdsInt;
	}
}
