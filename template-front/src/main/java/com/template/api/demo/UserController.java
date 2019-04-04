package com.template.api.demo;

import com.template.api.AbstractController;
import com.template.api.common.ResponseMessage;
import com.template.api.poi.ChartMode;
import com.template.api.poi.WordUtil;
import com.template.api.redis.TestRedis;
import com.template.api.redis.User;
import com.template.orm.mapper.TuserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: qiudong
 * @date: 2018/7/23
 * @version: V1.0
 * @review: qiudong /2018/7/23
 */
@RestController
public class UserController extends AbstractController {
//    @Autowired
//    private TuserMapper tuserMapper;

    @Autowired
    private TestRedis redis;

    @RequestMapping("/")
    public User home(){
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


        List<ChartMode> chartModes = new ArrayList<>();
        String[] series = {"黑色", "白色", "红色", "紫色"};//modelReader.readLine().split(",");

        ChartMode chartMode = new ChartMode();
        chartMode.setLimit(3);
        chartMode.setCode("00001");
        chartMode.setChartTitle("生成图表");
        chartMode.setSeries(series);
        List<String> categorys = new ArrayList<>(10);
        categorys.add("1111");
        categorys.add("2222");
        categorys.add("3333");
        categorys.add("44444");
        categorys.add("55555");
        categorys.add("66666");
        categorys.add("77777");
        chartMode.setCategory(categorys);
        List<List<Double>> data = new ArrayList<>();
        List<Double> item = new ArrayList<>();
        item.add(Double.valueOf(12));
        item.add(Double.valueOf(13));
        item.add(Double.valueOf(33));
        item.add(Double.valueOf(15));
        item.add(Double.valueOf(21));
        item.add(Double.valueOf(17));
        item.add(Double.valueOf(18));
        data.add(item);
        List<Double> item1 = new ArrayList<>();
        item1.add(Double.valueOf(22));
        item1.add(Double.valueOf(34));
        item1.add(Double.valueOf(24));
        item1.add(Double.valueOf(22));
        item1.add(Double.valueOf(26));
        item1.add(Double.valueOf(27));
        item1.add(Double.valueOf(28));
        data.add(item1);

        List<Double> item2 = new ArrayList<>();
        item2.add(Double.valueOf(32));
        item2.add(Double.valueOf(12));
        item2.add(Double.valueOf(34));
        item2.add(Double.valueOf(35));
        item2.add(Double.valueOf(36));
        item2.add(Double.valueOf(23));
        item2.add(Double.valueOf(38));
        data.add(item2);

        chartMode.setData(data);



        ChartMode chartMode1 = new ChartMode();
        chartMode1.setLimit(3);
        chartMode1.setCode("00002");
        chartMode1.setChartTitle("生成图表");
        chartMode1.setSeries(series);
        List<String> categorys1 = new ArrayList<>(10);
        categorys1.add("aaaa");
        categorys1.add("ccccc");
        categorys1.add("vvvvv");
        categorys1.add("fffff");
        categorys1.add("bbb");
        categorys1.add("ttt");
        categorys1.add("llllll");
        chartMode1.setCategory(categorys);
        List<List<Double>> data1 = new ArrayList<>();
        List<Double> itema = new ArrayList<>();
        itema.add(Double.valueOf(12));
        itema.add(Double.valueOf(13));
        itema.add(Double.valueOf(33));
        itema.add(Double.valueOf(15));
        itema.add(Double.valueOf(21));
        itema.add(Double.valueOf(17));
        itema.add(Double.valueOf(18));
        data1.add(itema);
        List<Double> itemb = new ArrayList<>();
        itemb.add(Double.valueOf(22));
        itemb.add(Double.valueOf(34));
        itemb.add(Double.valueOf(24));
        itemb.add(Double.valueOf(22));
        itemb.add(Double.valueOf(26));
        itemb.add(Double.valueOf(27));
        itemb.add(Double.valueOf(28));
        data1.add(itemb);

        List<Double> itemc = new ArrayList<>();
        itemc.add(Double.valueOf(32));
        itemc.add(Double.valueOf(12));
        itemc.add(Double.valueOf(34));
        itemc.add(Double.valueOf(35));
        itemc.add(Double.valueOf(36));
        itemc.add(Double.valueOf(23));
        itemc.add(Double.valueOf(38));
        data1.add(itemc);


        chartMode1.setData(data1);
        chartModes.add(chartMode);

        chartModes.add(chartMode1);



//        /** 此Map存放动态 批量生产Word中表格的内容 **/
//        List<String[]> tableDataList = new ArrayList<String[]>();
//        tableDataList.add(new String[]{"应还款日", "应还本金", "应付利息", "应还（付）总额"});
//        tableDataList.add(new String[]{"2018-01-01", "23", "0.2", "111"});
//        tableDataList.add(new String[]{"2018-01-02", "233", "0.2", "123"});
//        tableDataList.add(new String[]{"2018-01-03", "2333", "0.2", "321"});
//        tableDataList.add(new String[]{"2018-01-04", "2333", "0.2", "321"});
//        tableDataList.add(new String[]{"2018-01-05", "2333", "0.2", "321"});
//        tableDataList.add(new String[]{"2018-01-06", "2333", "0.2", "321"});
//        tableDataList.add(new String[]{"2018-01-07", "2333", "0.2", "321"});

        String localTemplateTmpPath = "/Users/qiudong/myproject/template/template-front/src/main/resources/人物模板.doc";
        String newContractLocalPath = "/Users/qiudong/myproject/template/template-front/src/main/resources/1111.doc";

        WordUtil.changWord(localTemplateTmpPath, newContractLocalPath, map, chartModes);
//        return wrapperSupplier(() -> {
//            return tuserMapper.findAll();
//        });
//        redis.hsetTest();
//        redis.hsetTest();
        redis.setStr();
        return redis.hgetTest();
//        return "1212121";

    }

}
