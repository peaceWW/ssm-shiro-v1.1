package com.yutons.shiro.util;

import com.yutons.shiro.bean.admin.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yutons
 * @desc
 * @date 2017/10/26 11:36
 */
public class MenuUtil {
    public static List<List<Permission>> getMenus(List<Permission> resources) {
        //系统菜单列表
        List<Permission> adminList = new ArrayList<>();
        //监控菜单列表
        List<Permission> monitorList = new ArrayList<>();
        //预警菜单列表
        List<Permission> warningList = new ArrayList<>();
        //分析菜单列表
        List<Permission> analysisList = new ArrayList<>();
        //决策菜单列表
        List<Permission> decidingList = new ArrayList<>();
        //菜单列表集合
        List<List<Permission>> list = new ArrayList<>();
        Permission menu = new Permission();
        for (Permission resource : resources) {
            if (resource.getParantid() == 0) {
                menu.setMenuname(resource.getMenuname());
                menu.setId(resource.getId());
                menu.setUrl(resource.getUrl());
                menu.setZindex(resource.getZindex());
                menu.setParantid(resource.getParantid());
                menu.setChildMenu(getMenus(resource.getId(), resources));
                if (menu.getId() == 1000) {
                    monitorList.add(menu);
                } else if (menu.getId() == 2000) {
                    warningList.add(menu);
                } else if (menu.getId() == 3000) {
                    analysisList.add(menu);
                } else if (menu.getId() == 4000) {
                    decidingList.add(menu);
                } else if (menu.getId() == 5000) {
                    adminList.add(menu);
                }
            }
            menu = new Permission();
        }
        list.add(monitorList);
        list.add(warningList);
        list.add(analysisList);
        list.add(decidingList);
        list.add(adminList);
        return list;
    }

    public static List<Permission> getMenus(Integer id, List<Permission> resources) {
        List<Permission> lists = new ArrayList<>();
        for (Permission resource : resources) {
            Permission menu = new Permission();
            Integer parantid = resource.getParantid();
            if (id.equals(parantid)) {
                menu.setMenuname(resource.getMenuname());
                menu.setId(resource.getId());
                menu.setUrl(resource.getUrl());
                menu.setZindex(resource.getZindex());
                menu.setParantid(resource.getParantid());
                menu.setChildMenu(getMenus(resource.getId(), resources));
                lists.add(menu);
            }
        }
        return lists;
    }
}
