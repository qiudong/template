package com.template.api.poi;

import lombok.Data;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFLineChartData;
import org.apache.poi.xwpf.usermodel.XWPFChart;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: qiudong
 * @CreateDate: 2019-04-04 10:54
 * @UpdateUser: qiudong
 * @UpdateDate: 2019-04-04 10:54
 * @Version: V1.0
 */
@Data
public class Chart {

    private int colLimit;

    private String code;

    private XWPFChart chart;

    private List<Double[]> params;

    private XDDFDataSource<?> categoriesData;

    private XDDFChartData bar;

    private int numOfPoints;

    private String[] series;

    private String chartTitle;


}
