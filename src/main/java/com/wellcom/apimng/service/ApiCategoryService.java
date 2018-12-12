/*
 * Powered By [wellcomframework]
 */

package com.wellcom.apimng.service;

import org.g4studio.common.service.BaseService;
import org.g4studio.core.metatype.Dto;

//@see com.wellcom.apimng.service.impl.ApiCategoryServiceImpl
public interface ApiCategoryService extends BaseService{

	/**
	 * 保存
	 * @param pDto
	 * @return
	 */
	public Dto saveApiCategory(Dto pDto);
	
	/**
	 * 修改
	 * @param pDto
	 * @return
	 */
	public Dto updateApiCategory(Dto pDto);
	
	
	/**
	 * 删除
	 * @param pDto
	 * @return
	 */
	public Dto deleteApiCategory(Dto pDto);
	
}
