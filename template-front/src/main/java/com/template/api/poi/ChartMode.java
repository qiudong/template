package com.template.api.poi;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Description: 图表数据类
 * @Author: qiudong
 * @CreateDate: 2019-04-03 17:13
 * @UpdateUser: qiudong
 * @UpdateDate: 2019-04-03 17:13
 * @Version: V1.0
 */
@Data
public class ChartMode implements Serializable {

    private String code;
    private int limit;

    private String chartTitle;

    private List<String> category; // 类别

    private String[] series; //

    private List<List<Double>> data;

}
