package com.yutons.shiro.service.admin;

import com.yutons.shiro.bean.admin.Permission;
import com.yutons.shiro.bean.admin.PermissionPage;
import com.yutons.shiro.dao.admin.PermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yutons
 * @desc 权限&菜单业务层
 * @date 2017/10/26 10:54
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    /**
     * 获取所有权限
     *
     * @return
     */
    @Override
    public Permission selectPermission() {
        List<Permission> permissions = permissionDao.selectPermission();
        List<Permission> permissions0 = getFirstPermissions(permissions);
        Permission permission = new Permission();
        permission.setChildMenu(permissions0);
        return permission;
    }

    /**
     * 根据条件获取权限列表
     *
     * @param permission
     * @return
     */
    @Override
    public PermissionPage selectPermissionPage(Permission permission) {
        List<Permission> permissions = permissionDao.selectPermissionsByCondition(permission);
        Integer count = permissionDao.selectPermissionsCountByCondition(permission);
        PermissionPage permissionPage = new PermissionPage();
        permissionPage.setCount(count);
        permissionPage.setData(permissions);
        return permissionPage;
    }

    /**
     * 添加权限&菜单
     *
     * @param permission
     * @return
     */
    @Override
    public Integer add(Permission permission) {
        Integer flag = permission.getFlag();
        if (flag == null) {
            permission.setFlag(0);
        }
        Integer i = null;
        try {
            i = permissionDao.add(permission);
        } catch (Exception e) {
            return 0;
        }
        return i;
    }


    /**
     * 根据条件获取权限列表
     *
     * @param permission
     * @return
     */
    @Override
    public PermissionPage selectMenuPage(Permission permission) {
        List<Permission> permissions = permissionDao.selectMenusByCondition(permission);
        Integer count = permissionDao.selectMenusCountByCondition(permission);
        PermissionPage permissionPage = new PermissionPage();
        permissionPage.setCount(count);
        permissionPage.setData(permissions);
        return permissionPage;
    }

    /**
     * 获取未生成菜单的选项,排除'系统管理'和'系统资源'
     *
     * @return
     */
    @Override
    public List<Permission> selectNoSetMenus() {
        return permissionDao.selectNoSetMenus();
    }

    /**
     * 获取所有父级菜单
     *
     * @return
     */
    @Override
    public List<Permission> selectAllPermissions() {
        List<Permission> allPermission = permissionDao.selectAllPermissions();
        for (int i = 0; i < allPermission.size(); i++) {
            if (allPermission.get(i).getParantid() != 0) {
                //一级菜单
                Permission permission = allPermission.get(i);
                permission.setMenuname("|---" + permission.getMenuname());
            }
        }
        return allPermission;
    }

    /**
     * 菜单的添加,修改
     *
     * @param permission
     * @return
     */
    @Override
    public Integer updatePermissionById(Permission permission) {
        return permissionDao.updatePermissionById(permission);
    }

    /**
     * 根据id获取权限数据
     *
     * @param permissionId
     * @return
     */
    @Override
    public Permission selectPermissionById(Integer permissionId) {
        return permissionDao.selectPermissionById(permissionId);
    }

    /**
     * 根据id修改菜单启用状态
     *
     * @param permission
     * @return
     */
    @Override
    public Integer updateFlagById(Permission permission) {
        return permissionDao.updateFlagById(permission);
    }

    /**
     * 根据id删除权限
     *
     * @param permission
     * @return
     */
    @Override
    public Integer deletePermissionById(Permission permission) {
        return permissionDao.deletePermissionById(permission);
    }

    /**
     * 更新角色,删除授权并重新进行授权
     *
     * @param role
     * @param model
     * @return
     */
    @Override
    public Integer update(Permission permission) {
        return permissionDao.update(permission);
    }

    /**
     * 删除菜单
     *
     * @param permission
     * @return
     */
    @Override
    public Integer deleteMenuById(Permission permission) {
        return permissionDao.updateMenuById(permission);
    }

    /**
     * 获取顶级菜单
     *
     * @param permissions
     * @return
     */
    private List<Permission> getFirstPermissions(List<Permission> permissions) {
        List<Permission> permissions1 = new ArrayList<>();
        //顶级权限
        for (Permission permission : permissions) {
            //设置顶级权限
            if (permission.getParantid() == null || "".equals(permission.getPermission()) || permission.getParantid() == 0) {
                List<Permission> permissions2 = getPermissions(permissions, permission);
                permission.setChildMenu(permissions2);
                permissions1.add(permission);
                //System.out.println("顶级权限" + permissions1);
            }
        }
        return permissions1;
    }

    /**
     * 递归调用菜单
     *
     * @param permissions
     * @param permission
     * @return
     */
    private List<Permission> getPermissions(List<Permission> permissions, Permission permission) {
        List<Permission> permissions2 = new ArrayList<>();
        //一级权限
        for (Permission permission2 : permissions) {
            if (permission.getId().equals(permission2.getParantid())) {
                permissions2.add(permission2);
                List<Permission> permissions3 = getPermissions(permissions, permission2);
                permission2.setChildMenu(permissions3);
                //System.out.println("权限" + permissions2);
            }
        }
        return permissions2;
    }
}
