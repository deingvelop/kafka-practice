package com.example.kafkapractice.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Properties;

public class Consumer {
    public static void main(String[] args) {
        Properties configs = new Properties();
        configs.put("bootstrap.servers", "localhost:9092");     // 브로커 지정 - 실무에서는 여러 개 브로커 지정 필요
        configs.put("group.id", "click_log_group");             // 컨슈머 그룹 설정
        configs.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");     // 키, 밸류에 대한 직렬화 설정
        configs.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(configs);    // 컨슈머 인스턴스 생성 - 이를 통해 데이터를 읽고 처리함

        consumer.subscribe(Arrays.asList("click_log"));             // 데이터를 가져올 토픽 선언
//        // 만약 토픽의 전체 데이터가 아닌 일부 파티션의 데이터만 가져오고 싶다면 아래와 같이 작성
//        TopicPartition partition0 = new TopicPartition(topicName, 0);
//        TopicPartition partition1 = new TopicPartition(topicName, 1);
//        consumer.assign(Arrays.asList(partition0, partition1));

        // 데이터를 실질적으로 가져오는 polling loop (poll() 메서드가 포함된 무한 루프)
        // 컨슈머 API의 핵심 로직
        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(500);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.value());     // 출력되는 부분 = 실제로 처리하고자 하는 데이터 = 이전에 producer가 전송한 데이터
                // 실무에서는 이 Sysout 대신 Hadoop 혹은 Elastic Search에 데이터를 저장하는 로직을 넣기도 함
            }
        }
    }
}
