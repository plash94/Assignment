package com.zefo.assignment;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.JSONException;
import org.json.simple.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by plash on 11/13/16.
 */


public class Main {

	public void parseResult (String resultSet) throws ParseException {
       
		HashMap<String, Jeans> resultFilter = new HashMap<String, Jeans>();
            
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(resultSet);
            
            Iterator it = json.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                if(pair.getKey().equals("hits")) {
                //System.out.println(pair.getValue().toString());
                JSONObject jsonF = (JSONObject) parser.parse(pair.getValue().toString());
                Iterator it2 = jsonF.entrySet().iterator();
                while (it2.hasNext()) {
                	Map.Entry pair2 = (Map.Entry)it2.next();
                	String splitString = pair.getValue().toString();
                	String[] explode= splitString.split("\"");
                    int markedCatalogues = 0;
                    String colorStor = null;
                    String waistStor = null;
                    String brandStor = null;
                    String idStor = null;
                	for(int i = 2 ; i < explode.length; i++){
                    	if(explode[i].equals("color")) {
                    		markedCatalogues++;
                    		colorStor = explode[i+2];
                    	} else if(explode[i].equals("waist")) {
                    		markedCatalogues++;
                    		waistStor = explode[i+2];
                    	} else if(explode[i].equals("brand")) {	
                    		markedCatalogues++;			
                    		brandStor = explode[i+2];
                    	} else if(explode[i].equals("_id")) {
                    		markedCatalogues++;
                    		idStor = explode[i+2];
                    	}
                    	if(markedCatalogues == 4) {
                    		resultFilter.put(idStor, new Jeans(waistStor, colorStor, brandStor));
                    		markedCatalogues = 0;
                    	}
                    }
                    
                    break;	
                }
                it2.remove();
                }
                it.remove(); 
            } //System.out.println(json.kekeySet()containsKey("hits"));

            
         if(resultFilter.isEmpty()){
        	 System.out.println("No Results Found !");
         }
        Iterator it4 = resultFilter.entrySet().iterator();
        while (it4.hasNext()) {
        	Map.Entry pair = (Map.Entry)it4.next();
            Jeans obj = (Jeans) pair.getValue();
        	System.out.println(pair.getKey() + " " + obj.getBrand() + " " +obj.getWaist() +  " " + obj.getColor() );
        }
        System.out.println("-----------------------------------------------");
    }
}
