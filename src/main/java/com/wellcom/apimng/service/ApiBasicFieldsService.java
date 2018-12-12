/*
 * Powered By [wellcomframework]
 */

package com.wellcom.apimng.service;

import org.g4studio.common.service.BaseService;
import org.g4studio.core.metatype.Dto;

//@see com.wellcom.apimng.service.impl.ApiBasicFieldsServiceImpl
public interface ApiBasicFieldsService extends BaseService{

	/**
	 * 保存
	 * @param pDto
	 * @return
	 */
	public Dto saveApiBasicFields(Dto pDto);
	
	/**
	 * 修改
	 * @param pDto
	 * @return
	 */
	public Dto updateApiBasicFields(Dto pDto);
	
	
	/**
	 * 删除
	 * @param pDto
	 * @return
	 */
	public Dto deleteApiBasicFields(Dto pDto);
	
	/**
	 * apiid删除
	 * @param pDto
	 * @return
	 */
	public Dto apiiddeleteApiBasicFields(Dto pDto);
	
}
