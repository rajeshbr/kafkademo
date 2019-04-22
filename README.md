# kafkademo
**Simple Kafka demo**

In this project producer will push few messages in a loop to topic named **test** and these messages are consumed by SimpleConsumer

Make sure kafka is up and running in you local.

To start the producer
```bash
./gradlew -q execute -PmainClass=com.github.rajeshbr.kafkademo.producer.SimpleProducerDemo
```
To start the consumer
```bash
./gradlew -q execute -PmainClass=com.github.rajeshbr.kafkademo.consumer.SimpleConsumerDemo
```
