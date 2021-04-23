package cn.deepdraw.training.crawler.novel.portal.app.interfaces.novel.rest;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.deepdraw.training.crawler.novel.api.NovelApi;
import cn.deepdraw.training.crawler.novel.api.NovelPackagingEventApi;
import cn.deepdraw.training.crawler.novel.api.dto.NovelDTO;
import cn.deepdraw.training.crawler.novel.api.dto.NovelPackagingEventDTO;
import cn.deepdraw.training.crawler.novel.api.dto.NovelQueryDTO;
import cn.deepdraw.training.framework.api.dto.page.PageDTO;
import cn.deepdraw.training.framework.api.dto.page.PageRequest;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * Novel Controller
 * @author huangjiancheng
 * 2020-06-19
 */
@RestController
@RequestMapping("/novels")
public class NovelController {

	@DubboReference
	private NovelApi novelApi;
	
	@DubboReference
	private NovelPackagingEventApi packagingEventApi;

	@GetMapping
	public PageDTO<NovelDTO> findByPage(@ModelAttribute NovelQueryDTO query, @ModelAttribute PageRequest request) {

		return novelApi.findByPage(query, request);
	}

	@GetMapping("/create")
	public NovelDTO create(@RequestParam String site, @RequestParam String url) throws WebAppRuntimeException {

		return novelApi.crawl(site, url);
	}

	@GetMapping("/{novelId}")
	public NovelDTO findByNovelId(@PathVariable Long novelId) {

		return novelApi.findByNovelId(novelId);
	}

	@GetMapping("/events/{eventId}")
	public NovelPackagingEventDTO publishPackagingEvent(@PathVariable Long eventId) {

		return packagingEventApi.publish(eventId);
	}
}