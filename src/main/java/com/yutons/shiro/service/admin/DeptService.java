package com.yutons.shiro.service.admin;

import com.yutons.shiro.bean.admin.Dept;

import java.util.List;

/**
 * @author yutons
 * @desc
 * @date 2017/10/26 13:14
 */
public interface DeptService {
    /**
     * 获取部门列表
     * @return
     */
    List<Dept> selectDepts();
}
