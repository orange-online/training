package cn.deepdraw.training.framework.api.adapter.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import cn.deepdraw.training.framework.api.dto.query.EntityBaseQueryDTO;
import cn.deepdraw.training.framework.orm.mysql.domain.EntityBase;

/**
 * EntityBase搜索规格创建器
 * @author huangjiancheng
 * 2018-10-12
 * @param <E>
 * @param <D>
 */
public abstract class EntityBaseSpecificationBuilder<E extends EntityBase, D extends EntityBaseQueryDTO> {

	protected void doBaseBuild(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates, D d) {

		predicates.add(builder.equal(root.get("removed").as(boolean.class), d.isRemoved()));
		if (d.getCreatedDate() != null) {

			predicates.add(builder.equal(root.get("createdDate").as(Date.class), d.getCreatedDate()));
		}
		if (StringUtils.isNotBlank(d.getCreatedBy())) {

			predicates.add(builder.equal(root.get("createdBy").as(String.class), d.getCreatedBy()));
		}
		if (d.getLastModifiedDate() != null) {

			predicates.add(builder.equal(root.get("lastModifiedDate").as(Date.class), d.getLastModifiedDate()));
		}
		if (StringUtils.isNotBlank(d.getLastModifiedBy())) {

			predicates.add(builder.equal(root.get("lastModifiedBy").as(String.class), d.getLastModifiedBy()));
		}
	}

	protected abstract void doBuild(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates, D d);

	public Specification<E> build(D d) {

		return (Root<E> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {

			List<Predicate> predicates = new ArrayList<>();
			doBaseBuild(root, query, builder, predicates, d);
			doBuild(root, query, builder, predicates, d);
			return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
		};
	}
}