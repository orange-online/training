package cn.deepdraw.training.crawler.novel.app.interfaces.core;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.api.dto.NovelQueryDTO;
import cn.deepdraw.training.crawler.novel.app.domain.core.Novel;
import cn.deepdraw.training.framework.api.adapter.query.EntityBaseSpecificationBuilder;

/**
 * Novel Query Adapter
 * @author huangjiancheng
 * 2020-06-19
 */
@Component
public class NovelQueryBuilder extends EntityBaseSpecificationBuilder<Novel, NovelQueryDTO> {

	@Override
	protected void doBuild(Root<Novel> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates, NovelQueryDTO dto) {

		if (StringUtils.isNotBlank(dto.getName())) {

			predicates.add(builder.like(root.get("name").as(String.class), "%" + dto.getName() + "%"));
		}
		if (StringUtils.isNotBlank(dto.getAuthor())) {

			predicates.add(builder.like(root.get("author").as(String.class), "%" + dto.getAuthor() + "%"));
		}
	}
}