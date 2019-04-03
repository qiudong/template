package com.template.api.poi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;


/**
 * @ClassName：WordDocDemo_POI
 * @Description：使用POI方式实现word文档导出功能 需要的jar包：poi-3.10.1.jar;poi-ooxml-3.10.1.jar;
 * poi-ooxml-schemas-3.10.1.jar;poi-scratchpad-3.10.1.jar
 * POI方式导出word文档，需要提前创建word导出模板
 * POI方式导出word时，本机安装的office版本是2007以前的版本，使用到的是HWPFDocument
 * POI方式导出word时，本机安装的office版本是2007+的版本，使用到的是XWPFDocument
 * @date：2017年5月12日 修改备注：
 */
public class WordDocDemo_POI {

    public static void main(String[] args) throws Exception {
        exportApplyForm();
    }

    /**
     * @Description:使用POI实现word导出功能，JSF页面请求，此处涉及到JSF框架中FacesContext对象，可摘除,
     * @date: 2017年5月12日 下午6:50:51
     * @修改备注:
     */
//    public void exportWord() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.responseComplete();
//        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
//        response.reset();
//        try {
//            String fileName = "关于" + "eventInfo.getEventName()" + "的情况报告.doc";
//            fileName = URLEncoder.encode(fileName, "UTF-8");
//            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
//            response.setContentType("application/msword");
//            ServletOutputStream outputStream = response.getOutputStream();
//            //引入word模板，转化成流
//            //String wordTemp = "E:\\项目\\导出报告模板\\reportTemplate.doc";
//            String wordTemp = request.getSession().getServletContext().getRealPath("eventInfo.export.reportTemplate");
//            FileInputStream inputStream = new FileInputStream(wordTemp);
//            HWPFDocument document = new HWPFDocument(inputStream);
//            //替换内容
//            Range range = document.getRange();
//            Map<String, String> content = this.getReplaceContent();
//            for (java.util.Map.Entry<String, String> entry : content.entrySet()) {
//                range.replaceText(entry.getKey(), entry.getValue());
//            }
//            document.write(outputStream);
//            inputStream.close();
//            outputStream.flush();
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        FacesContext.getCurrentInstance().responseComplete();
//    }

    private Map<String, String> getReplaceContent() {
        Map<String, String> content = new HashMap<String, String>();
        content.put("${eventType}", "typeName类型名称");
        content.put("${reportName}", "report.getReportName报告名称");
        content.put("${reporterUnit}", "report.getReporterUnit报送单位");
        content.put("${loginUser}", "loginUser登录人员信息");
        content.put("${nowTime}", "yyyy年MM月dd日时间消息");
        content.put("${occurTime}", "yyyy年MM月dd日事发时间");
        content.put("${disaster}", "非必填写1");
        content.put("${eventDesc}", "非必填写2");
        content.put("${eventDesc}", "非必填写3");
        return content;
    }


    public  static void exportApplyForm() throws Exception {
        XWPFDocument doComment=new XWPFDocument();
        //创建段落
        XWPFParagraph gParagraph=doComment.createParagraph();
        //设置文本的对齐方式
        gParagraph.setAlignment(ParagraphAlignment.CENTER);
        //创建文本对象
        XWPFRun frun=gParagraph.createRun();
        frun.setBold(true);//是否加粗
        frun.setColor("1AA160");//设置颜色
        frun.setFontSize(30);//设置字体大小
        frun.setText("我正在看Word文档的生成");

        //创建表格
        //参数说明：1、行数2、列数
        XWPFTable table=doComment.createTable(10,1);
        //设置表格属性
        CTTbl ttbl=table.getCTTbl();
        CTTblPr tp=ttbl.addNewTblPr();
        //设置表格宽度
        CTTblWidth cw=tp.addNewTblW();
        cw.setW(BigInteger.valueOf(8000));
        cw.setType(STTblWidth.DXA);//设置为固定。默认为AUTO

        XWPFTableRow tr1=table.getRow(0);
        XWPFTableCell tc1=tr1.getCell(0);
        tc1.setColor("4D8BF4");
        tc1.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);//竖直对齐方式
        //表格的列中嵌套段落
        XWPFParagraph gp=tc1.addParagraph();
        XWPFRun fr1=gp.createRun();
        fr1.setBold(true);
        fr1.setDoubleStrikethrough(true);
        fr1.setFontSize(25);
        fr1.setColor("D14825");
        fr1.setText("名次");
//      CTTc ct1=tc1.getCTTc();
//      CTTcPr cp1=ct1.addNewTcPr();
//      cp1.addNewVMerge().setVal(STMerge.CONTINUE);//合并表格的列
        for(int i=1;i<table.getRows().size();i++){
            XWPFTableCell c=table.getRow(i).getCell(0);
            c.setText("第"+i+"名");
        }

            //写出，生成word文件
            doComment.write(new FileOutputStream("first.doc"));
            doComment.close();
        System.err.println("OK");
    }
}