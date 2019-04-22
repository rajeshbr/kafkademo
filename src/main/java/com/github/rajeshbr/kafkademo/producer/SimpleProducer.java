package com.github.rajeshbr.kafkademo.producer;

import static com.github.rajeshbr.kafkademo.common.CommonConstants.BOOTSTRAP_SERVERS;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class SimpleProducer extends Thread {

  private final KafkaProducer<Integer, String> producer;
  private final String topic;
  private final Boolean isAsync;
  private final Integer numberOfMessages;

  public static final String CLIENT_ID = "producerdemo";

  public SimpleProducer(String topic, Integer numberOfMessages, Boolean isAsync) {

    this.topic = topic;
    this.isAsync = isAsync;
    this.numberOfMessages = numberOfMessages;

    Properties properties = new Properties();
    properties.put("bootstrap.servers", BOOTSTRAP_SERVERS);
    properties.put("client.id", CLIENT_ID);
    properties.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
    properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    producer = new KafkaProducer<Integer, String>(properties);
  }

  @Override
  public void run() {

    for (Integer i = 0; i < numberOfMessages; i++) {
      if (isAsync) {
        producer.send(
            new ProducerRecord<>(topic, i, "Message: " + i),
            (RecordMetadata metadata, Exception exception) -> {
              System.out.println("Message pushed to partition " + metadata.partition());
            });
        // Introducing delay so that we can see the callback being called.
        try {
          Thread.sleep(10000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      } else {
        try {

          producer.send(new ProducerRecord<>(topic, i, "Message: " + i)).get();
          System.out.println("Sent message " + i);

        } catch (InterruptedException | ExecutionException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
