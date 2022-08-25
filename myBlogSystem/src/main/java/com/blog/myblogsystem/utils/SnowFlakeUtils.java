package com.blog.myblogsystem.utils;

import org.springframework.stereotype.Component;

@Component
public class SnowFlakeUtils {
	   	private long workerId = 1L;
	    private long datacenterId = 1L;
	    private long sequence = 0L;
	    private long twepoch = 1288834974657L;
 
	    private long workerIdBits = 5L;
	    private long datacenterIdBits = 5L;
	    private long maxWorkerId = -1L ^ (-1L <<workerIdBits);
	    private long maxDatacenterId = -1L ^ (-1L <<datacenterIdBits);
	    private long sequenceBits = 12L;
 
	    private long workerIdShift = sequenceBits;
	    private long datacenterIdShift = sequenceBits + workerIdBits;
	    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
	    private long sequenceMask = -1L ^ (-1L <<sequenceBits);
 
	    private long lastTimestamp = -1L;
 
	    public SnowFlakeUtils(long datacenterId, long workerId) {
	        if ((datacenterId >maxDatacenterId || datacenterId <0)
	                ||(workerId >maxWorkerId || workerId <0)) {
	            throw new IllegalArgumentException("datacenterId/workerId值非法");
	        }
	        this.datacenterId = datacenterId;
	        this.workerId = workerId;
	    }

	public SnowFlakeUtils() {
	}

	public synchronized long nextId() {
				long timestamp = System.currentTimeMillis();
				if (timestamp <lastTimestamp) {
					throw new RuntimeException("时间戳值非法");
				}
				if (lastTimestamp == timestamp) {
					sequence = (sequence + 1) &sequenceMask;
					if (sequence == 0) {
						timestamp = tilNextMillis(lastTimestamp);
					}
				} else {
					sequence = 0L;
				}
				lastTimestamp = timestamp;
				return ((timestamp - twepoch) <<timestampLeftShift)
						| (datacenterId <<datacenterIdShift) | (workerId <<workerIdShift)
						| sequence;
		}
 
	    protected long tilNextMillis(long lastTimestamp) {
	        long timestamp = System.currentTimeMillis();
	        while (timestamp <= lastTimestamp) {
	            timestamp = System.currentTimeMillis();
	        }
	        return timestamp;
	    }
}