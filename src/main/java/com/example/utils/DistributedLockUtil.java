/*
package com.example.utils;
import com.example.utils.spring.ApplicationContextHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import tk.mybatis.mapper.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

*/
/**
 * 分布式锁工具类
 *
 *//*

public class DistributedLockUtil {

    private static final RedisTemplate<String, String> redisTemplate = ApplicationContextHelper.getBean("redisTemplate", RedisTemplate.class);

    private static final Logger logger = LoggerFactory.getLogger(DistributedLockUtil.class);

    private static final ThreadLocal<String> lockValueThreadLocal = new ThreadLocal<>();

    public static final String LOCK_SCRIPT = "if redis.call(\"get\",KEYS[1]) == ARGV[1] " +
                                            "then " +
                                            "    return redis.call(\"del\",KEYS[1]) " +
                                            "else " +
                                            "    return 0 " +
                                            "end ";

    */
/** 默认超时时间为1分钟 *//*

    private static final long DEFAULT_TIME_OUT = 60 * 1000;


    */
/**
     * 获取分布式锁，无论成功失败，立即返回
     * @param lockKey 锁的key
     * @param timeout 锁的有效期，单位为毫秒
     * @return
     *//*

    public static boolean lock(String lockKey, long timeout) {
        long currentTime = System.currentTimeMillis();
        long lockValue = currentTime + timeout + 1;

        // 获取锁成功，直接返回
        if (redisTemplate.opsForValue().setIfAbsent(lockKey, String.valueOf(lockValue))) {
            lockValueThreadLocal.set(String.valueOf(lockValue));
            logger.info("Redis分布式锁，上锁成功！key:[{}], value:[{}]", lockKey, lockValue);
            return true;
        }

        // 获取锁失败，检查锁是否超时
        String oldLockValue = redisTemplate.opsForValue().get(lockKey);
        // 如果当前时间已经大于锁的结束时间，证明这个锁已经超时，则尝试重新设置锁
        if (currentTime > Long.valueOf(oldLockValue)) {
            // 使用getAndSet方法，重新设置锁
            String newOldLockValue = redisTemplate.opsForValue().getAndSet(lockKey, String.valueOf(lockValue));
            // 如果返回的还是之前的值，证明锁被自己拿到
            // 如果返回的值不是之前的值，证明锁已经被其他线程获取，
            if (oldLockValue.equals(newOldLockValue)) {
                logger.info("Redis分布式锁，上锁成功！key:[{}], value:[{}]", lockKey, lockValue);
                lockValueThreadLocal.set(String.valueOf(lockValue));
                return true;
            } else {
                // 如果锁已经被其他线程获取，把刚刚被自己覆盖的值给设置回去，不然会影响别的线程解锁
                redisTemplate.opsForValue().set(lockKey, newOldLockValue);
            }
        }

        return false;
    }

    */
/**
     * 获取分布式锁，无论成功失败，立即返回
     * 获取锁成功后，锁默认有效时间为1分钟
     * @return true:成功获得锁
     *//*

    public static boolean lock(String lockKey) {
        return lock(lockKey, DEFAULT_TIME_OUT);
    }


    */
/**
     * 获取分布式锁，失败则一直阻塞 直到锁获取到为止
     * 获取锁成功后，锁默认有效时间为timeout
     * @return true:成功获得锁
     *//*

    public static boolean blockLock(String lockKey, long timeout) {
        while (!lock(lockKey, timeout)){}
        return true;
    }


    */
/**
     * 解锁指定的key
     *
     * 之所以不用delete直接删除Key，是因为，当，当前线程持有的锁超时，如果直接 delete，就会把别的线程持有的锁给删掉了
     *
     * 而如果用java代码先判断锁中的内容是不是当前线程的，再去调用delete方法，则会有并发问题
     *
     * 鉴于此，采用发送lua脚本到redis客户端执行 exec 命令，会保证 Lua 脚本中的语句在一个事务中执行完毕
     *
     * @param lockKey 需要解锁的key
     * @return true 解锁成功， false 解锁失败
     *//*

    public static boolean unlock(String lockKey) {
        boolean result = false;
        // 获取当前线程持有的锁
        String lockValueByThread = lockValueThreadLocal.get();
        logger.info("Redis分布式锁，准备解锁，key:[{}], valueByThread:[{}]", lockKey, lockValueByThread);
        // 只有当前线程还持有锁，才去解锁
        if (StringUtils.isNotEmpty(lockValueByThread)) {
            List<String> keys = new ArrayList<>(1);
            keys.add(lockKey);
            List<String> values = new ArrayList<>(1);
            values.add(lockValueByThread);
            try {
                // 通过exec命令执行Lua脚本解锁
                Long executeResult = redisTemplate.execute(new RedisCallback<Long>() {
                    @Override
                    public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                        Object connection = redisConnection.getNativeConnection();
                        if (connection instanceof Jedis) {
                            return (Long) ((Jedis) connection).eval(LOCK_SCRIPT, keys, values);
                        } else if (connection instanceof JedisCluster) {
                            return (Long) ((JedisCluster) connection).eval(LOCK_SCRIPT, keys, values);
                        } else {
                            return 0L;
                        }
                    }
                });
                if (executeResult.equals(1L)) {
                    result = true;
                    logger.info("Redis分布式锁，解锁成功！key:[{}]", lockKey);
                } else {
                    logger.error("Redis分布式锁，解锁失败！key:[{}], valueByThread:[{}]", lockKey, lockValueByThread);
                }
            } catch (Exception e) {
                logger.error("Redis分布式锁，解锁异常！key:[{}], valueByThread:[{}]", lockKey, lockValueByThread);
                logger.error(e.getMessage(), e);
            }
        }
        return result;
    }

    */
/**
     * 启动启守护线程扩容锁的过期时间
     * #如果超过原来过期时间的75%则扩容一倍#
     *//*

    public static class LockGuard implements Runnable{
        //给锁有效期扩容的阀值
        private static final double THRESHOLD= 0.75d;
        //最多延期次数
        private static final int MAX_LAY_OVER_TIMES=5;

        private String lockKey;
        private long lockTimeBucket;
        private long lockStartTime;
        private String lockValue;
        //续命线程睡眠周期
        private long lockGuardSleepTimeBucket;

        public LockGuard(String lockKey, long lockTimeBucket, long lockStartTime,long lockValue) {
            this.lockKey = lockKey;
            this.lockTimeBucket = lockTimeBucket;
            this.lockStartTime = lockStartTime;
            this.lockValue = String.valueOf(lockValue);
            this.lockGuardSleepTimeBucket = lockTimeBucket/10;
        }

        @Override
        public void run() {
            logger.info("续命线程启动...");
            int i=0;
            String fetchedVal = redisTemplate.opsForValue().get(lockKey);
            while (!StringUtil.isEmpty(fetchedVal)&&i<MAX_LAY_OVER_TIMES){
                long currentTimeMillis = System.currentTimeMillis();
                long timeDiff = currentTimeMillis-lockStartTime;
                //获取锁失败，检查锁是否超时
                //到达锁扩容阈值
                fetchedVal = redisTemplate.opsForValue().get(lockKey);
                logger.info("Running Params.[LockTimeBucket]:{},[LockStartTime]:{},[CurrentTimeMillis]:{},[TimeDiff]:{}",lockTimeBucket,lockStartTime,currentTimeMillis,timeDiff);
                if(fetchedVal!=null&&ifExceedThreshold(lockTimeBucket,timeDiff)){
                    i++;
                    lockStartTime = currentTimeMillis;
                    String newLockVal = String.valueOf(currentTimeMillis+lockTimeBucket);
                    setNewVal(lockKey,newLockVal,lockValue);
                    this.lockValue = newLockVal;
                    logger.info("续命线程为锁[{}]续命至[{}]",lockKey,newLockVal);
                }else {
                    try {
                        logger.info("续命线程先挂起【{}】毫秒", lockGuardSleepTimeBucket);
                        Thread.sleep(lockGuardSleepTimeBucket);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }

        */
/**
         * CAS判断
         * @param lockKey
         * @param newVal
         * @param oldLockVal
         *//*

        private static void setNewVal(String lockKey,String newVal,String oldLockVal){
            // 通过exec命令执行Lua脚本解锁
            String luaScript = "if redis.call('get', KEYS[1]) == ARGV[2] then redis.call('set',KEYS[1],ARGV[1]) return 1 else return 0 end";
            Long executeResult = redisTemplate.execute(new RedisCallback<Long>() {
                @Override
                public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    Object connection = redisConnection.getNativeConnection();
                    if (connection instanceof Jedis) {
                        return (Long) ((Jedis) connection).eval(luaScript, Collections.singletonList(lockKey), Arrays.asList(newVal,oldLockVal));
                    } else if (connection instanceof JedisCluster) {
                        return (Long) ((JedisCluster) connection).eval(luaScript, Collections.singletonList(lockKey),  Arrays.asList(newVal,oldLockVal));
                    } else {
                        return 0L;
                    }
                }
            });
            if (executeResult.equals(1L)) {
                logger.info("续命线程，续命成功！key:[{}]", lockKey);
            } else {
                logger.error("续命线程，续命失败！key:[{}], valueByThread:[{}]", lockKey);
            }
        }

        */
/**
         * 判断是否超过阀值
         * @return
         *//*

        private static Boolean ifExceedThreshold(  long lockTimeBucket,long timeDiff){
            if(MathUtil.divide(timeDiff,lockTimeBucket).compareTo(LockGuard.THRESHOLD)>=0){
                return true;
            }
            return false;
        }
    }

}
*/
