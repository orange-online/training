package cn.deepdraw.training.framework.api.conv;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

import cn.deepdraw.training.framework.api.dto.EntityBaseDTO;
import cn.deepdraw.training.framework.orm.mysql.domain.EntityBase;

/**
 * EntityBase Conv
 * @author huangjiancheng
 * @Date 2021-04-19
 * @param <E>
 * @param <D>
 */
public abstract class EntityBaseConv<E extends EntityBase, D extends EntityBaseDTO> {

	protected abstract D doing(E e);

	protected D after(E e, D d) {

		if (d != null) {

			d.setRemoved(e.removed());
			d.setCreatedDate(e.createdDate());
			d.setLastModifiedDate(e.lastModifiedDate());
			d.setCreatedBy(e.createdBy());
			d.setLastModifiedBy(e.lastModifiedBy());
		}
		return d;
	}

	protected D bridge(E e) {

		if (e == null) {

			return null;
		}
		return after(e, doing(e));
	}

	public D done(E e) {

		return bridge(e);
	}

	public List<D> done(List<E> es) {

		if (CollectionUtils.isEmpty(es)) {

			return Collections.emptyList();
		}
		return es.stream().map(this::done).filter(d -> d != null).collect(Collectors.toList());
	}
}