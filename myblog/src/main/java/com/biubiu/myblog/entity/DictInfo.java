package com.biubiu.myblog.entity;

import lombok.Data;

/**
 * 数据字典和配置表
 *
 * @author wbbaijq
 */
@Data
public class DictInfo {

    /** id **/
    private Integer id;
    /** 名称 **/
    private String name;
    /** 值 **/
    private String value;
    /** 编号**/
    private String code;
    /** 详细信息 **/
    private String info;

}
