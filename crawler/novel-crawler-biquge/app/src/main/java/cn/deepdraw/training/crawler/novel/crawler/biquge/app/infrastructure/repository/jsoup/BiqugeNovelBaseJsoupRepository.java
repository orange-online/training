package cn.deepdraw.training.crawler.novel.crawler.biquge.app.infrastructure.repository.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description BiqugeNovelBaseJsoupRepository
 * @Author zhangzhucong
 * @Date 2020/6/9
 **/
public class BiqugeNovelBaseJsoupRepository {

	private Logger logger = LoggerFactory.getLogger(getClass());

    protected static final String BIQUGE_URL = "http://www.biquge.com";

    protected static final String BIQUGE_SEARCH_URL = "http://www.biquge.com/searchbook.php?keyword=";

    protected Document getDocument(String url) {

        try {

            return Jsoup.connect(url).timeout(60 * 1000).post();
        } catch (IOException e) {

        	logger.error("connect url " + url + " failed. exception message: " + e.getMessage(), e);
            throw new IllegalArgumentException("url_connected_failed");
        }
    }
}