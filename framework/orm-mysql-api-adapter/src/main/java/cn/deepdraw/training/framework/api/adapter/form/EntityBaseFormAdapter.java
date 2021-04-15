package cn.deepdraw.training.framework.api.adapter.form;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import cn.deepdraw.training.framework.api.dto.form.EntityBaseFormDTO;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;
import cn.deepdraw.training.framework.orm.mysql.domain.EntityBase;

/**
 * 表单对象转换器
 * @author huangjiancheng
 * 2018-12-27
 * @param <E>
 * @param <D>
 */
public abstract class EntityBaseFormAdapter<E extends EntityBase, D extends EntityBaseFormDTO> {

	protected abstract E doAdapt(D d);

	protected E doAdaptBridge(D d) throws WebAppRuntimeException {

		if (d == null) {

			return null;
		}
		E e = doAdapt(d);
		if (e != null) {

			adapt(d, e);
		}
		return e;
	}

	protected void adapt(D d, E e) throws WebAppRuntimeException {

		// TODO do nothing
	}

	public E adapt(D d) throws WebAppRuntimeException {

		return doAdaptBridge(d);
	}

	public List<E> adapt(List<D> ds) throws WebAppRuntimeException {

		if (CollectionUtils.isEmpty(ds)) {

			return Collections.emptyList();
		}
		List<E> es = new ArrayList<E>();
		for (D d : ds) {

			es.add(adapt(d));
		}
		return es;
	}
}