package com.template.api.poi;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: qiudong
 * @CreateDate: 2019-04-03 17:13
 * @UpdateUser: qiudong
 * @UpdateDate: 2019-04-03 17:13
 * @Version: V1.0
 */
public class ChartBean implements Serializable {

    private String chartTitle;
    private List<String> category; // 类别

    private String[] series; //

    private List<List<Double>> data;

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public String[] getSeries() {
        return series;
    }

    public void setSeries(String[] series) {
        this.series = series;
    }

    public List<List<Double>> getData() {
        return data;
    }

    public void setData(List<List<Double>> data) {
        this.data = data;
    }

    public String getChartTitle() {
        return chartTitle;
    }

    public void setChartTitle(String chartTitle) {
        this.chartTitle = chartTitle;
    }
}
