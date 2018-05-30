package com.adven.concordion.extensions.exam.kafka;

import lombok.Builder;
import lombok.Value;

/**
 * @author Ruslan Ustits
 */
@Value
@Builder(toBuilder = true)
public class Event<T> {

    String topicName;
    String key;
    T message;
    EventHeader header;

    public static <T> Event<T> empty() {
        return Event.<T>builder().build();
    }

}