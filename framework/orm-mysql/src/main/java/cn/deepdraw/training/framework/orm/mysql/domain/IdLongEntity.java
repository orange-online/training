package cn.deepdraw.training.framework.orm.mysql.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * @GenericGenerator Hibernate主键策略生成器
 * @author huangjiancheng
 * @date Jul 24, 2018
 */
@MappedSuperclass
public abstract class IdLongEntity extends EntityBase {

	protected static final long serialVersionUID = Long.MAX_VALUE;

	@Id
	@GenericGenerator(name = "snowflake_identifier", strategy = "cn.deepdraw.training.framework.orm.mysql.domain.identifier.SnowflakeIdentifierGenerator")
	@GeneratedValue(generator = "snowflake_identifier")
	@Column(name = "entity_id")
	private Long entityId;

	public Long entityId() {

		return entityId;
	}

	public void entityId(Long entityId) {

		this.entityId = entityId;
	}

	/**
	 * 重写equals方法,以id为准判断
	 */
	@Override
	public boolean equals(Object object) {

		if (this == object) {

			return true;
		}

		if (object == null) {

			return false;
		}

		if (object instanceof IdLongEntity) {

			IdLongEntity idEntity = (IdLongEntity) object;
			if (this.entityId() == null || idEntity.entityId() == null) {

				return false;
			} else {

				return this.entityId().equals(idEntity.entityId());
			}
		}

		return false;

	}

	/**
	 * 重写hashCode方法,直接返回id的hash code
	 */
	@Override
	public int hashCode() {

		return entityId() == null ? super.hashCode() : entityId().hashCode();
	}
}