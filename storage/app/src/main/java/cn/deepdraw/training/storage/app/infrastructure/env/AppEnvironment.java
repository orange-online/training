package cn.deepdraw.training.storage.app.infrastructure.env;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 应用环境配置
 * @author huangjiancheng
 * 2020-07-17
 */
@Component
public class AppEnvironment {

	@Value("${app.dir}")
	private String dir;

	@Value("${app.url}")
	private String url;

	public String dir() {

		return dir;
	}

	public String url() {

		return url;
	}
}