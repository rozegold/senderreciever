package com.Application.message;


import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Reciever {

    private CountDownLatch latch = new CountDownLatch(1);

    public void recieveMessage(String msg){

        System.out.println("Recieved : "+msg);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
