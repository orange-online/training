package cn.deepdraw.training.crawler.novel.app.domain.shared;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.deepdraw.training.framework.orm.mysql.domain.EntityBase;

/**
 * 仓储接口
 * @author huangjiancheng
 * 2020-06-09
 */
public interface IdEntityRepository<T extends EntityBase> {

	T create(T entity);

	T update(T entity);

	Page<T> findByPage(Specification<T> specification, Pageable pageable);

	default void remove(T entity) {

		entity.remove();
		update(entity);
	}

	default String generateIdString() {

		return UUIDs.instance();
	}
}