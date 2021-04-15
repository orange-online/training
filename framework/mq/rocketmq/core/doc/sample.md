# Producer端装配示例
```java
	String group = "core";
	String namesrvAddr = "192.168.1.111:9876";
	String topic = "topic_001";
	
	DefaultRocketmqSetting setting = DefaultRocketmqSetting.of(group, namesrvAddr); // 加载队列配置
	RocketmqProducer producer = RocketmqProducer.getInstance(setting); // 获取当前配置对应的Producer实例
	SendResult result = producer.send(RocketmqMessage.of(topic, "This is a message body.")); // 发送消息
```

# Consumer端装配示例
```java
	String group = "core";
	String namesrvAddr = "192.168.1.111:9876";
	String topic = "topic_001";
	
	DefaultRocketmqConsumerSetting setting = DefaultRocketmqConsumerSetting.of(group, namesrvAddr); // 加载队列配置
	setting.subscribe(topic, "*"); // 订阅主题并
	setting.registerMessageListener(new MessageListenerOrderly() { // 注册配套的消息监听器，此处使用的是有序消费监听，根据实际需要，可以改用MessageListenerConcurrently并发消费监听
	
		@Override
		public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
	
			msgs.stream().forEach(msg -> System.out.println("message consumed: " + new String(msg.getBody())));
			return ConsumeOrderlyStatus.SUCCESS; // 消息消费完成后，返回SUCCESS状态标识
		}
	});
	RocketmqConsumer.getInstance(setting); // 获取当前配置对应的Consumer实例
```
