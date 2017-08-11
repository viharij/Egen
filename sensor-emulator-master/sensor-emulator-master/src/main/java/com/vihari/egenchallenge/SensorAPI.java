package com.vihari.egenchallenge;



import java.util.ArrayList;
import java.util.UUID;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.vihari.egenchallenge.Alerts;
import com.vihari.egenchallenge.Metrics;
import com.vihari.egenchallenge.MongoHelper;



public class SensorAPI {
	
	public String createMetric(Metrics request) {

		MongoHelper mongoHelper = new MongoHelper();
		// Deserialize object to json string
		Gson gson = new Gson();

		String nextId = UUID.randomUUID().toString();
		
		String json = gson.toJson(request);
		Document doc = Document.parse(json);
		
		

		mongoHelper.getMongoDatabase().getCollection(Metrics.COLLECTION_NAME).insertOne(doc);
		return nextId;

	}
	
	public String createAlert(Alerts request) {

		MongoHelper mongoHelper = new MongoHelper();
		// Deserialize object to json string
		Gson gson = new Gson();
		
		String nextId = UUID.randomUUID().toString();
		String json = gson.toJson(request);
		
		Document doc = Document.parse(json);

		mongoHelper.getMongoDatabase().getCollection(Alerts.COLLECTION_NAME).insertOne(doc);
		return nextId;

	}
	
	public ArrayList<Metrics> getAllMetrics() {

		MongoHelper mongoHelper = new MongoHelper();
		// Deserialize object to json string
		Gson gson = new Gson();
		FindIterable<Document> search = mongoHelper.getMongoDatabase().getCollection(Metrics.COLLECTION_NAME).find();
		if (search == null) {
			return null;
		}

		ArrayList<Metrics> metricList = new ArrayList<Metrics>();

		for (Document current : search) {
			Metrics anCarrierFromMongoDB = gson.fromJson(current.toJson(), Metrics.class);
			metricList.add(anCarrierFromMongoDB);
		}

		return metricList;
	}

	
	public ArrayList<Metrics> getAllAlerts() {

		MongoHelper mongoHelper = new MongoHelper();
		// Deserialize object to json string
		Gson gson = new Gson();
		FindIterable<Document> search = mongoHelper.getMongoDatabase().getCollection(Alerts.COLLECTION_NAME).find();
		if (search == null) {
			return null;
		}
		ArrayList<Metrics> metricList = new ArrayList<Metrics>();

		for (Document current : search) {
			Metrics anCarrierFromMongoDB = gson.fromJson(current.toJson(), Metrics.class);
			metricList.add(anCarrierFromMongoDB);
		}
		return metricList;
	}

	
	

}
