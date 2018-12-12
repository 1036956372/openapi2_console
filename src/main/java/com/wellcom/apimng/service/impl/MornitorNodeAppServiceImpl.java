/*
 * Powered By [wellcomframework]
 */

package com.wellcom.apimng.service.impl;

import com.wellcom.apimng.service.MornitorNodeAppService;

import org.g4studio.common.service.impl.BaseServiceImpl;
import org.g4studio.core.metatype.Dto;
import org.g4studio.core.metatype.impl.BaseDto;
import org.g4studio.core.util.CodeUtil;
import org.g4studio.core.util.G4Constants;
import org.g4studio.core.util.G4Utils;
import org.g4studio.system.common.util.SystemConstants;
import org.g4studio.system.common.util.idgenerator.IDHelper;


public class MornitorNodeAppServiceImpl  extends BaseServiceImpl implements MornitorNodeAppService{

	/**
	 * 保存
	 * @param pDto
	 * @return
	 */
	public Dto saveMornitorNodeApp(Dto pDto){
		Dto outDto = new BaseDto();
		Integer count = (Integer) g4Dao.queryForObject("MornitorNodeApp.checkMornitorNodeAppByIndex", pDto);
		if (count != 0) {
			outDto.put("success", new Boolean(false));
			outDto.put("msg", "违反唯一约束.");
			return outDto;
		} else {
			g4Dao.insert("MornitorNodeApp.saveMornitorNodeApp", pDto);
			outDto.put("success", new Boolean(true));
		}
		return outDto;
	}
	
	/**
	 * 修改
	 * @param pDto
	 * @return
	 */
	public Dto updateMornitorNodeApp(Dto pDto){
		g4Dao.update("MornitorNodeApp.updateMornitorNodeApp", pDto);
		return null;
	}
	
	
	/**
	 * 删除
	 * @param pDto
	 * @return
	 */
	public Dto deleteMornitorNodeApp(Dto pDto){
		Dto dto = new BaseDto();
		String[] arrChecked = pDto.getAsString("strChecked").split(",");
		for (int i = 0; i < arrChecked.length; i++) {
			dto.put("appid", arrChecked[i]);
			g4Dao.delete("MornitorNodeApp.deleteMornitorNodeApp", dto);
		}
		return null;
	}
	
}
