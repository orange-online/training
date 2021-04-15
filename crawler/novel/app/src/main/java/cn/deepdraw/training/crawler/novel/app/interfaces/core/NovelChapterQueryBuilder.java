package cn.deepdraw.training.crawler.novel.app.interfaces.core;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.api.dto.NovelChapterQueryDTO;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapter;
import cn.deepdraw.training.framework.api.adapter.query.EntityBaseSpecificationBuilder;

/**
 * NovelChapter Query Adapter
 * @author huangjiancheng
 * 2020-07-22
 */
@Component
public class NovelChapterQueryBuilder extends EntityBaseSpecificationBuilder<NovelChapter, NovelChapterQueryDTO> {

	@Override
	protected void doBuild(Root<NovelChapter> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates, NovelChapterQueryDTO dto) {

		if (StringUtils.isNotBlank(dto.getNovelId())) {

			predicates.add(builder.equal(root.get("novel").get("novelId").as(String.class), dto.getNovelId()));
		}
		if (StringUtils.isNotBlank(dto.getSite()) && EnumUtils.isValidEnum(Site.class, dto.getSite())) {

			predicates.add(builder.equal(root.get("addr").get("site").as(Site.class), EnumUtils.getEnum(Site.class, dto.getSite())));
		}
		if (StringUtils.isNotBlank(dto.getName())) {

			predicates.add(builder.like(root.get("name").as(String.class), "%" + dto.getName() + "%"));
		}
	}
}