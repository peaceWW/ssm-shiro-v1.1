package com.yutons.shiro.bean.admin;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * @author yutons
 * @desc shiro权限控制之用户角色关联实体类
 * @date 2017/10/25 15:56
 */
@Data
@Log4j
@NoArgsConstructor
@AllArgsConstructor
public class UserAndRole {
    /**
     * 角色ID
     */
    protected Integer userId;
    /**
     * 权限ID
     */
    protected Integer roleId;
}
