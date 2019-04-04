package com.template.api.poi;

/**
 * @Description: 图表枚举类
 * @Author: qiudong
 * @CreateDate: 2019-04-04 11:35
 * @UpdateUser: qiudong
 * @UpdateDate: 2019-04-04 11:35
 * @Version: V1.0
 */
public enum ChartEnum {

    HISTOGRAM_CHART("00001", "baseChartServiceImpl", 1),
    LINE_CHART("00002", "baseChartServiceImpl", 2),
    ;

    private String code;
    private String beanName;
    private Integer type;

    ChartEnum(String code, String beanName, Integer type) {
        this.code = code;
        this.beanName = beanName;
        this.type = type;
    }

    public static ChartEnum getEnumByCode(String code) {
        for (ChartEnum chartEnum : ChartEnum.values()) {
            if (code.equals(chartEnum.getCode())) {
                return chartEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }}
