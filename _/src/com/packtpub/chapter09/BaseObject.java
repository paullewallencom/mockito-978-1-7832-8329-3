package com.packtpub.chapter09;

import java.io.Serializable;
import java.util.Map;

public abstract class BaseObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DirtyState dirtyState;
	private Long objectId;

	public DirtyState getDirtyState() {
		return dirtyState;
	}

	public void setDirtyState(DirtyState dirtyState) {
		this.dirtyState = dirtyState;
	}

	public BaseObject(Long objId, boolean initializationRequired){
		initialize(objId,initializationRequired);
	}
	public BaseObject(Long objectId) {
		this(objectId, true);
	}

	private void initialize(Long objectId, boolean isRequried) {
		if(!isRequried){
			return;
		}
		Map<String, String> config  =PropertyFileReader.readConfig();
		String url = config.get(ArchitectureConstants.DBUrl);
		String userName = config.get(ArchitectureConstants.DBUserName);
		String password = config.get(ArchitectureConstants.DBPassword);
		
		DataAccessFacade.register(url,
				userName,
				password);
		if (null == objectId) {
			setDirtyState(DirtyState.insert);
		} else {
			BaseObject obj = MemoryManager.getInstance().lookUpInCurrentThread(
					objectId);
			if (obj == null) {
				obj = DataAccessFacade.retrieveObject(objectId);
				setDirtyState(DirtyState.fresh);
			}

			MemoryManager.getInstance().putInConext(objectId, obj);
		}
		this.objectId = objectId;
	}

	public Long getObjectId() {
		return objectId;
	}

	public int save(){
		return 0;
	}
}
