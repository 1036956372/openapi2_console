package com.wellcom.student.service;

import org.g4studio.common.service.BaseService;
import org.g4studio.core.metatype.Dto;
/**
 * @author 20181202 邱超强
 * @version 1.0
 * */
public interface StudentService extends BaseService {
	/**
	 * 保存学员信息
	 * @param	要被修改的学员信息
	 * @return	保存事件的结果
	 * */
	public Dto saveStuInfo(Dto pDto);
	/**
	 * 更新学员信息
	 * @param	要被修改的学员信息
	 * @return	保存事件的结果
	 * */
	public Dto updateStuInfo(Dto pDto);
	/**
	 * 删除学员信息
	 * @param	要被修改的学员信息
	 * @return	保存事件的结果
	 * */
	public Dto deleteStuInfo(Dto pDto);

}
