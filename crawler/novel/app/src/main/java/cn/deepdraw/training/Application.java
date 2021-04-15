package cn.deepdraw.training;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cn.deepdraw.training.framework.motan.spring.config.MotanConfigContextInitializer;
import cn.deepdraw.training.framework.orm.mysql.spring.datasource.DatasourceConfigContextInitializer;

/**
 * 启动类
 * @author huangjiancheng
 * 2020-05-21
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication application = new SpringApplication(Application.class);
		application.addInitializers(new DatasourceConfigContextInitializer());
		application.addInitializers(new MotanConfigContextInitializer());
		application.setBannerMode(Mode.OFF);
		application.run(args);
	}
}