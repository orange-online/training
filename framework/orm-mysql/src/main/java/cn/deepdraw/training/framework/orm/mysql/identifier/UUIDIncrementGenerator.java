package cn.deepdraw.training.framework.orm.mysql.identifier;

import java.io.Serializable;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

/**
 * 扩展主键生成策略: 使用UUID生成策略
 * 
 * @author huangjiancheng
 * @date Jul 24, 2018
 */
public class UUIDIncrementGenerator implements IdentifierGenerator, Configurable {

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {}

	@Override
	public Serializable generate(SharedSessionContractImplementor sessionImplementor, Object object) throws HibernateException {

		return StringUtils.remove(UUID.randomUUID().toString().toLowerCase(), '-'); // TODO 此处暂时随机生成，但随机算法需要优化 by 黄建成
	}
}