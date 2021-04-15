package cn.deepdraw.training.framework.orm.mysql.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import cn.deepdraw.training.framework.orm.mysql.constants.ColumnDefinitionConstants;

/**
 * @GenericGenerator Hibernate主键策略生成器
 * Hibernate提供多种主键生成策略，有点是类似于JPA，以下是Hibernate特有的:
 * native: 对于oracle采用Sequence方式，对于MySQL和SQL Server采用identity（自增主键生成机制），native就是将主键的生成工作交由数据库完成，Hibernate不管（常用）；
 * uuid: 采用128位的uuid算法生成主键，uuid被编码为一个32位16进制数字的字符串，占用空间大（字符串类型）；
 * hilo: 使用hilo生成策略，要在数据库中建立一张额外的表，默认表名为hibernate_unique_key，默认字段为integer类型，名称是next_hi（比较少用）；
 * assigned: 在插入数据的时候主键由程序处理（很常用），这是<generator>元素没有指定时的默认生成策略。等同于JPA中的AUTO；
 * identity: 使用SQL Server和MySQL的自增字段，这个方法不能放到Oracle中，Oracle不支持自增字段，要设定sequence（MySQL和SQL Server中很常用），等同于JPA中的INDENTITY；
 * select: 使用触发器生成主键（主要用于早期的数据库主键生成机制，少用）；
 * sequence: 调用底层数据库的序列来生成主键，要设定序列名，不然Hibernate无法找到；
 * seqhilo: 通过hilo算法实现，但是主键历史保存在Sequence中，适用于支持Sequence的数据库，如Oracle（比较少用）；
 * increment: 插入数据的时候Hibernate会给主键添加一个自增的主键，但是一个Hibernate实例就维护一个计数器，所以在多个实例运行的时候不能使用这个方法；
 * foreign: 使用另外一个相关联的对象的主键，通常和<one-to-one>联合起来使用；
 * guid: 采用数据库底层的guid算法机制，对应MYSQL的uuid()函数，SQL Server的newid()函数，Oracle的rawtohex(sys_guid())函数等；
 * uuid.hex: 看uuid，建议用uuid替换；
 * sequence-identity: sequence策略的扩展，采用立即检索策略来获取sequence值，需要JDBC3.0和JDK4以上（含1.4）版本；
 * @author huangjiancheng
 * @date Jul 24, 2018
 */
@MappedSuperclass
public abstract class IdStringEntity extends EntityBase {

	protected static final long serialVersionUID = Long.MAX_VALUE;

	@Id
	@GenericGenerator(name = "short_uuid", strategy = "cn.deepdraw.training.framework.orm.mysql.identifier.UUIDIncrementGenerator")
	@GeneratedValue(generator = "short_uuid")
	@Column(name = "entity_id", columnDefinition = ColumnDefinitionConstants.VARCHAR_45)
	private String entityId;

	public String entityId() {

		return entityId;
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

		if (object instanceof IdStringEntity) {

			IdStringEntity idEntity = (IdStringEntity) object;
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