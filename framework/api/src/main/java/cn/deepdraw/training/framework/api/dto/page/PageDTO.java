package cn.deepdraw.training.framework.api.dto.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.deepdraw.training.framework.api.dto.EntityBaseDTO;

/**
 * @description 分页dto
 * @author xujunfeng
 * @date 2018年9月10日
 */
public class PageDTO<T extends EntityBaseDTO> implements Serializable {

	private static final long serialVersionUID = Long.MAX_VALUE;

	private PageRequest request;

	private int more;

	private List<T> data = new ArrayList<>();

	public PageRequest getRequest() {
		return request;
	}

	public void setRequest(PageRequest request) {
		this.request = request;
	}

	public int getMore() {
		return more;
	}

	public void setMore(int more) {
		this.more = more;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}