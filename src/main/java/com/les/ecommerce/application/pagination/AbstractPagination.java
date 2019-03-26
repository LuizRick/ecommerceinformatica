package com.les.ecommerce.application.pagination;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.les.ecommerce.application.IApplicationData;
import com.les.ecommerce.model.EntidadeDominio;

public abstract class AbstractPagination implements IApplicationData {

	protected Page<EntidadeDominio> page;
	protected PageRequest pageRequest;
	protected Sort sort;
	
	
	public Sort getSort() {
		return sort;
	}


	public void setSort(Sort sort) {
		this.sort = sort;
	}


	public Page<EntidadeDominio> pagination() {
		return this.page;
	}
	
	
	public void pagination(Page<EntidadeDominio> pagination) {
		this.page = pagination;
	}
	
	
	public PageRequest getPageRequest () {
		return this.pageRequest;
	}
	
	public void setPageRequest(PageRequest pageRequest) {
		this.pageRequest = pageRequest;
	}
	
}
