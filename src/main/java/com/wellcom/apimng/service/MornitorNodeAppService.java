/*
 * Powered By [wellcomframework]
 */

package com.wellcom.apimng.service;

import org.g4studio.common.service.BaseService;
import org.g4studio.core.metatype.Dto;

//@see com.wellcom.apimng.service.impl.MornitorNodeAppServiceImpl
public interface MornitorNodeAppService extends BaseService{

	/**
	 * 保存
	 * @param pDto
	 * @return
	 */
	public Dto saveMornitorNodeApp(Dto pDto);
	
	/**
	 * 修改
	 * @param pDto
	 * @return
	 */
	public Dto updateMornitorNodeApp(Dto pDto);
	
	
	/**
	 * 删除
	 * @param pDto
	 * @return
	 */
	public Dto deleteMornitorNodeApp(Dto pDto);
	
}
