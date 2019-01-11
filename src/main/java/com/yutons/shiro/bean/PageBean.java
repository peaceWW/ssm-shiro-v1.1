package com.yutons.shiro.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Created by yutons on 2017/9/1
 */
@Data
@Log4j
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
    protected Integer page;//页码
    protected Integer limit;//当页总条数
}
