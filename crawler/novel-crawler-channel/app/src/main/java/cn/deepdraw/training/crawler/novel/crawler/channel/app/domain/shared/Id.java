package cn.deepdraw.training.crawler.novel.crawler.channel.app.domain.shared;

import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * @Description 唯一标识
 * @author xujunfeng
 * @date: 2019年9月19日
 */
@MappedSuperclass
public abstract class Id {

	protected String id;

	protected Id() {}

	public Id(String id) {

		Validate.notBlank(id, "Id.id cannot be blank");
		this.id = id;
	}

	public String id() {

		return this.id;
	}
	
	@Override
	public boolean equals(Object obj) {

		if (obj == null) {

			return false;
		} else if (this == obj) {

			return true;
		} else if (obj instanceof Id) {

			return equals((Id) obj);
		} else {

			return false;
		}
	}
	
	protected <T extends Id> boolean equals(T id) {

		return id != null && (this == id || StringUtils.equals(this.toString(), id.toString()));
	}

	@Override
	public int hashCode() {

		return this.toString().hashCode();
	}

	@Override
	public String toString() {

		return this.prefix() + "_" + suffix();
	}

	public String suffix() {

		return this.id();
	}

	public abstract String prefix();
}