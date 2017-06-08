package com.xiaomatech.dbevent.canal.dbkafka;

import java.util.Properties;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;


public class ClusterCanalClient extends AbstractCanalClient {

    public ClusterCanalClient(String destination) {
        super(destination);
    }

    public static void main(String args[]) {
        String destination = "";
        String topic = "";
        String canalhazk = "1";
        String kafka = "";

        if (args.length != 4) {
            logger.error("input param must :  destination topic canalhazookeeper kafka \n for example: baiduyun_recharge test 192.168.0.163:2181 192.168.0.163:9092");
            System.exit(1);
        } else {
            destination = args[0];
            topic = args[1];
            canalhazk = args[2];
            kafka = args[3];
        }


        // 基于zookeeper动态获取canal server的地址，建立链接，其中一台server发生crash，可以支持failover
        CanalConnector connector = CanalConnectors.newClusterConnector(canalhazk, destination, "", "");
        Properties props = new Properties();


        props.put("bootstrap.servers", kafka);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("acks", "all");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);

        final ClusterCanalClient clientTest = new ClusterCanalClient(destination);
        clientTest.setConnector(connector);
        clientTest.setKafkaProducer(producer);
        clientTest.setKafkaTopic(topic);
        clientTest.start();


        Runtime.getRuntime().addShutdownHook(new Thread() {

            public void run() {
                try {
                    logger.info("## stop the canal client");
                    clientTest.stop();
                } catch (Throwable e) {
                    logger.warn("##something goes wrong when stopping canal:\n{}", ExceptionUtils.getFullStackTrace(e));
                } finally {
                    logger.info("## canal client is down.");
                }
            }

        });
    }
}
