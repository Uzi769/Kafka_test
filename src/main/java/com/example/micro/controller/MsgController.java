package com.example.micro.controller;

import com.example.micro.Entity.Address;
import com.example.micro.Entity.UserDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("msg")
@RequiredArgsConstructor
public class MsgController {

    private final KafkaTemplate<Long, String> kafkaTemplate;

//    @PostMapping
//    public void sendOrder(String msgId, String msg) {
//        kafkaTemplate.send("msg", msgId, msg);
//    }

    @PostMapping()
    public void sendMsg(Long msgId, String msg) {
        //ListenableFuture<SendResult<String, String>> future для просмотра результата отправки сообщения
        ListenableFuture<SendResult<Long, String>> future = kafkaTemplate.send("msg", msgId, msg);

        //Метод addCallback() принимает два параметра – SuccessCallback и FailureCallback. Оба они являются функциональными интерфейсами.
        // Из названия можно понять, что метод первого будет вызван в результате успешной отправки сообщения, второго – в результате ошибки
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();

//        msg.setAddress(new Address("RUS", "Moscow", "Lenina", 1L, 1L));
//        ListenableFuture<SendResult<Long, UserDto>> future = kafkaTemplate.send("msg", msgId, msg);
//        future.addCallback(System.out::println, System.err::println);
//        kafkaTemplate.flush();
    }
}
