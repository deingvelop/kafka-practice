package com.example.kafkapractice.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.util.Properties;

public class Producer {
    public static void main(String[] args) throws IOException {

        Properties configs = new Properties();
        configs.put("bootstrap.servers", "localhost:9092");        // 브로커 지정 - 실무에서는 여러개 지정하기
        configs.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");        // 키 직렬화
        configs.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");      // 밸류 직렬화

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(configs);        // 프로듀서 인스턴스 생성 - 얘로 인해 데이터를 전송

        ProducerRecord<String, String> record = new ProducerRecord<String, String>("click_log", "login");       // 토픽 지정 + 보낼 데이터 값 지정
        // 만약 key를 같이 보내고 싶은 경우, 아래와 같이 작성
        // ProducerRecord<String, String> record = new ProducerRecord<String, String>("click_log", "1", "login");

        producer.send(record);

        producer.close();

    }
}
