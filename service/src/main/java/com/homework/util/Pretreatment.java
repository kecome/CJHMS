package com.homework.util;

import java.util.Date;
import java.util.Map;

/**
 * 预处理
 *
 * @author xuke
 * @create 2017-04-06 下午 15:34
 **/
public class Pretreatment {

    private static SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator();

    public static Date now() {
        return new Date();
    }

    public static long nextId() {
        return idGenerator.nextId();
    }

//    public static void main(String[] args) {
//        for(int i=0;i<20;i++) {
//            System.out.println(nextId());
//        }
//    }

    public static String getOrder(Map<String, String> order) {
        if(order != null && order.size() > 0) {
            StringBuffer buffer = new StringBuffer();
            for(Map.Entry entry : order.entrySet()) {
                buffer.append(entry.getKey() + " " + entry.getValue() + ",");
            }
            return buffer.deleteCharAt(buffer.length() -1).toString();
        }
        return "";
    }

    private static class SnowflakeIdGenerator {

        private static final long TWEPOCH = 1420041600000L;
        private static final long WORKER_ID_BITS = 5L;
        private static final long DATACENTER_ID_BITS = 5L;
        private static final long MAX_WORKER_ID = -1L ^ (-1L << WORKER_ID_BITS);
        private static final long MAX_DATACENTER_ID = -1L ^ (-1L << DATACENTER_ID_BITS);
        private static final long SEQUENCE_BITS = 12L;
        private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
        private static final long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
        private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;
        private static final long SEQUENCE_MASK = -1L ^ (-1L << SEQUENCE_BITS);

        private long workerId;
        private long datacenterId;
        private long sequence = 0L;
        private long lastTimestamp = -1L;

        public SnowflakeIdGenerator() {
            long workerId = 1L;
            long datacenterId = 2L;
            if (workerId > MAX_WORKER_ID || workerId < 0) {
                throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", MAX_WORKER_ID));
            }
            if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
                throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", MAX_DATACENTER_ID));
            }
            this.workerId = 112;//Long.parseLong(env.getProperty("workerId"));
            this.datacenterId = 113; //Long.parseLong(env.getProperty("datacenterId"));
        }

        public SnowflakeIdGenerator(long workerId, long datacenterId) {
            if (workerId > MAX_WORKER_ID || workerId < 0) {
                throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", MAX_WORKER_ID));
            }
            if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
                throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", MAX_DATACENTER_ID));
            }
            this.workerId = workerId;
            this.datacenterId = datacenterId;
        }

        public synchronized long nextId() {
            long timestamp = this.getTimestamp();
            if (timestamp < this.lastTimestamp) {
                throw new IllegalStateException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", this.lastTimestamp - timestamp));
            }
            if (this.lastTimestamp == timestamp) {
                this.sequence = (this.sequence + 1) & SEQUENCE_MASK;
                if (this.sequence == 0) {
                    timestamp = this.getNextTimestamp(this.lastTimestamp);
                }
            } else {
                this.sequence = 0L;
            }
            this.lastTimestamp = timestamp;
            return ((timestamp - TWEPOCH) << TIMESTAMP_LEFT_SHIFT)
                    | (this.datacenterId << DATACENTER_ID_SHIFT)
                    | (this.workerId << WORKER_ID_SHIFT)
                    | this.sequence;
        }

        private long getNextTimestamp(long lastTimestamp) {
            long timestamp = this.getTimestamp();
            while (timestamp <= lastTimestamp) {
                timestamp = this.getTimestamp();
            }
            return timestamp;
        }

        private long getTimestamp() {
            return System.currentTimeMillis();
        }
    }

}
