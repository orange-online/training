package cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.infrastructure.repository.jsoup;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.crawler.channel.api.ChannelApi;
import cn.deepdraw.training.crawler.novel.crawler.channel.api.dto.ChannelDTO;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain.XuanshuwangChapter;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain.XuanshuwangChapterContent;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain.XuanshuwangConstants;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain.XuanshuwangNovel;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain.XuanshuwangRepository;
import cn.deepdraw.training.framework.utils.HttpUtils;
import cn.deepdraw.training.framework.utils.HttpUtils.Reply;
import cn.deepdraw.training.framework.utils.collection.MultiValueHashMap;
import cn.deepdraw.training.framework.utils.collection.MultiValueMap;

/**
 * @author：杨攀
 * @date：2020年7月21日 下午4:01:57
 */
@Component
public class XuanshuwangNovelJsoupRepository implements XuanshuwangRepository {

    private static final String UNSUPPORTED_SITE = "unsupported_site";

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ChannelApi channelApi;

    @Override
    public List<XuanshuwangNovel> findByKeywords(String keywords) {

        Reply reply = initiateSearchRequest(keywords);
        
        Elements elements = parseRequestResult(reply);
        
        return createNovels(elements);
    }

    private List<XuanshuwangNovel> createNovels(Elements elements) {

        return elements.stream().map(element -> {

            Elements novelAttributes = element.select("td");
            Element nameElment = novelAttributes.get(0);
            Element authorElement = novelAttributes.get(2);
            String novelUrl = nameElment.select("a").attr("href");
            return XuanshuwangNovel.of(nameElment.text(), authorElement.text(), novelUrl);
        }).collect(Collectors.toList());
    }

    private Elements parseRequestResult(Reply reply) {

        Document document = Jsoup.parse(reply.getContent());
        Elements elements = document.getElementById("content").select("tr");
        elements.remove(0);
        elements.remove(elements.size() - 1);
        return elements;
    }

    private Reply initiateSearchRequest(String keywords) {

        MultiValueMap<String, String> parameters = new MultiValueHashMap<>();
        parameters.add("searchkey", keywords);
        return HttpUtils.post(getSearchURLPrefix(), parameters);
    }

    @Override
    public List<XuanshuwangChapter> findChapters(String novelUrl) {

        Elements elements = parseNovelUrl(novelUrl);
        
        return CollectionUtils.isEmpty(elements) ? Collections.emptyList() : createChapters(novelUrl, elements);
    }

    private List<XuanshuwangChapter> createChapters(String novelUrl, Elements elements) {

        List<XuanshuwangChapter> chapters = new ArrayList<>();
        for (int index = 0; index < elements.size(); index++) {

            Element nameElement = elements.get(index);
            String chapterUrl = novelUrl + nameElement.select("a").attr("href");
            int sequence = index + 1;
            
            XuanshuwangChapter chapter = new XuanshuwangChapter(nameElement.text(), chapterUrl, sequence);
            chapters.add(chapter);
        }
        return chapters;
    }

    private Elements parseNovelUrl(String novelUrl) {

        try {
            Document document = Jsoup.parse(new URL(novelUrl), getConnectionTimeout());
            return document.getElementsByClass("pc_list").select("li");
        } catch (IOException e) {

            logger.error("the chapter content of Xuanshuwang parsed failed, url is " + novelUrl, e);
        }
        
        return null;
    }

    @Override
    public XuanshuwangChapterContent findChapterContent(String chapterUrl) {

        Element contentElement = parseChapterUrl(chapterUrl);
        
        return contentElement == null ? null : new XuanshuwangChapterContent(contentElement.text());
    }

    @Override
    public XuanshuwangNovel findNovel(String url) {

        try {

            Document doc = Jsoup.connect(url).timeout(getConnectionTimeout()).get();
            Elements info = doc.getElementsByClass("info_des");
            String name = info.select("h1").first().text();
            String author = info.select("dl").first().text();
            author = author.substring(author.indexOf("：") + 1);
            return XuanshuwangNovel.of(name, author, url);
        } catch (IOException e) {

            logger.error(e.getMessage());
            return null;
        }
    }

    private Element parseChapterUrl(String chapterUrl) {

        try {
            Document document = Jsoup.parse(new URL(chapterUrl), getConnectionTimeout());
            return document.getElementById("content1");
        } catch (IOException e) {

            logger.error("the chapter content of Xuanshuwang parsed failed, url is " + chapterUrl, e);
        }
        return null;
    }
    
    private String getURLPrefix() {
    	
    	return getChannel().getLink();
    }
    
    private String getSearchURLPrefix() {
    	
    	return getURLPrefix() + "/search.php";
    }
    
    private int getConnectionTimeout() {
    	
    	return getChannel().getTimeout();
    }
    
    private ChannelDTO getChannel() {
    	
    	return Validate.notNull(channelApi.findByChannelCode(XuanshuwangConstants.SITE), UNSUPPORTED_SITE);
    }
}