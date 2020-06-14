package com.cheetahapps.sales.integration.mail;

import com.cheetahapps.sales.setting.Setting;
import com.cheetahapps.sales.setting.SettingItem;

import jodd.bean.BeanUtil;
import lombok.Data;

@Data
public class MailSetting {

	
	private String user;
	
	private String password;
	
	private String server;
	
	private int port;
	
	private boolean allowTls;
	
	public static MailSetting from(Setting setting) {
		MailSetting mailSetting = new MailSetting();
		
		for(SettingItem item : setting.getItems()) {
			BeanUtil.declaredForced.setProperty(mailSetting, item.getKey(), item.getValue());
		}
		
		return mailSetting;
	}
	
}
