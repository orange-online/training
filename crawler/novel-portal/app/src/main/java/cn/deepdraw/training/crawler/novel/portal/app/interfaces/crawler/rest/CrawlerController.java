package cn.deepdraw.training.crawler.novel.portal.app.interfaces.crawler.rest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.deepdraw.training.crawler.novel.crawler.api.NovelCrawlerApi;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterContentDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.NovelDTO;

/**
 * 爬虫Controller
 * @author huangjiancheng
 * 2020-06-08
 */
@RestController
@RequestMapping("/crawler")
public class CrawlerController {

	@Autowired
	private List<NovelCrawlerApi> crawlerApis;

	@GetMapping("/novels")
	public Map<String, List<NovelDTO>> find(@RequestParam String keywords) {

		return crawlerApis.stream().collect(Collectors.toMap(NovelCrawlerApi::site, crawler -> crawler.find(keywords)));
	}

	@GetMapping("/novel")
	public Map<String, NovelDTO> findNovel(@RequestParam String url) {

		return crawlerApis.stream().collect(Collectors.toMap(NovelCrawlerApi::site, crawler -> crawler.findNovel(url)));
	}

	@GetMapping("/chapters")
	public Map<String, List<ChapterDTO>> findChapters(@RequestParam String site, @RequestParam String url) {

		return crawlerApis.stream().filter(crawler -> crawler.site().equals(site)).collect(Collectors.toMap(NovelCrawlerApi::site, crawler -> crawler.findChapters(url)));
	}

	@GetMapping("/contents")
	public Map<String, ChapterContentDTO> findChapterContents(@RequestParam String site, @RequestParam String url) {

		return crawlerApis.stream().filter(crawler -> crawler.site().equals(site)).collect(Collectors.toMap(NovelCrawlerApi::site, crawler -> crawler.findChapterContent(url)));
	}
}