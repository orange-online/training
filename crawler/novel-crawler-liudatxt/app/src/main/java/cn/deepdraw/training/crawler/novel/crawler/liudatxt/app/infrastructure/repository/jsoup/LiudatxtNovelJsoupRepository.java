package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.infrastructure.repository.jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtConstants;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovel;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovelRepository;
import cn.deepdraw.training.framework.utils.HttpUtils;
import cn.deepdraw.training.framework.utils.HttpUtils.Reply;
import cn.deepdraw.training.framework.utils.collection.MultiValueHashMap;

/**
 * LiudatxtNovel Jsoup Repository
 * @author huangjiancheng
 * 2020-06-07
 */
@Component
public class LiudatxtNovelJsoupRepository implements LiudatxtNovelRepository {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public List<LiudatxtNovel> find(String keywords) {

		List<LiudatxtNovel> novels = new ArrayList<>();
		MultiValueHashMap<String, String> parameters = new MultiValueHashMap<String, String>();
		parameters.add("searchkey", keywords);
		Reply reply = HttpUtils.post(LiudatxtConstants.URL_QUERY, parameters);
		Document document = Jsoup.parse(reply.getContent());
		document.getElementById("sitembox").select("dl").forEach(dlEl -> {

			Elements aEl = dlEl.select("h3 a");
			Element authorEl = dlEl.select("dd.book_other span").first();
			novels.add(LiudatxtNovel.of(aEl.text(), authorEl.text(), prepareNovelUrl(aEl.attr("href"))));
		});
		return novels;
	}

	private String prepareNovelUrl(String url) {

		return LiudatxtConstants.URL_BASE + url;
	}

	@Override
	public LiudatxtNovel findNovel(String url) {

		try {

			Document doc = Jsoup.connect(url).timeout(LiudatxtConstants.TIMEOUT_MILLIS).userAgent(LiudatxtConstants.USER_AGENT).get();
			Element smallcons = doc.getElementById("smallcons");
			String name = smallcons.selectFirst("h1").text();
			String author = smallcons.getElementsByTag("a").text();
			return LiudatxtNovel.of(name, author, url);
		} catch (IOException e) {

			logger.error("novel url " + url + " connected failed. exception message: " + e.getMessage(), e);
			return null;
		}
	}
}