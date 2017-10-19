package com.haisenberg.kafka;

import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;

public class KafkaProducerThread extends Thread {
	public static final Logger LOG = Logger.getLogger(KafkaProducerThread.class);

	private KafkaProducer<Integer, String> producer;

	private String topic;
	private Queue<String> queue;
	private int messagePoolSize;

	public KafkaProducerThread(String serverConfig, String topic, int messagePoolSize) {
		this.topic = topic;
		if (serverConfig == null) {
			serverConfig = "localhost:9092";
		}
		Properties props = new Properties();
		props.put("bootstrap.servers", serverConfig);
		props.put("client.id", topic);
		props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer<>(props);
		if (messagePoolSize <= 1) {
			messagePoolSize = 20;
		}
		this.messagePoolSize = messagePoolSize;
		queue = new ArrayBlockingQueue<String>(messagePoolSize);
		this.setName("KafkaProducerThread-" + topic);
		this.setDaemon(true);
	}

	public String getTopic() {
		return topic;
	}

	@Override
	public void run() {
		while (true) {
			String data = queue.poll();
			LOG.info("Send:" + data);
			producer.send(new ProducerRecord<Integer, String>(topic, data));
			try {
				sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean addToQueue(String data) {
		try {
			return queue.add(data);
		} catch (IllegalStateException e) {

			try {
				String error = e.getMessage();
				if (error != null && (error.indexOf("full") > 0 || error.indexOf("满") > 0)) {
					// retry
					int loopTimes = 0;
					while (queue.size() >= messagePoolSize - 3) {
						loopTimes = loopTimes + 1;
						if (loopTimes > 20) {
							break;
						}
						Thread.sleep(50);
					}
					return addToQueue(data);
				} else {
					e.printStackTrace();
					throw e;
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		return false;
	}
}
