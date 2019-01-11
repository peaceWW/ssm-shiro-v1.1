package com.yutons.shiro.bean.admin;

import com.yutons.shiro.bean.PageBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.io.Serializable;
/**
 * @author yutons
 * @desc shiro权限控制之部门实体类
 * @date 2017/10/25 15:56
 */
@Data
@Log4j
@NoArgsConstructor
@AllArgsConstructor
public class Dept extends PageBean implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 部门ID
     */
    protected Integer id;
    /**
     * 部门代码
     */
    protected String code;
    /**
     * 部门名称
     */
    protected String name;
    /**
     * 部门父级ID
     */
    protected Integer parantid;
}
