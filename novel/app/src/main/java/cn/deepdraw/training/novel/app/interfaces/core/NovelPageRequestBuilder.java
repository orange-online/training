package cn.deepdraw.training.novel.app.interfaces.core;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.framework.api.builder.query.EntityBaseQueryBuilder;
import cn.deepdraw.training.novel.api.dto.NovelPageRequest;
import cn.deepdraw.training.novel.app.domain.core.Novel;

/**
 * Novel Query Adapter
 * @author huangjiancheng
 * 2020-06-19
 */
@Component
public class NovelPageRequestBuilder extends EntityBaseQueryBuilder<Novel, NovelPageRequest> {

	@Override
	protected void building(Root<Novel> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates, NovelPageRequest dto) {

		if (StringUtils.isNotBlank(dto.getName())) {

			predicates.add(builder.like(root.get("name").as(String.class), "%" + dto.getName() + "%"));
		}
		if (StringUtils.isNotBlank(dto.getAuthor())) {

			predicates.add(builder.like(root.get("author").as(String.class), "%" + dto.getAuthor() + "%"));
		}
	}
}