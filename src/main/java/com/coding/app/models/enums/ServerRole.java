package com.coding.app.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ServerRole {

    ADMIN("ADMIN", "/admin", "/shared"), CLIENT("CLIENT", "/", "/"), MANAGER("MANAGER", "/shared", "/shared");

    private final String role;
    private final String privateSpace;
    private final String targetUrl;
}
