package com.Application.message;

import com.Application.Application;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


@Component
public class Runner implements CommandLineRunner {


    private RabbitTemplate rabbitTemplate;
    private Reciever reciever;
    private ConfigurableApplicationContext context;

    public Runner(RabbitTemplate rabbitTemplate, Reciever reciever, ConfigurableApplicationContext context) {
        this.rabbitTemplate = rabbitTemplate;
        this.reciever = reciever;
        this.context = context;
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Sending message");
        Thread.sleep(10000);

        IntStream.range(1,10).forEach(i -> rabbitTemplate.convertAndSend(Application.queueName, "Hello : "+i));
       // rabbitTemplate.convertAndSend(Application.queueName, "Hello Rabbitmq");
        reciever.getLatch().await(100000, TimeUnit.MILLISECONDS);
        context.close();

    }
}
