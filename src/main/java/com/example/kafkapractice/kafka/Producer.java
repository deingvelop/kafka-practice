package com.example.kafkapractice.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.util.Properties;

public class Producer {
    public static void main(String[] args) throws IOException {

        Properties configs = new Properties();
        configs.put("bootstrap.servers", "localhost:9092");
        configs.put("key.serializer", "org.ap            ache.kafka.common.serialization.StringSerializer");
        configs.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(configs);

        ProducerRecord<String, String> record = new ProducerRecord<String, String>("click_log", "login");
        // 만약 key를 같이 보내고 싶은 경우, 아래와 같이 작성
        // ProducerRecord<String, String> record = new ProducerRecord<String, String>("click_log", "1", "login");

        producer.send(record);

        producer.close();

    }
}
