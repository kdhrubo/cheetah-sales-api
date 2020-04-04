package com.cheetahapps.sales.domain;



import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document("Territory")
@Data
@TypeAlias("territory")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Territory extends ExtensibleBase{


	private String name;

	private String website;

	private Country country ;

	private String state ;

	private String fax;

	private double city;

	private Integer within ;

}