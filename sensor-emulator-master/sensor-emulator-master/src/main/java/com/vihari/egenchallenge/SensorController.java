package com.vihari.egenchallenge;

import java.util.ArrayList;
import java.util.HashMap;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vihari.egenchallenge.Alerts;
import com.vihari.egenchallenge.Metrics;
import com.vihari.egenchallenge.SensorAPI;


@RestController
public class SensorController {

	/* To insert Data of JSon with Timestamp and Value into MongoDB*/
	@RequestMapping(value = "metric", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, String> addMetric(@RequestBody Metrics uiRequest) {

		/* Checking the rule condition and inserted in Alert if necessary*/
		Rules rule=new Rules(uiRequest);
		RulesEngine ruleEngine =RulesEngineBuilder.aNewRulesEngine()
                .named("alertrule")
                .build();
		ruleEngine.registerRule(rule);
		ruleEngine.fireRules();
		
		/*API call to insert into Metric collection at MongoDB*/
		SensorAPI sensorAPI  = new SensorAPI();
		String metricID = sensorAPI.createMetric(uiRequest);
		HashMap<String,String > resp = new HashMap<String, String>();
		resp.put("Inserted_Metric with ID", metricID);
		return resp;
	}
	
	/* To insert Data of AlertRule Data with Timestamp and Value into MongoDB*/
	@RequestMapping(value = "alert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, String> addAlert(@RequestBody Alerts uiRequest) {

		SensorAPI sensorAPI  = new SensorAPI();
		String metricID = sensorAPI.createAlert(uiRequest);
		HashMap<String,String > resp = new HashMap<String, String>();
		resp.put("Inserted_Alert with ID", metricID);
		return resp;
	}
	
	

	/* To read entire data in Metrics_Collection from  MongoDB*/
	@RequestMapping(value = "readAllMetrics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList> getAllMetrics() {

		SensorAPI SensorAPI  = new SensorAPI();
		ArrayList<Metrics> metric = SensorAPI.getAllMetrics();
		return ResponseProcessor.returnPolishedResponse(ArrayList.class,metric);
	}
	
	/* To read entire data in Alerts_Collection from  MongoDB*/
	@RequestMapping(value = "readAllAlerts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList> getAllAlerts() {

		SensorAPI SensorAPI  = new SensorAPI();
		ArrayList<Metrics> metric = SensorAPI.getAllAlerts();
		return ResponseProcessor.returnPolishedResponse(ArrayList.class,metric);
	}
	
	
	
	
	
}
