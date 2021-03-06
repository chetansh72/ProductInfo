package com.niit.cloud.kafka;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.niit.cloud.ItemInvoice;

@Component
public class Sender {
	private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
  @Autowired
	private KafkaTemplate<String, ItemInvoice> jsonKafkaTemplate;
 
  @Value("${kafka.topic.helloworld}")
	private String topic;

	@Value("${kafka.topic.json}")
	private String jsonTopic;

	@Value("${kafka.topic.partitioned}")
	private String partitionTopic;
	
	// Sending objects as Json
		public void send(ItemInvoice item, int partition) {
			// If a valid partition number is specified that partition will be used
			// when sending the record. If no partition is specified but a key is
			// present a partition will be chosen using a hash of the key. If
			// neither key nor partition is present a partition will be assigned in
			// a round-robin fashion.
			ListenableFuture<SendResult<String, ItemInvoice>> future = null;
			if (partition != -1) {
				future = jsonKafkaTemplate.send(partitionTopic, partition, null, item);
			} else {
				future = jsonKafkaTemplate.send(jsonTopic, item);
			}
			// So lets register a listener rather than future.get which blocks.
			future.addCallback(new ListenableFutureCallback<SendResult<String, ItemInvoice>>() {

				@Override
				public void onSuccess(SendResult<String, ItemInvoice> result) {
					RecordMetadata recordMetadata = result.getRecordMetadata();
					LOGGER.info("sent json message='{}' on {}@partition={}", item, recordMetadata.topic(),
							recordMetadata.partition());
				}

				@Override
				public void onFailure(Throwable ex) {
					LOGGER.error("unable to send json message='{}'", item, ex);
				}
			});
		}

		// Kafka broker auto-creates a topic when it receives a request for an
		// unknown topic.
		public void send(String message) {
			// async
			ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
			// So lets register a listener rather than future.get which blocks.
			future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

				@Override
				public void onSuccess(SendResult<String, String> result) {
					LOGGER.info("sent message='{}' with offset={}", message, result.getRecordMetadata().offset());
				}

				@Override
				public void onFailure(Throwable ex) {
					LOGGER.error("unable to send message='{}'", message, ex);
				}
			});

}
}
