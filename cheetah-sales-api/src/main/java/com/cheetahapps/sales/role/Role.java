package com.cheetahapps.sales.role;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import jodd.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document("Role")
@TypeAlias("Role")
public class Role {

	@Id
	private String id;

	private String name;

	private boolean system;

	@CreatedDate
	private LocalDateTime createdDate;

	@LastModifiedDate
	private LocalDateTime lastModifiedDate;
	
	public boolean isAdmin() {
		return StringUtil.equalsOne(this.name, "TENANT_ADMIN", "SUPER_ADMIN") != -1;
	}

}