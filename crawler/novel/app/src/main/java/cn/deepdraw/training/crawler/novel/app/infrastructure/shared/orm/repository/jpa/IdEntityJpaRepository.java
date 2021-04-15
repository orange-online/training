package cn.deepdraw.training.crawler.novel.app.infrastructure.shared.orm.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.deepdraw.training.crawler.novel.app.domain.shared.IdEntityRepository;
import cn.deepdraw.training.framework.orm.mysql.domain.IdLongEntity;

/**
 * @description id实体JPA repository
 * @author XU JUN FENG
 * @date 2020年2月27日
 */
public interface IdEntityJpaRepository<T extends IdLongEntity> extends IdEntityRepository<T>, JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

	@Override
	default T create(T entity) {

		return save(entity);
	}
	
	@Override
	default T update(T entity) {

		return save(entity);
	}
	
	@Override
	default Page<T> findByPage(Specification<T> specification, Pageable pageable) {

		return findAll(specification, pageable);
	}
}