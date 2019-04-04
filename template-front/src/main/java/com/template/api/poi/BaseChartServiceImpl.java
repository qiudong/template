package com.template.api.poi;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xwpf.usermodel.XWPFChart;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 标准柱状图实现类
 * @Author: qiudong
 * @CreateDate: 2019-04-04 10:52
 * @UpdateUser: qiudong
 * @UpdateDate: 2019-04-04 10:52
 * @Version: V1.0
 */
@Component
public class BaseChartServiceImpl implements ChartService {

    @Override
    public void getChart(Chart chartData) {
        final List<Double[]> params = chartData.getParams();
        final int numOfPoints = chartData.getNumOfPoints();
        final XDDFChartData bar = chartData.getBar();
        final String[] series = chartData.getSeries();
        final XDDFDataSource<?> categoriesData = chartData.getCategoriesData();
        final XWPFChart chart = chartData.getChart();
        int[] count = {0};
        params.stream().forEach(value -> {
            int col = count[0];
            final String valuesDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, col + 1, col + 1));
            final XDDFNumericalDataSource<? extends Number> valuesData = XDDFDataSourcesFactory.fromArray(value, valuesDataRange, col + 1);

            if (col < chartData.getColLimit()) { /** 模板类别必须固定 */
                XDDFChartData.Series seriesData = bar.getSeries().get(col);
                seriesData.replaceData(categoriesData, valuesData);
                seriesData.setTitle(series[col], chart.setSheetTitle(series[col], col));
            } else {
                XDDFChartData.Series seriesData = bar.addSeries(categoriesData, valuesData);
                seriesData.setTitle(series[col], chart.setSheetTitle(series[col], col));
            }

            count[0]++;
        });

    }
}
