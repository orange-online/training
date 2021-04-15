package cn.deepdraw.training.framework.api.adapter;

import org.springframework.data.domain.Page;

import cn.deepdraw.training.framework.api.dto.EntityBaseDTO;
import cn.deepdraw.training.framework.api.dto.page.PageDTO;
import cn.deepdraw.training.framework.api.dto.page.PageRequest;
import cn.deepdraw.training.framework.orm.mysql.domain.EntityBase;

/**
 * 基类分页转换器
 * @author huangjiancheng
 * 2018-09-21
 * @param <E>
 * @param <D>
 */
public abstract class EntityBasePageAdapter<E extends EntityBase, D extends EntityBaseDTO> extends EntityBaseAdapter<E, D> {

	protected void adapt(Page<E> page, PageDTO<D> pageDTO) {

		pageDTO.setRequest(PageRequest.instance().limit(page.getPageable().getPageSize()).offset(page.getPageable().getPageNumber() + 1));
		pageDTO.setMore((int) page.getTotalElements());
		pageDTO.setData(adapt(page.getContent()));
	}

	public PageDTO<D> adapt(Page<E> page) {

		PageDTO<D> pageDTO = new PageDTO<>();
		if (page != null) {

			adapt(page, pageDTO);
		}
		return pageDTO;
	}
}