package com.les.ecommerce.helpers.pedido;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class RelatorioHelper {

	
	public List<String> rangeYear(String startYear,String endYear){
		int cur = Integer.parseInt(startYear);
	    int stop = Integer.parseInt(endYear);
	    List<String> list = new ArrayList<String>();
	    while (cur <= stop) {
	        list.add(String.valueOf(cur++));
	    }
	    return list;
	}
	
}
