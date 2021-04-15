package cn.deepdraw.training.crawler.novel.app.interfaces.heartbeat.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.deepdraw.training.framework.utils.response.Response;
import cn.deepdraw.training.framework.utils.response.ResponseUtils;

/**
 * 心跳接口
 * @author huangjiancheng
 * 2020-06-06
 */
@RestController
@RequestMapping("/heartbeat")
public class HeartbeatController {
	
//	@Autowired
//	private EntityManagerFactory managerFactory;

	@GetMapping
	public Response heartbeat() {

		// To test "hibernate.cache.use_second_level_cache" config.
//		EntityManager manager = managerFactory.createEntityManager();
//		EntityTransaction transaction = manager.getTransaction();
//		transaction.begin();
//		
//		manager.find(Novel.class, 1L);
//		
//		transaction.commit();
//		manager.close();
//		
//		manager = managerFactory.createEntityManager();
//		transaction = manager.getTransaction();
//		transaction.begin();
//		
//		manager.find(Novel.class, 1L);
		return ResponseUtils.success();
	}
}