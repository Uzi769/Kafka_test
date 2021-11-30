package com.example.micro;

import com.example.micro.Entity.UserDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;


//Класс, в котором будет создаваться консьюмер необходимо пометить аннотацией @EnableKafka.
@EnableKafka
@SpringBootApplication
public class MicroApplication {

	@KafkaListener(topics = "msg")
	public void msgListener(String msg){
		System.out.println(msg);
	}

//	@KafkaListener(topics = "msg")
//	public void orderListener(ConsumerRecord<Long, UserDto> record){
//		System.out.println(record.partition());
//		System.out.println(record.key());
//		System.out.println(record.value());
//	}

	public static void main(String[] args) {
		SpringApplication.run(MicroApplication.class, args);
	}

}
