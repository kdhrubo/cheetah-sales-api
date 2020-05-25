package com.cheetahapps.sales.integration.box;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoxSettingController {
	
	private final BoxSettingRepository boxSettingRepository;
	
	//Saving updating settings only allowed thru tenants/admin
	@PostMapping("/cockpit/box")
	public BoxSetting saveBox(@Valid @RequestBody BoxSetting boxSetting) {
		log.info("Saving box settings");
		return boxSettingRepository.save(boxSetting);
		
	}
	@GetMapping("/cockpit/box")
	public BoxSetting findOne() {
		return boxSettingRepository.findFirstByDeleted(false).getOrElse(new BoxSetting());
	}
}
