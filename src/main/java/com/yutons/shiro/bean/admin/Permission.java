package com.yutons.shiro.bean.admin;


import com.yutons.shiro.bean.PageBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.io.Serializable;
import java.util.List;

/**
 * @author yutons
 * @desc shiro权限控制之权限实体类
 * @date 2017/10/25 15:56
 */
@Data
@Log4j
@NoArgsConstructor
@AllArgsConstructor
public class Permission extends PageBean implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer id;
    /**
     * 权限ID
     */
    protected Integer permissionId;
    /**
     * 权限名
     */
    protected String name;
    /**
     * 权限生成的菜单名
     */
    protected String menuname;
    /**
     * 资源权限字符串
     */
    protected String permission;
    /**
     * 资源访问路径
     */
    protected String url;
    /**
     * 是否生成菜单,0:默认不生成菜单,1:生成菜单
     */
    protected Integer flag;
    /**
     * 菜单排序
     */
    protected Integer zindex;
    /**
     * 父级菜单id
     */
    protected Integer parantid;
    /**
     * 子菜单list
     */
    protected List childMenu;
}
