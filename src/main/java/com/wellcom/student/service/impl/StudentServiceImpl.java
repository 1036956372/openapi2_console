package com.wellcom.student.service.impl;

import org.g4studio.common.service.impl.BaseServiceImpl;
import org.g4studio.core.metatype.Dto;
import org.g4studio.core.metatype.impl.BaseDto;

import com.wellcom.student.service.StudentService;

public class StudentServiceImpl extends BaseServiceImpl implements StudentService {

	@Override
	public Dto saveStuInfo(Dto pDto) {
		Dto outDto = new BaseDto();
		g4Dao.insert("Students.insert", pDto);
		outDto.put("success", new Boolean(true));
		return outDto ;
	}

	@Override
	public Dto updateStuInfo(Dto pDto) {
		g4Dao.update("Students.update", pDto);
		return null;
	}

	@Override
	public Dto deleteStuInfo(Dto pDto) {
		Dto dto = new BaseDto();
		String[] split = pDto.getAsString("strChecked").split(",");
		for (String string : split) {
			dto.put("stuid", string);
			g4Dao.delete("Students.delete",dto);
		}
		return null;
	}

}
