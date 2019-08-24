package com.beable.poker.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequest extends BaseRequest {
	private String id;
	private String pwd;
	private String pwd2;
	private String nick;
}
