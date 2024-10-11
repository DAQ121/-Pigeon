package com.aqdai.pigeoncore;

import lombok.Data;

@Data
public class Message {

    private String receiver;
    private String content;
    private String type;

}
