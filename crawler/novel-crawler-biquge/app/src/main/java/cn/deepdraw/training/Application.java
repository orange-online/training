package cn.deepdraw.training;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 应用启动类
 * @author huangjiancheng
 * 2020-06-06
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication application = new SpringApplication(Application.class);
		application.setBannerMode(Mode.OFF);
		application.run(args);
	}
}