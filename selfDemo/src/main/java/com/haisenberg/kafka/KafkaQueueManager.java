package com.haisenberg.kafka;

import java.util.List;

import org.apache.log4j.Logger;

import com.haisenberg.utils.PropertiesUtil;
import com.mysql.jdbc.StringUtils;


public class KafkaQueueManager {
	
	protected static final Logger LOGGER = Logger.getLogger(KafkaQueueManager.class);


	private static KafkaProducerThread friendSendLogProducer = null;

	
	
	
	static {

		try {

			String kafkaServer = PropertiesUtil.getProperty("kafka.server");
			if (StringUtils.isNullOrEmpty(kafkaServer)) {

				kafkaServer = "localhost:9092";
			}
			String localServerID = PropertiesUtil.getProperty("local.server.id");
			if (localServerID == null || localServerID.length() == 0) {
				localServerID = "01";
			}
			

			// -朋友圈发送历史-----
			friendSendLogProducer = new KafkaProducerThread(kafkaServer, "friendSendLogNotify", 5000);

 			
			friendSendLogProducer.start();


		} catch (Exception ex) {

			LOGGER.error("KafkaQueueManager", ex);
		}

	}
	
	/**
	 * 朋友圈发送历史
	 * 
	 * @param notifyList
	 */
	public static void addFriendSendLogModel(List<Object> dataList) {
		int len = dataList.size();
		for (int index = 0; index < len; index++) {
			Object obj = dataList.get(index);		
			String data =(String)obj;
			while (!friendSendLogProducer.addToQueue(data)) {
				try {
					Thread.sleep(50);
				} catch (Exception e) {
				}
			}
		}
	}
}
