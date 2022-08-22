package com.cdn.auth.auth.mvc.model;

import lombok.Data;

@Data
public class StatusResponse {
    int status;

    public StatusResponse(int status) {
        this.status = status;
    }
}

