package com.github.rajeshbr.kafkademo.consumer;

import com.github.rajeshbr.kafkademo.common.CommonConstants;

public class SimpleConsumerDemo {

  public static void main(String[] args) {
    SimpleConsumer consumerThread = new SimpleConsumer(CommonConstants.SIMPLE_DEMO_TOPIC);
    consumerThread.start();
  }
}
