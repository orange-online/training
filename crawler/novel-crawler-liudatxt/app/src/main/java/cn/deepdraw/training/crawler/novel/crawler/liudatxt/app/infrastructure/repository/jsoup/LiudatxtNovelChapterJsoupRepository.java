package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.infrastructure.repository.jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapter;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapterRepository;

/**
 * LiudatxtNovelChapter Jsoup Repository
 * @author huangjiancheng
 * 2020-06-07
 */
@Component
public class LiudatxtNovelChapterJsoupRepository extends LiudatxtNovelBaseJsoupRepository implements LiudatxtNovelChapterRepository {

	@Override
	public List<LiudatxtNovelChapter> findChapters(String url) {

		List<LiudatxtNovelChapter> chapters = new ArrayList<>();
		try {

			Document document = Jsoup.connect(url).timeout(getConnectionTimeout()).userAgent(getUserAgent()).get(); // 获取溜达小说章节html document
			Element chaptersEl = document.getElementById("readerlist"); // 从document中解析小说章节目录节点
			Elements ulEls = chaptersEl.select("ul"); // 从小说章节目录节点解析章节分卷节点
			int index = 0;
			for (Element ulEl : ulEls) {

				Element groupEl = ulEl.selectFirst("h3");
				String ulname = groupEl == null ? StringUtils.EMPTY : groupEl.text();
				for (Element el : ulEl.select("li a")) { // 开始循环构造章节实例

					chapters.add(LiudatxtNovelChapter.of(prepareChapterName(ulname, el.attr("title")), prepareChapterUrl(el.attr("href")), ++index));
				}
			}
		} catch (IOException e) {

			logger.error("chapters url " + url + " connected failed. exception message: " + e.getMessage(), e);
		}
		return chapters;
	}

	private String prepareChapterName(String ulname, String eltitle) {

		String name = eltitle.contains(ulname) ? eltitle : ulname + " " + eltitle;
		return name.replaceAll("　", " ").replaceAll("  ", " ").replaceAll("  ", " ").replaceAll("\t", "");
	}

	private String prepareChapterUrl(String elhref) {

		return getURLPrefix() + elhref;
	}
}