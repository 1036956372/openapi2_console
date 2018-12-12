/*
 * Powered By [wellcomframework]
 */

package com.wellcom.apimng.service.impl;

import com.wellcom.apimng.service.ApiBasicFieldsService;

import org.g4studio.common.service.impl.BaseServiceImpl;
import org.g4studio.core.metatype.Dto;
import org.g4studio.core.metatype.impl.BaseDto;



public class ApiBasicFieldsServiceImpl  extends BaseServiceImpl implements ApiBasicFieldsService{

	/**
	 * 保存
	 * @param pDto
	 * @return
	 */
	public Dto saveApiBasicFields(Dto pDto){
		Dto outDto = new BaseDto();
		Integer count = (Integer) g4Dao.queryForObject("ApiBasicFields.checkApiBasicFieldsByIndex", pDto);
		if (count != 0) {
			outDto.put("success", new Boolean(false));
			outDto.put("msg", "违反唯一约束.");
			return outDto;
		} else {
			g4Dao.insert("ApiBasicFields.saveApiBasicFields", pDto);
			outDto.put("success", new Boolean(true));
		}
		return outDto;
	}
	
	/**
	 * 修改
	 * @param pDto
	 * @return
	 */
	public Dto updateApiBasicFields(Dto pDto){
		g4Dao.update("ApiBasicFields.updateApiBasicFields", pDto);
		return null;
	}
	
	
	/**
	 * 删除
	 * @param pDto
	 * @return
	 */
	public Dto deleteApiBasicFields(Dto pDto){
		Dto dto = new BaseDto();
		String[] arrChecked = pDto.getAsString("strChecked").split(",");
		for (int i = 0; i < arrChecked.length; i++) {
			dto.put("filedsid", arrChecked[i]);
			g4Dao.delete("ApiBasicFields.deleteApiBasicFields", dto);
		}
		return null;
	}
	
	/**
	 * apiid删除
	 * @param pDto
	 * @return
	 */
	public Dto apiiddeleteApiBasicFields(Dto pDto){
		Dto dto = new BaseDto();
		String[] arrChecked = pDto.getAsString("strChecked").split(",");
		for (int i = 0; i < arrChecked.length; i++) {
			dto.put("apiid", arrChecked[i]);
			g4Dao.delete("ApiBasicFields.apiiddeleteApiBasicFields", dto);
		}
		return null;
	}
	
}
