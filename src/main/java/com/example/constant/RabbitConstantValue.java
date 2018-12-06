package com.example.constant;

/**
 */
public class RabbitConstantValue {

    /**
     * 默认编码
     */
    public static final String DEFAULT_CHARSET = "UTF-8";

    /** exchange的名字 **/
    public static final String EXCHANGE_JOLLYCHIC_TMS = "jollychic.tms";
    public static final String EXCHANGE_JOLLYCHIC_TOPIC = "jollychic.topic";
    public static final String EXCHANGE_JOLLYCHIC_GLP = "jollychic.glp";


    /** routing key 便利店更新 */
    public static final String ROUTING_KEY_TMS_STATISTIC = "tms.statistic";
    public static final String ROUTING_KEY_TMS_STATISTIC_CONCURENCY = "tms.statistic.concurrency";
    public static final String ROUTING_KEY_GLP_ODC_ACTION = "glp.operation.glp.order.actions";


    /** 队列名 statistic */
    public static final String QUEUE_NAME_TMS_STATISTIC = "tms.statistic";
    public static final String QUEUE_NAME_TMS_STATISTIC_CONCURENCY = "tms.statistic.concurrency";

}
