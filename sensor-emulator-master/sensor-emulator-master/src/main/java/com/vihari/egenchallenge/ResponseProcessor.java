package com.vihari.egenchallenge;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseProcessor {

	public static <T> ResponseEntity<T> returnPolishedResponse(Class<T> accessBeanClass, Object object) {
		
		if (null == object){
			return new ResponseEntity<T>((T)null, HttpStatus.NOT_FOUND);
		}else{
			T object2 = (T)object;
			return new ResponseEntity<T>(object2, HttpStatus.OK);
		}
	}
	
	
}
