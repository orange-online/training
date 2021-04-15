package cn.deepdraw.training.framework.api.adapter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

import cn.deepdraw.training.framework.api.dto.EntityBaseDTO;
import cn.deepdraw.training.framework.orm.mysql.domain.EntityBase;

/**
 * 对象转换器
 * @author huangjiancheng
 *
 * @param <E>
 * @param <D>
 */
public abstract class EntityBaseAdapter<E extends EntityBase, D extends EntityBaseDTO> {

	protected abstract D doAdapt(E e);

	protected D doAdaptBridge(E e) {

		if (e == null) {

			return null;
		}
		D d = doAdapt(e);
		if (d != null) {

			adapt(e, d);
		}
		return d;
	}

	protected void adapt(E e, D d) {

		adaptBase(e, d);
		d.setCreatedBy(e.createdBy());
		d.setLastModifiedBy(e.lastModifiedBy());
	}

	protected void adaptBase(E e, D d) {

		d.setRemoved(e.removed());
		d.setCreatedDate(e.createdDate());
		d.setLastModifiedDate(e.lastModifiedDate());
	}

	public D adapt(E e) {

		return doAdaptBridge(e);
	}

	public List<D> adapt(List<E> es) {

		if (CollectionUtils.isEmpty(es)) {

			return Collections.emptyList();
		}
		return es.stream().map(this::adapt).filter(d -> d != null).collect(Collectors.toList());
	}
}