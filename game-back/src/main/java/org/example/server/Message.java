package org.example.server;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class Message {
    public Action action;
}
