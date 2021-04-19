package cn.deepdraw.training.framework.orm.mysql.domain.identifier;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

/**
 * 扩展主键生成策略: 使用雪花算法生成策略
 * @author huangjiancheng
 * @Date 2021-04-19
 */
public class SnowflakeIdentifierGenerator implements IdentifierGenerator, Configurable {
	
	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {}
	
	@Override
	public Serializable generate(SharedSessionContractImplementor sessionImplementor, Object object) throws HibernateException {
		
		return SnowflakeIdGenerator.getInstance().nextIdentifier();
	}
}