package com.cheetahapps.sales.integration.mail;

import com.cheetahapps.sales.setting.Setting;
import com.cheetahapps.sales.setting.SettingItem;

import io.vavr.collection.List;
import jodd.bean.BeanUtil;
import lombok.Data;

@Data
public class MailSetting {

	
	private String user;
	
	private String password;
	
	private String server;
	
	private int port;
	
	private boolean allowTls;
	
	public MailSetting from(Setting setting) {
		MailSetting mailSetting = new MailSetting();
		
		for(SettingItem item : setting.getItems()) {
			BeanUtil.declaredForced.setProperty(mailSetting, item.getKey(), item.getValue());
		}
		
		return mailSetting;
	}
	
	public Setting to(String name, String product, String provider) {
		return Setting.builder().name(name).product(product).provider(provider)
				.items(List.of(
						SettingItem.builder().key("user").value(this.user).build(),
						SettingItem.builder().key("password").value(this.password)
						.sensitive(true).build(),
						SettingItem.builder().key("server").value(this.server).build(),
						SettingItem.builder().key("port").value(this.port + "").build(),
						SettingItem.builder().key("allowTls").value(this.allowTls + "").build()
					).toJavaList())
						.build(); 
		
		
	}
	
}
