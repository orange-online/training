package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.infrastructure.repository.jsoup;

import java.io.IOException;

import org.apache.commons.lang3.Validate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.deepdraw.training.crawler.novel.crawler.channel.api.ChannelApi;
import cn.deepdraw.training.crawler.novel.crawler.channel.api.dto.ChannelDTO;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtConstants;

/**
 * LiudatxtNovel Base Jsoup Repository
 * @author huangjiancheng
 * @Date 2021-05-06
 */
public class LiudatxtNovelBaseJsoupRepository {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected static final String UNSUPPORTED_SITE = "unsupported_site";

    @Autowired
    protected ChannelApi channelApi;

    protected Document getDocument(String url) {

        try {

            return Jsoup.connect(url).timeout(getConnectionTimeout()).post();
        } catch (IOException e) {

        	logger.error("connect url " + url + " failed. exception message: " + e.getMessage(), e);
            throw new IllegalArgumentException("url_connected_failed");
        }
    }
    
    protected String getUserAgent() {
    	
    	return "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:88.0) Gecko/20100101 Firefox/88.0";
    }
    
    protected String getURLPrefix() {
    	
    	return getChannel().getLink();
    }
    
    protected String getSearchURLPrefix() {
    	
    	return getURLPrefix() + "/search.php";
    }
    
    protected int getConnectionTimeout() {
    	
    	return getChannel().getTimeout();
    }
    
    protected ChannelDTO getChannel() {
    	
    	return Validate.notNull(channelApi.findByChannelCode(LiudatxtConstants.SITE), UNSUPPORTED_SITE);
    }
}