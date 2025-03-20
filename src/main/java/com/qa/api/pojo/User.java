package com.qa.api.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.qa.api.utils.StringUtility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)

public class User {

	private Integer id;///new added with @JsonInclude(Include.NON_NULL)
	//updated 	//User user = new User("Vrushali", StringUtility.getRandomEmailId(), "female", "active");
	//User user = new User(null,"Vrushali", StringUtility.getRandomEmailId(), "female", "active");
	private String name;
	private String email;
	private String gender;
	private String status;
}
