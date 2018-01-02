package com.websocket.server.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class  RedisUtil {
	private Logger logger = LoggerFactory.getLogger(RedisUtil.class);

	/**
	 * 缓存超时时间
	 */
	private static long TIME_OUT = 7*24*3600;//单位为秒

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private RedisTemplate<String, Object> redisJDKTemplate;

	private RedisConnection connection;

	/**
	 * 写入缓存
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, String value) {
		boolean result = false;
		try {
			ValueOperations<String, Object> operations = redisTemplate
					.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 写入object类型的缓存
	 *
	 * @param key
	 * @param value
	 * @param expireTime
	 * @return
	 */
	public boolean set(final String key, String value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<String, Object> operations = redisTemplate
					.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean leftPushList(final String key, Object object){
		return leftPushList(key,object,TIME_OUT,false);
	}

	public boolean leftPushList(final String key, Object object, boolean isStringSerialize){
		return leftPushList(key,object,TIME_OUT,isStringSerialize);
	}

	public boolean leftPushList(final String key, Object object, Long expireTime, boolean isStringSerialize){
		boolean result = false;
		try {
			ListOperations<String, Object> list;
			if (isStringSerialize){
				list = redisTemplate.opsForList();
			}else {
				list = redisJDKTemplate.opsForList();
			}

			list.leftPush(key, object);
			if (expireTime > 0){
				redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean rightPushList(final String key, Collection objects){
		return rightPushList(key, objects, TIME_OUT);
	}

	public boolean rightPushList(final String key, Collection objects, Long expireTime){
		boolean result = false;
		try {
			ListOperations<String, Object> list = redisJDKTemplate
					.opsForList();
			list.rightPushAll(key, objects);
			if (expireTime > 0){
				redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Object> leftRangeList(final String key, long start, long end){
		return leftRangeList(key,start,end,TIME_OUT);
	}

	public List<Object> leftRangeList(final String key, long start, long end, Long expireTime){
		List<Object> objectList = null;
		try {
			ListOperations<String, Object> list = redisJDKTemplate
					.opsForList();
			objectList = list.range(key, start, end);
			if (expireTime > 0){
				redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectList;
	}

	public boolean leftTrimList(final String key, long start, long end){
		boolean result = false;
		try {
			ListOperations<String, Object> list = redisJDKTemplate
					.opsForList();
			list.trim(key, start, end);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 阻塞地从列表右侧弹出元素
	 * block_timeout单位秒
	 */
	public Object brpopList(int block_timeout,String listKey){
		try{
			return redisJDKTemplate.opsForList().rightPop(listKey,block_timeout,TimeUnit.SECONDS);
//			connection = RedisConnectionUtils.getConnection(redisTemplate.getConnectionFactory());
//			List<byte[]> msgs = connection.bRPop(block_timeout, listKey.getBytes());
//			return msgs;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public boolean addZSet(final String key, String value, int score){
		boolean result = false;
		try {
			ZSetOperations<String, Object> operations = redisTemplate
					.opsForZSet();
			operations.add(key,value,score);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List reverseRangeZSet(final String key, int start, int end){
		Set<Object>objectSet = redisTemplate.opsForZSet().reverseRange(key,start,end);
		return new ArrayList<Object>(objectSet);
	}

	public int zcardZSet(final String key){
		long result = redisTemplate.opsForZSet().zCard(key);
		return (int)result;
	}

	/**
	 * 写入hash类型的缓存
	 */
	public boolean setHash(final String key,String field, String value) {
		return setHash(key,field,value,TIME_OUT);
	}

	/**
	 * 写入hash类型的缓存
	 * @param expireTime 过期时间
	 */
	public boolean setHash(final String key,String field, String value, Long expireTime) {
		boolean result = false;
		try{
			redisTemplate.opsForHash().put(key,field,value);
			if (expireTime > 0){
				redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			}
			result = true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}


	/**
	 * 读取hash类型的缓存
	 */
	public String getHash(final String key, final String field){
		Object obj = redisTemplate.opsForHash().get(key, field);
		if (obj == null) return null;
		return (String) obj;
	}


	/**
	 * 读取hash类型的缓存
	 */
	public Object getHash(final String key){
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * 读取缓存
	 *
	 * @param key
	 * @return
	 */
	public String get(final String key) {
		ValueOperations<String, Object> operations = redisTemplate
				.opsForValue();
		Object obj = operations.get(key);
		String result = (String) obj;
		return result;
	}


	/**
	 * 判断缓存中是否有对应的value
	 *
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 批量删除对应的value
	 *
	 * @param keys
	 */
	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 * 批量删除key
	 *
	 * @param pattern
	 */
	public void removePattern(final String pattern) {
		Set<String> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0)
			redisTemplate.delete(keys);
	}

	/**
	 * 删除对应的value
	 *
	 * @param key
	 */
	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	/**
	 * 删除hash中的一条记录
	 */
	public Long delHashRecord(String key,String field){
		BoundHashOperations<String,String,Object> bhops = redisTemplate.boundHashOps(key);
		return bhops.delete(field);
	}

	/**
	 * 分布式锁
	 */
	public synchronized boolean lock(final String key) {
		long timeout = 5*60; // 五分钟
		long expires = System.currentTimeMillis() + timeout;
		String expiresStr = String.valueOf(expires); //锁到期时间
		return setNX(key, expiresStr, timeout);
	}

	public synchronized void unlock(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	private boolean setNX(final String key, final String value, long expireTime) {
		boolean result = false;
		try {
			ValueOperations<String, Object> operations = redisTemplate
					.opsForValue();
			result = operations.setIfAbsent(key, value);
			if (result && expireTime > 0){
				result = redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 发布订阅消息
	 * @param channel
	 * @param message
	 */
	public void sendMessage(String channel, Serializable message) {
		redisJDKTemplate.convertAndSend(channel, message);
		logger.info("sendMessage,channel:"+channel+",message:"+message);
	}

}
