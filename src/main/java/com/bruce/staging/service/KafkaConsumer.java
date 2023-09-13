package com.bruce.staging.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "#{'${kafka.topics.my_test_topic}'}", groupId = "#{'${kafka.groups.my_test_group}'}", concurrency = "3")
    public void onMessage(ConsumerRecord<String, Object> data, Acknowledgment ack) {
        log.warn("topic：{}，partition：{}，offset：{}，value：{}", data.topic(), data.partition(), data.offset(), data.value());
        ack.acknowledge();
    }
}
