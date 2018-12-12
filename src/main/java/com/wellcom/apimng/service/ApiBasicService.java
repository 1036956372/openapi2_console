/*
 * Powered By [wellcomframework]
 */

package com.wellcom.apimng.service;

import org.g4studio.common.service.BaseService;
import org.g4studio.core.metatype.Dto;

//@see com.wellcom.apimng.service.impl.ApiBasicServiceImpl
public interface ApiBasicService extends BaseService{

	/**
	 * 保存
	 * @param pDto
	 * @return
	 */
	public Dto saveApiBasic(Dto pDto);
	
	/**
	 * 修改
	 * @param pDto
	 * @return
	 */
	public Dto updateApiBasic(Dto pDto);
	
	
	/**
	 * 删除
	 * @param pDto
	 * @return
	 */
	public Dto deleteApiBasic(Dto pDto);
	
}
