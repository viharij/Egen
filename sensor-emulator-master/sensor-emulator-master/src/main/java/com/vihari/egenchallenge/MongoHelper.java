package com.vihari.egenchallenge;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoHelper {
    private MongoClient mongoClient = null;
    private MongoDatabase mongoDatabase = null;

    public static final String MONGODB_DataBase = "Egen";
    public static final String MONGODB_HOST = "localhost";
    public static final int MONGODB_HOST_PORT = 27017;

    public MongoClient getMongoClient() {
	if (this.mongoClient == null) {
	    this.mongoClient = new MongoClient(MONGODB_HOST, MONGODB_HOST_PORT);
	}
	return this.mongoClient;
    }

    public MongoDatabase getMongoDatabase() {
	if (this.mongoDatabase == null) {
	    this.mongoDatabase = this.getMongoClient().getDatabase(MongoHelper.MONGODB_DataBase);
	}
	
	return this.mongoDatabase;
    }
    /* To insert to any collection */
    public int insert(String Collection,Document doc) {
    
    	int i=0;
    	return i;
    }

}
