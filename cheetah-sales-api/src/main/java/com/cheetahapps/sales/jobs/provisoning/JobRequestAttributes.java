package com.cheetahapps.sales.jobs.provisoning;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.context.request.AbstractRequestAttributes;

public class JobRequestAttributes extends AbstractRequestAttributes {

	Map<String, Object> data = new HashMap<>();

	@Override
	public Object getAttribute(String key, int scope) {

		return data.get(key);
	}

	@Override
	public void setAttribute(String key, Object value, int scope) {
		data.put(key, value);

	}

	@Override
	public void removeAttribute(String key, int scope) {
		data.remove(key);

	}

	@Override
	public String[] getAttributeNames(int scope) {
		return null;
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback, int scope) {
		/**
		 * Not implemented
		 */

	}

	@Override
	public Object resolveReference(String key) {
		return null;
	}

	@Override
	public String getSessionId() {
		return null;
	}

	@Override
	public Object getSessionMutex() {
		return null;
	}

	@Override
	protected void updateAccessedSessionAttributes() {
		/**
		 * Not implemented
		 */
	}

}
