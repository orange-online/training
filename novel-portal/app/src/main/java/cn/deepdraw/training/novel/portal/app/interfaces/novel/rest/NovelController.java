package cn.deepdraw.training.novel.portal.app.interfaces.novel.rest;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.deepdraw.training.framework.api.dto.page.PageDTO;
import cn.deepdraw.training.framework.api.dto.page.PageRequest;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;
import cn.deepdraw.training.novel.api.NovelApi;
import cn.deepdraw.training.novel.api.NovelPackagingEventApi;
import cn.deepdraw.training.novel.api.dto.NovelDTO;
import cn.deepdraw.training.novel.api.dto.NovelPackagingEventDTO;
import cn.deepdraw.training.novel.api.dto.NovelPageRequest;

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
	public PageDTO<NovelDTO> findByPage(@ModelAttribute NovelPageRequest novelReq, @ModelAttribute PageRequest pageReq) {

		return novelApi.findByPage(novelReq, pageReq);
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