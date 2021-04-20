package cn.deepdraw.training.framework.api.dto;

/**
 * IdEntity DTO
 * @author huangjiancheng
 * 2018-08-27
 */
public abstract class IdEntityDTO extends EntityBaseDTO {

	private static final long serialVersionUID = Long.MAX_VALUE;

	private Long entityId;

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
}