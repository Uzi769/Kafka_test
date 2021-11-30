package com.example.micro.config;

import com.example.micro.Entity.UserDto;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;
/*
Если мы попробуем вручную создать объект KafkaTemplate, то увидим,
 что в конструктор в качестве параметра передаётся объект интерфейса ProducerFactory<K, V>,
например DefaultKafkaProducerFactory<>. Для того чтобы создать DefaultKafkaProducerFactory,
нам нужно в его конструктор передать Map, содержащий настройки продюсера.
Весь код по конфигурации и созданию продюсера вынесем в отдельный класс.
Для этого создадим пакет config и в нём класс KafkaProducerConfig.
 */

@Configuration
public class KafkaProducerConfig {

    private String kafkaServer = "localhost:9092";

    @Bean
    public Map<String, Object> producerConfigs(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<Long, String> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<Long, String> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
