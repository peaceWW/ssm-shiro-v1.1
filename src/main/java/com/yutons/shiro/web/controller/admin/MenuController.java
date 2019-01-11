package com.yutons.shiro.web.controller.admin;

import com.yutons.shiro.bean.admin.Permission;
import com.yutons.shiro.bean.admin.PermissionPage;
import com.yutons.shiro.service.admin.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yutons
 * @desc
 * @date 2017/10/30 9:59
 */
@Controller
@RequestMapping(value = "/admin/menu")
public class MenuController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 跳转到菜单列表
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(String msg, Model model) {
        if (msg != null) {
            /*if ("success".equals(msg)){
                model.addAttribute("msg", "菜单添加成功!");
            }*/
            if ("error1".equals(msg)){
                model.addAttribute("msg", "当前菜单不可禁用!");
            }
        }
        return "admin/menu/list";
    }

    /**
     * 根据条件获取菜单列表
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PermissionPage list(Permission permission) {
        return permissionService.selectMenuPage(permission);
    }

    /**
     * 跳转到菜单添加页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addIndex(Model model) {
        List<Permission> menus = permissionService.selectNoSetMenus();
        List<Permission> parantMenus = permissionService.selectAllPermissions();
        model.addAttribute("menus", menus);
        model.addAttribute("parantMenus", parantMenus);
        return "admin/menu/add";
    }

    /**
     * 菜单添加
     *
     * @param permission
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Permission permission, Model model) {
        Integer update = permissionService.updatePermissionById(permission);
        if (update == 0) {
            //添加失败,重新添加
            model.addAttribute("permission", permission);
            model.addAttribute("msg", "菜单添加失败,请重新添加!");
            return "/admin/menu/add";
        } else {
            return "redirect:/admin/menu/index?msg=success";
        }
    }

    /**
     * 跳转到角色修改页面
     *
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{permissionId}", method = RequestMethod.GET)
    public String updateInex(@PathVariable("permissionId") Integer permissionId, Model model) {
        Permission permission = permissionService.selectPermissionById(permissionId);
        List<Permission> menus = permissionService.selectNoSetMenus();
        List<Permission> parantMenus = permissionService.selectAllPermissions();
        model.addAttribute("menus", menus);
        model.addAttribute("parantMenus", parantMenus);
        model.addAttribute("permission", permission);
        return "admin/menu/update";
    }

    /**
     * 删除菜单
     *
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteMenuById", method = RequestMethod.POST)
    public String deleteMenuById(Permission permission) {
        Integer i = permissionService.deleteMenuById(permission);
        if (i == 1) {
            return "success";
        } else {
            return "error";
        }
    }

    /**
     * 设置菜单启用状态
     *
     * @param permission
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateFlag", method = RequestMethod.POST)
    public String updateFlag(Permission permission) {
        List permissionIds = new ArrayList();
        permissionIds.add(5000);
        permissionIds.add(5100);
        permissionIds.add(5110);
        permissionIds.add(5120);
        permissionIds.add(5130);
        permissionIds.add(5200);
        permissionIds.add(5210);
        permissionIds.add(5300);
        permissionIds.add(5310);
        permissionIds.add(5320);
        permissionIds.add(5330);
        Integer i = 0;
        if (!permissionIds.contains(permission.getId())) {
            i = permissionService.updateFlagById(permission);
        }
        if (i == 1) {
            return "success";
        } else if(i==0) {
            return "error1";
        }else {
            return "error";
        }
    }
}
