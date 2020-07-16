package com.whiteslave.whiteslaveApp.controller;

public enum HeaderKey {
    STATUS("Status"),
    MESSAGE("Message");

    private String headerKeyLabel;

    HeaderKey(String headerKeyLabel) {
        this.headerKeyLabel = headerKeyLabel;
    }

    public String getHeaderKeyLabel() {
        return headerKeyLabel;
    }
}
