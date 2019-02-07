package com.andregcaires.webstoreapi.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	
	public static List<Integer> decodeIntList(String str) {
		
		String[] vet = str.split(",");
		
		List<Integer> list = new ArrayList<>();
		
		for(int i = 0; i < vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		
		return list;
		
		//return Arrays.asList(str.split(",")).stream().map(obj -> Integer.parseInt(obj)).collect(Collectors.toList());
		
	}
	
	
	/*
	 * Decodifica string enviada como @RequestParam, evitando caracteres especiais ou espacamento
	 * */
	public static String decodeParam(String s) {
		
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
		
	}
	
}
