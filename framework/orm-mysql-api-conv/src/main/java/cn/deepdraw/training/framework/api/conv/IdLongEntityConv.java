package cn.deepdraw.training.framework.api.conv;

import cn.deepdraw.training.framework.api.dto.IdEntityDTO;
import cn.deepdraw.training.framework.orm.mysql.domain.IdLongEntity;

/**
 * IdLongEntity Conv
 * @author huangjiancheng
 * @Date 2021-04-19
 * @param <E>
 * @param <D>
 */
public abstract class IdLongEntityConv<E extends IdLongEntity, D extends IdEntityDTO> extends EntityBaseConv<E, D> {

	@Override
	protected D bridge(E e) {

		D d = super.bridge(e);
		if (d != null) {

			d.setEntityId(e.entityId());
		}
		return d;
	}
}
