package com.koliexpress.tripservice.config;

public enum ApiPaths {
    TRIPS("/api/v1/trips"),
    TRANSPORTS("/api/v1/transports");

    private final String path;

    ApiPaths(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }
}

