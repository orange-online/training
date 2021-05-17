package cn.deepdraw.training.novel.crawler.liudatxt.app.infrastructure.repository.jsoup;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapterContent;
import cn.deepdraw.training.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapterContentRepository;

/**
 * LiudatxtNovelChapterContent Jsoup Repository
 * @author huangjiancheng
 * 2020-06-07
 */
@Component
public class LiudatxtNovelChapterContentJsoupRepository extends LiudatxtNovelBaseJsoupRepository implements LiudatxtNovelChapterContentRepository {

	@Override
	public LiudatxtNovelChapterContent findChapterContent(String url) {

		String content = StringUtils.EMPTY;
		try {

			Document document = Jsoup.connect(url).timeout(getConnectionTimeout()).userAgent(getUserAgent()).get(); // 获取溜达小说章节html document
			Element contentEl = document.getElementById("content"); // 从document中解析小说章节内容节点
			contentEl.getElementsByTag("i").remove(); // 从章节内容节点中清洗掉其他冗余的链接
			content = contentEl.html(); // 获取最终章节内容
		} catch (IOException e) {

			logger.error("chapter content url " + url + " connected failed. exception message: " + e.getMessage(), e);
		}
		return LiudatxtNovelChapterContent.of(prepareChapterContent(content));
	}

	private String prepareChapterContent(String content) {

		return content
				.replaceAll("更多到，地址", "")
				.replaceAll("&nbsp;", StringUtils.EMPTY)
				.replaceAll("<br>", "\n")
				.replaceAll("\n\n", "\n");
	}
}