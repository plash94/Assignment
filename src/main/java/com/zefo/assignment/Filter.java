package com.zefo.assignment;

/**
 * Created by plash on 11/13/16.
 */

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
import com.google.gson.Gson;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.shield.ShieldPlugin;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static org.elasticsearch.index.query.QueryBuilders.*;

public class Filter {
	private Gson gson = new Gson();
	private ObjectMapper objectMapper = new ObjectMapper();

	private static String CLASS = "test";
	private static String SUBCLASS = "jeans";
	// TransportClient client = new TransportClient(new
	// InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
	// Client client = new TransportClient().addTransportAddress(new
	// InetSocketTransportAddress("localhost", 9200));
	// .addTransportAddress(new InetSocketTransportAddress("localhost", 9300))
	// .addTransportAddress(new InetSocketTransportAddress("localhost", 9301));
/*	TransportClient client = TransportClient.builder()
		    .addPlugin(ShieldPlugin.class)
		    .settings(Settings.builder()
		        .put("cluster.name", "myClusterName")
		        .put("shield.user", "transport_client_user:changeme")
		        .build().addTransportAddress(new InetSocketTransportAddress("localhost", 9301)));
	*/
	TransportClient transportClient = new PreBuiltTransportClient(Settings.EMPTY)
			.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
	
	
	//Client client = new TransportClient().addTransportAddress(InetSocketTransportAddress("localhost",9300));
	//Settings settings = Settings.builder().put("cluster.name", "myClusterName").build();

	public Filter() throws UnknownHostException {

	}
	public String searchQuery(HashMap<String, ArrayList<String>> searchBody) {
		// @SuppressWarnings("resource")
		//System.out.println(searchBody.size());
		
		BoolQueryBuilder boolQueryBuilder = boolQuery();
		
		for (HashMap.Entry<String, ArrayList<String>> entry : searchBody.entrySet()) {
			//System.out.println(entry.getKey() + " " + entry.getValue());
			BoolQueryBuilder shouldQueryBuilder = boolQuery();
			// Taking OR of all selections in a catalogue | Waist 28,32 | Color Black,Blue,Green .... 
			for (String value : entry.getValue()) {
				shouldQueryBuilder = shouldQueryBuilder.should(termQuery(entry.getKey(), value));
		
			}
			// Taking AND of all catalogue selections | Waist + Brand | Waist + Color + Brand ....
			boolQueryBuilder = boolQueryBuilder.must(shouldQueryBuilder);
		}

		SearchRequestBuilder reqBuilder = transportClient.prepareSearch(CLASS).setTypes(SUBCLASS)
				.setSearchType(SearchType.QUERY_AND_FETCH).setQuery(constantScoreQuery(boolQueryBuilder))
				.setFrom(0).setSize(50);
		//System.out.println(reqBuilder.toString());
		SearchResponse searchResult =  reqBuilder.execute().actionGet();
		//transportClient.close();
		return searchResult.toString();
	}
}
