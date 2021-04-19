package cn.deepdraw.training.framework.orm.mysql.domain.identifier;

/**
 * 生成分布式系统唯一的主键id
 * string类型
 * Snowflake 的结构如下
 * 0-00000000 00000000 00000000 00000000 00000000 00000000 - 00000 - 0000 -000000000000
 * 第一位是符号为，41位表示当前时间戳跟定义好的时间戳的差值，5位
 * 41位的表示(1L << 41) / (1000L * 3600 * 24 * 365L) == 69年;
 * 指定10位的机器位，可以部署1024台机器
 * 所有的数据加起来就是64位，正好是一个Long型数据
 * @author huangjiancheng
 * @Date 2021-04-19
 */
public final class SnowflakeIdGenerator {
	
	/**
	 * 起始的时间戳(2020-02-02 00:00:00)
	 */
	private final static long START_STMP = 1580572800000L;

	/**
	 * 每一部分占用的位数
	 */
	private final static long SEQUENCE_BIT = 12; // 序列号占用的位数
	private final static long MACHINE_BIT = 5; // 机器标识占用的位数
	private final static long DATACENTER_BIT = 5;// 数据中心占用的位数

	/**
	 * 每一部分的最大值
	 */
	private final static long MAX_DATACENTER_ID = -1L ^ (-1L << DATACENTER_BIT);
	private final static long MAX_MACHINE_ID = -1L ^ (-1L << MACHINE_BIT);
	private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

	/**
	 * 每一部分向左的位移
	 */
	private final static long MACHINE_ID_LEFT = SEQUENCE_BIT;
	private final static long DATACENTER_ID_LEFT = SEQUENCE_BIT + MACHINE_BIT;
	private final static long TIMESTMP_LEFT = DATACENTER_ID_LEFT + DATACENTER_BIT;
	
	private long sequence = 0L; // 序列号
	private long lastStmp = -1L;// 上一次时间戳

	private long datacenterId; // 数据中心
	private long machineId; // 机器标识
	
	public SnowflakeIdGenerator() {
		
		datacenterId = 1;
		machineId = 1;
	}
	
	/**
	 * 构造函数
	 * @param datacenterId (0~31)
	 * @param machineId    (0~31)
	 */
	public SnowflakeIdGenerator(long datacenterId, long machineId) {
		
		if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
			
			throw new IllegalArgumentException("datacenterId can't be greater than " + MAX_DATACENTER_ID + " or less than 0");
		}
		if (machineId > MAX_MACHINE_ID || machineId < 0) {
			
			throw new IllegalArgumentException("machineId can't be greater than " + MAX_MACHINE_ID + " or less than 0");
		}
		this.datacenterId = datacenterId;
		this.machineId = machineId;
	}
	
	public static SnowflakeIdGenerator getInstance() {
		
		return SnowFlakeIdGeneratorHolder.INSTANCE;
	}

	/**
	 * 产生下一个ID(该方法是线程安全的)
	 * 
	 * @return
	 */
	public synchronized long nextIdentifier() {
		
		long currStmp = getCurrentTimeMillis();
		
		// 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
		if (currStmp < lastStmp) {
			
			throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
		}
		
		// 如果是同一时间生成的，则进行毫秒内序列
		if (currStmp == lastStmp) {
			
			// 相同毫秒内，序列号自增
			sequence = (sequence + 1) & MAX_SEQUENCE;
			
			// 同一毫秒的序列数已经达到最大（毫秒内序列溢出）
			if (sequence == 0L) {
				
				// 阻塞到下一个毫秒,获得新的时间戳
				currStmp = getNextTimeMillis();
			}
		} else {
			
			// 不同毫秒内，序列号置为0（时间戳改变，毫秒内序列重置）
			sequence = 0L;
		}
		// 上次生成ID的时间截
		lastStmp = currStmp;

		// 移位并通过或运算拼到一起组成ID
		return (currStmp - START_STMP) << TIMESTMP_LEFT // 时间戳部分
				| datacenterId << DATACENTER_ID_LEFT // 数据中心部分
				| machineId << MACHINE_ID_LEFT // 机器标识部分
				| sequence; // 序列号部分
	}

	private long getNextTimeMillis() {
		
		long stmp = getCurrentTimeMillis();
		while (stmp <= lastStmp) {
			
			stmp = getCurrentTimeMillis();
		}
		return stmp;
	}

	private long getCurrentTimeMillis() {
		
		return System.currentTimeMillis();
	}
	
    private static class SnowFlakeIdGeneratorHolder {
    	
        public static SnowflakeIdGenerator INSTANCE = new SnowflakeIdGenerator(13, 14);
    }
}