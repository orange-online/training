package cn.deepdraw.training.framework.web.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

/**
 * String To Date Converter
 * @author huangjiancheng
 * 2018-10-08
 */
public class String2DateConverter implements Converter<String, Date> {

	private static final Logger logger = LoggerFactory.getLogger(String2DateConverter.class);

	private static final String[] formats = new String[] { "yyyy-MM", "yyyy-MM-dd", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss" };

	@Override
	public Date convert(String source) {

		if (StringUtils.isBlank(source)) {

			return null;
		}
		String trimmedSource = source.trim();
		if (trimmedSource.matches("^\\d{4}-\\d{1,2}$")) {

			return formatDate(trimmedSource, formats[0]);
		} else if (trimmedSource.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {

			return formatDate(trimmedSource, formats[1]);
		} else if (trimmedSource.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {

			return formatDate(trimmedSource, formats[2]);
		} else if (trimmedSource.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {

			return formatDate(trimmedSource, formats[3]);
		} else {

			throw new IllegalArgumentException("Invalid date value '" + source + "'");
		}
	}

	/**
	 * 格式化日期
	 * 
	 * @param dateValue String 字符型日期
	 * @param format  String 格式
	 * @return Date 日期
	 */
	private Date formatDate(String dateValue, String format) {

		Date date = null;
		try {

			date = new SimpleDateFormat(format).parse(dateValue);
		} catch (Exception e) {

			logger.error("cannot parse date '" + dateValue + "'.", e);
		}
		return date;
	}
}