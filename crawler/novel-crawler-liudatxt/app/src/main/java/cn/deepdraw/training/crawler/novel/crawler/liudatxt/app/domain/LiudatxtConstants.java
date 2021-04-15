package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain;

/**
 * 溜达小说常量
 * @author huangjiancheng
 * 2020-06-12
 */
public final class LiudatxtConstants {

	private LiudatxtConstants() {}

	public static final String URL_BASE = "http://www.txtshuku.org";

	public static final String URL_QUERY = URL_BASE + "/search.php";

	public static final int TIMEOUT_MILLIS = 60 * 1000;
	
	public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";
}