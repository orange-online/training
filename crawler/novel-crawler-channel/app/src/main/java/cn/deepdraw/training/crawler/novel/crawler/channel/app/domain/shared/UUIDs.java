package cn.deepdraw.training.crawler.novel.crawler.channel.app.domain.shared;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

/**
 * UUID生成器
 * @author huangjiancheng
 * 2020-06-09
 */
public final class UUIDs {

	private UUIDs() {}
	
	public static String instance() {

		return StringUtils.remove(UUID.randomUUID().toString().toLowerCase(), '-');
	}
}