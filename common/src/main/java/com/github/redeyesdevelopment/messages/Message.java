package com.github.redeyesdevelopment.messages;

import java.util.Date;

public interface Message<T> {
    long getMessageId();
    MessageType getMessageType();
    Date getCreationTime();
    String getProducerName();
    T getData();
}
