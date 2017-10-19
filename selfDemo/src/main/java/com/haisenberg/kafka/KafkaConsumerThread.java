package com.haisenberg.kafka;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.Logger;

public class KafkaConsumerThread extends Thread {
	public static final Logger LOG = Logger.getLogger(KafkaConsumerThread.class);
	private KafkaConsumer<Integer, String> consumer;
	private String topic;
	private long lastSubmitTime;	
	private long interval = 5000;   //5 seconds.
	
	public KafkaConsumerThread(String serverConfig, String topic) {
		if (serverConfig == null) {
			serverConfig = "localhost:9092";
		}
		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, serverConfig);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.IntegerDeserializer");
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringDeserializer");
		consumer = new KafkaConsumer<>(props);
		this.topic = topic;
		this.setName("KafkaConsumerThread-" + topic);
	}

	@Override
	public void run() {
		consumer.subscribe(Collections.singletonList(this.topic));
		ConsumerRecords<Integer, String> records = consumer.poll(500);
		long currentTime = System.currentTimeMillis();
		if (lastSubmitTime == 0) {
			lastSubmitTime = currentTime;
		}
		if (records == null) {
			//When there are no any message, it try to commit transaction per 5 seconds.
			if (currentTime - lastSubmitTime > interval) {
				consumer.commitAsync();
				lastSubmitTime = currentTime;
			}
		}else if (records.isEmpty()) {
			//When there are no any message, it try to commit transaction per 5 seconds.
			if (currentTime - lastSubmitTime > interval) {
				consumer.commitAsync();
				lastSubmitTime = currentTime;
			}
		}else{
			 for(ConsumerRecord<Integer, String> record : records) {    
				  	LOG.debug("kafka消费者线程正在执行...");
					System.out.println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset() + " partition(" + record.partition() + ")");
	          }   
		}
	}
}
