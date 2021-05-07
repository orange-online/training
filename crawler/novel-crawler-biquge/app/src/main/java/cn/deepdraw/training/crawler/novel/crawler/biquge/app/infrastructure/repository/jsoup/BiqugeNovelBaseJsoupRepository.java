package cn.deepdraw.training.crawler.novel.crawler.biquge.app.infrastructure.repository.jsoup;

import java.io.IOException;

import org.apache.commons.lang3.Validate;
import org.apache.dubbo.config.annotation.DubboReference;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.deepdraw.training.crawler.novel.crawler.biquge.app.domain.BiqugeConstants;
import cn.deepdraw.training.crawler.novel.crawler.channel.api.ChannelApi;
import cn.deepdraw.training.crawler.novel.crawler.channel.api.dto.ChannelDTO;

/**
 * @Description BiqugeNovelBaseJsoupRepository
 * @Author zhangzhucong
 * @Date 2020/6/9
 **/
public class BiqugeNovelBaseJsoupRepository {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected static final String UNSUPPORTED_SITE = "unsupported_site";
	
	@DubboReference
    protected ChannelApi channelApi;

    protected Document getDocument(String url) {

        try {

            return Jsoup.connect(url).timeout(getConnectionTimeout()).post();
        } catch (IOException e) {

        	logger.error("connect url " + url + " failed. exception message: " + e.getMessage(), e);
            throw new IllegalArgumentException("url_connected_failed");
        }
    }
    
    protected String getURLPrefix() {
    	
    	return getChannel().getLink();
    }
    
    protected String getSearchURLPrefix() {
    	
    	return getURLPrefix() + "/searchbook.php?keyword=";
    }
    
    protected int getConnectionTimeout() {
    	
    	return getChannel().getTimeout();
    }
    
    protected ChannelDTO getChannel() {
    	
    	return Validate.notNull(channelApi.findByChannelCode(BiqugeConstants.SITE), UNSUPPORTED_SITE);
    }
}