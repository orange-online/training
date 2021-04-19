package cn.deepdraw.training.framework.api.conv.page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

/**
 * OrderBy Conv
 * @author huangjiancheng
 * @Date 2021-04-19
 */
@Component
public class OrderByConv {

	public static final String ORDER_BY_SEPARATOR = ";";
	
	public static final String PROPERTY_SEPARATOR = "-";

	public Sort conv(String orderByValue) {

		return Sort.by(doConv(orderByValue));
	}

	public List<Order> doConv(String orderByValue) {

		List<Order> orders = new ArrayList<>();
		if (verify(orderByValue)) {
			
			orders.addAll(Stream
					.of(orderByValue.split(ORDER_BY_SEPARATOR))
					.map(orderBy -> new Order(Direction.valueOf(orderBy.split(PROPERTY_SEPARATOR)[1].toUpperCase()), orderBy.split(PROPERTY_SEPARATOR)[0]))
					.collect(Collectors.toList()));
		}
		return orders;
	}

	private boolean verify(String orderByValue) {

		return StringUtils.isNotBlank(orderByValue) && isOrderByValueStructureLegal(orderByValue) && isOrderByValueContentLegal(orderByValue);
	}

	/**
	 * OrderByValue结构是否合法
	 * @param orderByValue
	 * @return
	 */
	private boolean isOrderByValueStructureLegal(String orderByValue) {

		return (orderByValue.split(ORDER_BY_SEPARATOR).length >= 2 || !orderByValue.contains(ORDER_BY_SEPARATOR));
	}

	/**
	 * OrderByValue内容是否合法
	 * @param orderByValue
	 * @return
	 */
	private boolean isOrderByValueContentLegal(String orderByValue) {

		return areAllOrderByValuesNotEmpty(orderByValue) && areAllOrderByValuesLegal(orderByValue);
	}
	
	private boolean areAllOrderByValuesNotEmpty(String orderByValue) {

		return Stream.of(orderByValue.split(ORDER_BY_SEPARATOR)).allMatch(value -> !value.isEmpty());
	}
	
	private boolean areAllOrderByValuesLegal(String orderByValue) {

		return Stream.of(orderByValue.split(ORDER_BY_SEPARATOR)).allMatch(value -> isOrderByValueLegal(value));
	}
	
	private boolean isOrderByValueLegal(String orderByValue) {

		return isStructureLegal(orderByValue)
				&& areAllPropertyNotEmpty(orderByValue)
				&& isDirectionValueLegal(orderByValue);
	}
	
	private boolean isStructureLegal(String orderByValue) {

		return orderByValue.split(PROPERTY_SEPARATOR).length >= 2;
	}
	
	private boolean areAllPropertyNotEmpty(String orderByValue) {

		return Stream.of(orderByValue.split(PROPERTY_SEPARATOR)).allMatch(value -> !value.isEmpty());
	}
	
	private boolean isDirectionValueLegal(String orderByValue) {

		String directionValue = orderByValue.split(PROPERTY_SEPARATOR)[1];
		return Stream.of(Direction.values()).anyMatch(direction -> direction.toString().equals(directionValue.toUpperCase()));
	}
}