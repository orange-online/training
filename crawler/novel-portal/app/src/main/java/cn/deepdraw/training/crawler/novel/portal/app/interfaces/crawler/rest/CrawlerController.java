package cn.deepdraw.training.crawler.novel.portal.app.interfaces.crawler.rest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.deepdraw.training.crawler.novel.crawler.api.dto.Chapter;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterContent;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.Novel;
import cn.deepdraw.training.crawler.novel.crawler.api.gateway.NovelCrawlerApiGateway;

/**
 * 爬虫Controller
 * @author huangjiancheng
 * 2020-06-08
 */
@RestController
@RequestMapping("/crawler")
public class CrawlerController {

	@DubboReference
	private NovelCrawlerApiGateway crawlerGateway;

	@GetMapping("/novels")
	public Map<String, List<Novel>> find(@RequestParam String keywords) {

		return crawlerGateway.find(keywords).stream().collect(Collectors.groupingBy(Novel::getSite));
	}

	@GetMapping("/chapters")
	public List<Chapter> findChapters(@RequestParam String site, @RequestParam String url) {

		return crawlerGateway.findChapters(site, url);
	}

	@GetMapping("/contents")
	public ChapterContent findChapterContents(@RequestParam String site, @RequestParam String url) {

		return crawlerGateway.findChapterContent(site, url);
	}
}