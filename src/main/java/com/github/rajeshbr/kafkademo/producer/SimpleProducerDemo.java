package com.github.rajeshbr.kafkademo.producer;

import com.github.rajeshbr.kafkademo.common.CommonConstants;

public class SimpleProducerDemo {
  public static void main(String[] args) {

    Boolean isAsync = false;
    SimpleProducer producerThread =
        new SimpleProducer(CommonConstants.SIMPLE_DEMO_TOPIC, 5, isAsync);
    producerThread.start();
  }
}
