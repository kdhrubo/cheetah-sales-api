package com.cheetahapps.sales.contact;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Emails {
	
	private String primaryEmail;
	private String secondaryEmail;
	private String otherEmail;

}
