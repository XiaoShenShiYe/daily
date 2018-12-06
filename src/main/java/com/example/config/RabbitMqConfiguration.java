/*
package com.example.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.constant.RabbitConstantValue;
import com.example.utils.MqUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.config.StatelessRetryOperationsInterceptorFactoryBean;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

*/
/**
 *//*

@Configuration
public class RabbitMqConfiguration {

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory containerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean(name = "concurrentContainerFactory")
    public SimpleRabbitListenerContainerFactory concurrentContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrentConsumers(10);
        factory.setAdviceChain(retryOperationsInterceptorFactoryBean().getObject());
        return factory;
    }

    @Bean
    public StatelessRetryOperationsInterceptorFactoryBean retryOperationsInterceptorFactoryBean() {
        StatelessRetryOperationsInterceptorFactoryBean bean = new StatelessRetryOperationsInterceptorFactoryBean();
        bean.setMessageRecoverer(messageRecoverer());
        return bean;
    }

    @Bean
    public MessageRecoverer messageRecoverer() {
        return new TmsMessageRecoverer();
    }


    public class TmsMessageRecoverer implements MessageRecoverer {

        private Logger logger = LoggerFactory.getLogger(TmsMessageRecoverer.class);

        private static final String ERROR_MESSAGE_KEY = "errorMsg";
        private static final String ERROR_QUEUE_SUFFIX = ".error";

        @Override
        public void recover(Message message, Throwable throwable) {
            try{
                String msg = new String(message.getBody(), RabbitConstantValue.DEFAULT_CHARSET);
                logger.error("RabbitMQ接收消息，重试三次后仍然失败。队列名：{}, 消息内容：{}", message.getMessageProperties().getConsumerQueue(), msg);
                logger.error(throwable.getCause().getMessage(), throwable.getCause());

                String errorMsgBody;
                JSONObject jsonObj = JSON.parseObject(msg);
                if (null == jsonObj) {
                    jsonObj = new JSONObject();
                }
                jsonObj.put(ERROR_MESSAGE_KEY, throwable.getCause().getMessage());
                errorMsgBody = jsonObj.toJSONString();

                String queueName = message.getMessageProperties().getConsumerQueue();
                String errorQueueName = queueName + ERROR_QUEUE_SUFFIX;

                MqUtil.publishMessage(RabbitConstantValue.EXCHANGE_JOLLYCHIC_GLP, errorQueueName, errorMsgBody);
            } catch (Exception e) {
                logger.error("异常消息发送到错误队列失败，原始消息内容: " + new String(message.getBody()));
                logger.error(e.getMessage(), e);
            }

        }
    }

}
*/
