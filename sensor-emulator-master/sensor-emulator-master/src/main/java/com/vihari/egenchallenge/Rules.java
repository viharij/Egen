package com.vihari.egenchallenge;


import java.util.UUID;

import org.bson.Document;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.easyrules.core.BasicRule;
import org.easyrules.spring.SpringRule;

import com.google.gson.Gson;

@Rule(name = "alerrule")
public class Rules extends BasicRule{

	
	
	Metrics metric;
	public int metric_weight;
	
	public static final int baseweight=150;
	
	public Rules(Metrics metric)
	{
		this.metric=metric;
	}
	

    @Condition
    public boolean evaluate() {
    	metric_weight=Integer.parseInt(metric.getValue());
        
    	if((metric_weight>baseweight*0.1) ||(metric_weight<baseweight*0.1))
    	
       		return true;
    	
    	return false;
    	   
    
    }

    @Action
    public void execute(){
        
    	Alerts request=new Alerts();
    	
    	request.setTimeStamp(metric.getTimeStamp());
    	request.setValue(metric.getValue());
    	
    	MongoHelper mongoHelper = new MongoHelper();
		// Deserialize object to json string
		Gson gson = new Gson();
		
		String nextId = UUID.randomUUID().toString();
		String json = gson.toJson(request);
		Document doc = Document.parse(json);

		mongoHelper.getMongoDatabase().getCollection(Alerts.COLLECTION_NAME).insertOne(doc);
		System.out.println("Inserted in alert Collection in Mongodb"+nextId);
		
    }
}