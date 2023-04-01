package com.pegasus.hk.model.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class MessageRequest implements Serializable {
    private String message;
}



