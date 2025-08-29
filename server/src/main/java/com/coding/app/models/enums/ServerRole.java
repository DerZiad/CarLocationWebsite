package com.coding.app.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ServerRole {

	ADMIN("ADMIN", "/admin"), CLIENT("CLIENT", "/"),MANAGER("MANAGER","/");

	private final String role;
	private final String space;
}
