package com.template.api.poi;

import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: java类作用描述
 * @Author: qiudong
 * @CreateDate: 2019-04-03 13:18
 * @UpdateUser: qiudong
 * @UpdateDate: 2019-04-03 13:18
 * @Version: V1.0
 */
public class WordUtil {

    /**
     * 根据模板生成新word文档
     * 判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入
     *
     * @param inputUrl  模板存放地址
     * @param outputUrl 新文档存放地址
     * @param textMap   需要替换的信息集合
     * @param tableList 需要插入的表格信息集合
     * @return 成功返回true, 失败返回false
     */
    public static boolean changWord(String inputUrl, String outputUrl, Map<String, String> textMap, List<String[]> tableList) {

        // 模板转换默认成功
        boolean changeFlag = true;
        try {
            // 获取docx解析对象
            XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(inputUrl));
            // 解析替换文本段落对象
            WordUtil.changeText(document, textMap);
//            // 解析替换表格对象
//            WordUtil.changeTable2(document, textMap, tableList);

            // 生成新的word
            File file = new File(outputUrl);
            FileOutputStream stream = new FileOutputStream(file);
            document.write(stream);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
            changeFlag = false;
        }
        return changeFlag;
    }

    /**
     * 替换段落文本
     *
     * @param document docx解析对象
     * @param textMap  需要替换的信息集合
     */
    public static void changeText(XWPFDocument document, Map<String, String> textMap) {
        // 获取段落集合
        List<XWPFParagraph> paragraphs = document.getParagraphs();

        Set<String> keySet = textMap.keySet();
        changeParagraph(textMap, paragraphs, keySet);
        // 获取表格对象集合
        List<XWPFTable> tables = document.getTables();
        for (XWPFTable table : tables) {
            List<XWPFParagraph> paras;
            int rcount = table.getNumberOfRows();
            for (int j = 0; j < rcount; j++) {
                XWPFTableRow row = table.getRow(j);
                List<XWPFTableCell> cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    paras = cell.getParagraphs();
                    changeParagraph(textMap, paras, keySet);
                }
            }
        }
    }

    private static void changeParagraph(Map<String, String> textMap, List<XWPFParagraph> paragraphs, Set<String> keySet) {
        reBuildParagraph2(textMap, paragraphs, keySet);
    }

    private static void reBuildParagraph2(Map<String, String> textMap, List<XWPFParagraph> paragraphs, Set<String> keySet) {
        for (XWPFParagraph paragraph : paragraphs) {

            /**
             * 此处For循环的思路:
             * 将原来的XWPFParagraph中的多个XWPFRun转化成一个XWPFRun,如此做的好处是不会改变原Word的格式
             * 如果直接通过XWPFParagraph获取文本内容，再进行赋值，会改变原有的Word的格式
             */
            String paragraphText = paragraph.getText();
            for (String key : keySet) {
                String regex = "\\$\\{" + key + "\\}";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(paragraphText);
                while (matcher.find()) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    StringBuffer sb = new StringBuffer("");
                    for (XWPFRun run : runs) {
                        int testPosition = run.getTextPosition();
                        String text = run.getText(testPosition);
                        if (StringUtils.isEmpty(text)) {
                            sb.append(text);
                        }
                    }
                    String newText = sb.toString();
                    int runLength = runs.size();
                    for (int i = runLength - 1; i >= 0; i--) {
                        if (i == 0) {
                            runs.get(i).setText(newText, 0);
                        } else {
                            paragraph.removeRun(i);
                            if (runLength > 1) {
                                runLength = runLength - 1;
                            }
                        }
                    }
                }
            }

            /**
             * 此处的思路:
             * 转换后的XWPFParagraph只会存在一个XWPFRun,并保留了原Word的格式
             * 将XWPFRun按照正则进行截取成多个XWPFRun;(具体实现是，将原有的XWPFRun拆分成多个，删除原有的XWPFRun，再将拆分后的XWPFRun创建到原XWPFParagraph)
             * 如此做的目的是：对Word中定义的变量(例如：${abc})进行格式操作，比如添加下划线
             */
            List<XWPFRun> runs = paragraph.getRuns();
            List<String> list = new ArrayList<>();
            if (runs.size() > 0) {
                XWPFRun run = runs.get(0);
                int testPosition = run.getTextPosition();
                String text = run.getText(testPosition);
                String regex2 = "\\$\\{\\w*\\}";
                String[] strs = text.split(regex2);
                String totalStr = "";
                if (strs.length == 1) {
                    if (text.contains("${")) {
                        list.add(strs[0]);
                        String regexStr = text.substring(strs[0].length());
                        list.add(regexStr);
                        paragraph.removeRun(0);
                    }
                } else {
                    if (strs.length > 1) {
                        for (int i = 0; i < strs.length; i++) {
                            String splitStr = strs[i];
                            totalStr = totalStr + splitStr;
                            String regexStr = "";
                            list.add(strs[i]);
                            if (i + 1 < strs.length) {
                                regexStr = text.substring(totalStr.length(), text.indexOf(strs[i + 1], totalStr.length()));
                            }
                            list.add(regexStr);
                            totalStr = totalStr + regexStr;
                        }
                        paragraph.removeRun(0);
                    }
                }

                for (int i = 0; i < list.size(); i++) {
                    XWPFRun newRun = paragraph.createRun();
                    newRun.setText(list.get(i));
                }
                /**
                 * 此处的思路:
                 * 获取转换后的XWPFParagraph上的多个XWPFRun
                 * 将XWPFRun按照正则进行文本内容替换(动态替换)
                 */
                List<XWPFRun> newRuns = paragraph.getRuns();
                for (XWPFRun newRun : newRuns) {
                    int position = newRun.getTextPosition();
                    String newText = newRun.getText(position);
                    for (String key : keySet) {
                        String regex = "\\$\\{" + key + "\\}";
                        Pattern pattern = Pattern.compile(regex);
                        if (null != pattern && null != newText && !"".equals(newText)) {
                            Matcher matcher = pattern.matcher(newText);
                            while (matcher.find()) {
                                String replaceText = newText.replaceAll(regex, textMap.get(key));
                                replaceText = "  " + replaceText + "  ";
                                newRun.setText(replaceText, 0);
                                newRun.setUnderline(UnderlinePatterns.SINGLE);
                            }
                        }
                    }
                }
            }

        }
    }

    public static void changeChart(XWPFDocument document){
        List<XWPFChart> charts = document.getCharts();
        XWPFChart chart = charts.get(0);

        final List<XDDFChartData> series = chart.getChartSeries();
        final XDDFBarChartData bar = (XDDFBarChartData) series.get(0);
        final XDDFChartData.Series series1 = bar.getSeries().get(0);
        final int numOfPoints = categories.length;

        final String categoryDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, 0, 0));

        final XDDFDataSource<?> categoriesData = XDDFDataSourcesFactory.fromArray(categories, categoryDataRange,0);
        final XDDFNumericalDataSource<? extends Number> valuesData2 = XDDFDataSourcesFactory.fromArray(values, valuesDataRange2,2);

        series1.replaceData(categoriesData, valuesData2);
    }

    /**
     * 替换表格对象方法
     *
     * @param document  docx解析对象
     * @param textMap   需要替换的信息集合
     * @param tableList 需要插入的表格信息集合
     */
    public static void changeTable2(XWPFDocument document, Map<String, String> textMap, List<String[]> tableList) {
        // 获取表格对象集合
        List<XWPFTable> tables = document.getTables();
        for (int i = 0; i < tables.size(); i++) {
            // 只处理行数大于等于2的表格，且不循环表头
            XWPFTable table = tables.get(i);

            // 表头
            String[] tableHeads = tableList.get(0);
            String tableHead = "";
            for (int a = 0; a < tableHeads.length; a++) {
                tableHead = tableHead + tableHeads[a];
            }
            String templateTableHead = table.getText().replaceAll("\t", "").replaceAll("\n", "");
            System.out.println(templateTableHead.contains(tableHead));
            if (templateTableHead.contains(tableHead)) {
                // 创建20行7列
                int rows = tableList.size();
                int cols = tableList.get(0).length;

                for (int l = 1; l < rows; l++) {
                    XWPFTableRow targetRow = table.insertNewTableRow(l + 1);
                    targetRow.getCtRow().setTrPr(table.getRow(l).getCtRow().getTrPr());
                    List<XWPFTableCell> cellList = table.getRow(l).getTableCells();
                    XWPFTableCell targetCell = null;
                    for (XWPFTableCell sourceCell : cellList) {
                        targetCell = targetRow.addNewTableCell();
                        targetCell.getCTTc().setTcPr(sourceCell.getCTTc().getTcPr());
                        targetCell.getParagraphs().get(0).getCTP().setPPr(sourceCell.getParagraphs().get(0).getCTP().getPPr());
                    }
                    XWPFTableRow row = table.getRow(l);
                    for (int j = 0; j < cols; j++) {
                        // 设置单元格内容
                        row.getCell(j).setText(tableList.get(l)[j]);
                    }
                }
            }
        }
    }


    public static void main(String[] args) {

        /** 此Map存放动态替换的内容，key-Word中定义的变量,value-要替换的内容 **/
        Map<String, String> map = new HashMap<>();
        map.put("name", "55555556444444888544");
        map.put("age", "1111");
        map.put("LOAN_memberName", "小赵");
        map.put("LOAN_certNo", "1234568524545");
        map.put("LOAN_address", "重庆市沙坪坝积极");
        map.put("LOAN_mobileNo", "110110110");
        map.put("LOAN_memberName", "张三");
        map.put("LOAN_certNo", "510254545488445");
        map.put("LOAN_address", "四川省锦州达到");
        map.put("LOAN_mobileNo", "023-5854555");
        map.put("GUARANTEE_guaPersonName", "李四");

        /** 此Map存放动态 批量生产Word中表格的内容 **/
        List<String[]> tableDataList = new ArrayList<String[]>();
        tableDataList.add(new String[]{"应还款日", "应还本金", "应付利息", "应还（付）总额"});
        tableDataList.add(new String[]{"2018-01-01", "23", "0.2", "111"});
        tableDataList.add(new String[]{"2018-01-02", "233", "0.2", "123"});
        tableDataList.add(new String[]{"2018-01-03", "2333", "0.2", "321"});
        tableDataList.add(new String[]{"2018-01-04", "2333", "0.2", "321"});
        tableDataList.add(new String[]{"2018-01-05", "2333", "0.2", "321"});
        tableDataList.add(new String[]{"2018-01-06", "2333", "0.2", "321"});
        tableDataList.add(new String[]{"2018-01-07", "2333", "0.2", "321"});

        String localTemplateTmpPath = "/Users/qiudong/myproject/template/template-front/src/main/resources/人物模板.doc";
        String newContractLocalPath = "/Users/qiudong/myproject/template/template-front/src/main/resources/1111.doc";

        WordUtil.changWord(localTemplateTmpPath, newContractLocalPath, map, tableDataList);

    }
}
