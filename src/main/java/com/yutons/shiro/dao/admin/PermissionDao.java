package com.yutons.shiro.dao.admin;

import com.yutons.shiro.bean.admin.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yutons
 * @desc
 * @date 2017/10/27 15:49
 */
public interface PermissionDao {
    /**
     * 获取所有权限
     *
     * @return
     */
    List<Permission> selectPermission();

    /**
     * 根据条件获取权限列表
     *
     * @param permission
     * @return
     */
    List<Permission> selectPermissionsByCondition(@Param("permission") Permission permission);

    /**
     * 根据条件获取权限总数
     *
     * @param permission
     * @return
     */
    Integer selectPermissionsCountByCondition(@Param("permission") Permission permission);

    /**
     * 添加权限&菜单
     *
     * @param permission
     * @return
     */
    Integer add(@Param("permission") Permission permission);

    /**
     * 获取菜单列表
     *
     * @param permission
     * @return
     */
    List<Permission> selectMenusByCondition(@Param("permission") Permission permission);

    /**
     * 获取菜单总数
     *
     * @param permission
     * @return
     */
    Integer selectMenusCountByCondition(@Param("permission") Permission permission);

    /**
     * 获取未生成菜单的选项,排除'系统管理'和'系统资源'
     *
     * @return
     */
    List<Permission> selectNoSetMenus();

    /**
     * 获取所有父级菜单
     *
     * @return
     */
    List<Permission> selectAllPermissions();

    /**
     * 菜单添加(权限数据修改)
     *
     * @param permission
     * @return
     */
    Integer updatePermissionById(@Param("permission") Permission permission);

    /**
     * 获取最大排序
     *
     * @param permission
     * @return
     */
    Integer selectMaxZindexByParantid(@Param("permission") Permission permission);

    /**
     * 根据id获取权限数据
     *
     * @param permissionId
     * @return
     */
    Permission selectPermissionById(@Param("permissionId") Integer permissionId);

    /**
     * 根据id修改菜单启用状态
     *
     * @param permission
     * @return
     */
    Integer updateFlagById(@Param("permission") Permission permission);

    /**
     * 根据id删除权限
     *
     * @param permission
     * @return
     */
    Integer deletePermissionById(@Param("permission") Permission permission);

    /**
     * 更新角色,删除授权并重新进行授权
     *
     * @param role
     * @param model
     * @return
     */
    Integer update(@Param("permission") Permission permission);
    /**
     * 删除菜单
     *
     * @param permission
     * @return
     */
    Integer updateMenuById(@Param("permission") Permission permission);
}
