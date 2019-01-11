package com.yutons.shiro.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Author: yutons
 * @Description: 时间转换器
 * @Created: 2017/10/12 12:13
 */
public class JsonDateTimeValueProcessor implements JsonValueProcessor {
    private String format ="yyyy-MM-dd HH:mm:ss";

    public JsonDateTimeValueProcessor() {
        super();
    }

    public JsonDateTimeValueProcessor(String format) {
        super();
        this.format = format;
    }

    @Override
    public Object processArrayValue(Object paramObject,
                                    JsonConfig paramJsonConfig) {
        return process(paramObject);
    }

    @Override
    public Object processObjectValue(String paramString, Object paramObject,
                                     JsonConfig paramJsonConfig) {
        return process(paramObject);
    }


    private Object process(Object value){
        if(value instanceof Date){
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
            return sdf.format(value);
        }
        return value == null ? "" : value.toString();
    }

}
