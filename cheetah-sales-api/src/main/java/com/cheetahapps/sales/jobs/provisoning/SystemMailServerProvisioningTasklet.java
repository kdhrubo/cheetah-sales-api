package com.cheetahapps.sales.jobs.provisoning;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.setting.Setting;
import com.cheetahapps.sales.setting.SettingBusinessDelegate;
import com.cheetahapps.sales.setting.SettingItem;
import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class SystemMailServerProvisioningTasklet implements Tasklet {
	
	@Value("${smtp.username}")
	private String smtpUser;
	@Value("${smtp.password}")
	private String smtpPassword;
	@Value("${smtp.server}")
	private String smtpServer;
	@Value("${smtp.port}")
	private int smtpPort;
	@Value("${smtp.useTls}")
	private boolean allowTls;
	
	private final SettingBusinessDelegate settingBusinessDelegate;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		Setting setting = Setting.builder().name("system_smtp").product("mail").provider("dreamhost")
				.items(List.of(
						SettingItem.builder().key("user").value(smtpUser).build(),
						SettingItem.builder().key("password").value(smtpPassword).sensitive(true).build(),
						SettingItem.builder().key("server").value(smtpServer).build(),
						SettingItem.builder().key("smtpPort").value(smtpPort + "").build(),
						SettingItem.builder().key("smtpPort").value(allowTls + "").build()
					).toJavaList())
						.build();
		
		settingBusinessDelegate.save(setting);
		
		return RepeatStatus.FINISHED;
	}

}
