package com.yutons.shiro.bean.admin;


import com.yutons.shiro.bean.PageBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.io.Serializable;

/**
 * @author yutons
 * @desc shiro权限控制之角色实体类
 * @date 2017/10/25 15:56
 */
@Data
@Log4j
@NoArgsConstructor
@AllArgsConstructor
public class Role extends PageBean implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    protected Integer id;
    /**
     * 角色ID
     */
    protected Integer roleId;
    /**
     * 角色名
     */
    protected String name;
    /**
     * 角色字符串
     */
    protected String sn;
    /**
     * 备注
     */
    protected String remark;
    /**
     * 角色拥有的权限
     */
    protected String permission;
}
