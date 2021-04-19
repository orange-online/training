package cn.deepdraw.training.framework.api.conv;

import org.springframework.data.domain.Page;

import cn.deepdraw.training.framework.api.dto.EntityBaseDTO;
import cn.deepdraw.training.framework.api.dto.page.PageDTO;
import cn.deepdraw.training.framework.api.dto.page.PageRequest;
import cn.deepdraw.training.framework.orm.mysql.domain.EntityBase;

/**
 * EntityBase Page Conv
 * @author huangjiancheng
 * @Date 2021-04-19
 * @param <E>
 * @param <D>
 */
public abstract class EntityBasePageConv<E extends EntityBase, D extends EntityBaseDTO> extends EntityBaseConv<E, D> {

	protected void done(Page<E> page, PageDTO<D> pageDTO) {

		pageDTO.setRequest(PageRequest.instance().limit(page.getPageable().getPageSize()).offset(page.getPageable().getPageNumber() + 1));
		pageDTO.setMore((int) page.getTotalElements());
		pageDTO.setData(done(page.getContent()));
	}

	public PageDTO<D> done(Page<E> page) {

		PageDTO<D> pageDTO = new PageDTO<>();
		if (page != null) {

			done(page, pageDTO);
		}
		return pageDTO;
	}
}