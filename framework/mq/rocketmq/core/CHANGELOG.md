#2.0.2 (2020-11-29)
Features
RocketmqProducer支持定制sendMsgTimeout配置

#2.0.1 (2020-07-28)
Features
RocketmqProducer单例模式设计存在瑕疵，变更原有以namesrvAddr作为缓存key的设计为以namesrvAddr@group作为复合缓存key，以支持同一个namesrvAddr，可以向不同group推送消息
RocketmqConsumer单例模式设计存在瑕疵，变更原有以namesrvAddr作为缓存key的设计为以namesrvAddr@group@topic@expression作为复合缓存key，以支持同一个队列，不同标签过滤规则单独消费

#2.0.0 (2020-07-24)
Features
调整setting包路径
构造RocketmqProducerSetting接口，封装RocketmqMessager消息发送工具，简化消息发送逻辑

#1.0.0 (2020-07-14)
Features
完成Rocketmq Producer和Consumer封装