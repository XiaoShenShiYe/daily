/*
package com.example.utils;

import com.example.constant.RabbitConstantValue;
import com.example.utils.spring.ApplicationContextHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConversionException;

import java.io.UnsupportedEncodingException;

*/
/**
 *//*

public class MqUtil {
    private static final Logger logger = LoggerFactory.getLogger(MqUtil.class);
    
    private static final RabbitTemplate rabbitTemplate = ApplicationContextHelper.getBean(RabbitTemplate.class);

    */
/**
     * 调用rabbitMQ发送消息
     * @param exchange 消息的exchange
     * @param routingKey 消息的routingKey
     * @param message 消息的内容
     *//*

    public static void publishMessage(String exchange, String routingKey, String message) {
        publishMessage(exchange, routingKey, message, MessageDeliveryMode.PERSISTENT);
    }

    */
/**
     * 调用rabbitMQ发送消息
     * @param exchange 消息的exchange
     * @param routingKey 消息的routingKey
     * @param message 消息的内容
     * @param deliveryMode 消息的送达模式 MessageDeliveryMode.PERSISTENT | MessageDeliveryMode.NON_PERSISTENT
     *//*

    public static void publishMessage(String exchange, String routingKey, String message, MessageDeliveryMode deliveryMode) {
        if (StringUtils.isEmpty(exchange)) {
            exchange = RabbitConstantValue.EXCHANGE_JOLLYCHIC_GLP;
        }
        if (StringUtils.isEmpty(routingKey)) {
            logger.error("routingKey不能为空!");
            throw new RuntimeException("routingKey不能为空!");
        }
        if (StringUtils.isEmpty(message)) {
            logger.error("消息内容不能为空!");
            throw new RuntimeException("消息内容不能为空!");
        }

        try{
            rabbitTemplate.convertAndSend(exchange, routingKey, buildMessage(message, deliveryMode));
            logger.info("MqUtil发送消息[exchange:{}, routingKey:{}, message:{}]", exchange, routingKey, message);
        } catch (Exception e) {
            logger.error("MQ消息发送失败:{}", e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static Message buildMessage(String msg, MessageDeliveryMode deliveryMode) {
        MessageProperties properties = new MessageProperties();
        properties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        properties.setContentEncoding(RabbitConstantValue.DEFAULT_CHARSET);
        properties.setDeliveryMode(deliveryMode);
        byte[] bytes;
        try {
            bytes = msg.getBytes(RabbitConstantValue.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new MessageConversionException(
                    "failed to convert to Message content", e);
        }
        return new Message(bytes, properties);
    }
}
*/
