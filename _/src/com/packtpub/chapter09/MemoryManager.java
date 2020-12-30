package com.packtpub.chapter09;

import java.util.HashMap;

public class MemoryManager {
   private static MemoryManager mm;
   private HashMap<Long, BaseObject> objectGraphMap = new HashMap<Long, BaseObject>();
	private MemoryManager(){
		
	}
	
	public static synchronized MemoryManager getInstance(){
		if(mm == null){
			mm = new MemoryManager();
		}
		
		return mm;
	}
	
	public synchronized BaseObject lookUpInCurrentThread(Long objectId){
		return objectGraphMap.get(objectId);
	}

	public void putInConext(Long objectId, BaseObject obj) {
		objectGraphMap.put(objectId, obj);
		
	}
}
