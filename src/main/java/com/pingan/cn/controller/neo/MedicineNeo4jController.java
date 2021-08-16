package com.pingan.cn.controller.neo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pingan.cn.common.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.ss.formula.functions.T;
import org.neo4j.driver.v1.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import sun.java2d.pipe.AAShapePipe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(tags = "medicineNeo4j")
@RestController
@RequestMapping(value = "/api/neo/medicine")
public class MedicineNeo4jController {

    private static String URI = "bolt://localhost:7687";
    private static String USERNAME = "neo4j";
    private static String PASSWORD = "123";
    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    /**
     * 1.限定就医方式
     * 输入：医保商品名编码、就医方式、就诊日期（就诊日期在项目开始时间和结束范围内）
     * 不在时间范围内，提示
     * 输出：有无限制
     * 根据【医保商品名编码】和【就医方式】搜索【限制】结果
     */
    @ApiOperation(value="限定就医方式",tags={"限定就医方式"},notes="eg: yka266 = ZLC330604009 ， doctorway = 普通人员门诊 ，jiuzhenriqi = 20090102")
    @GetMapping(value = "/xiandingjiuyifangshi/{yka266}/{doctorway}/{jiuzhenriqi}/{jiuyifangshi}")
    public ResponseUtil xiandingjiuyifangshi(@ApiParam(defaultValue = "ZLC330604009")@PathVariable String yka266, @ApiParam(defaultValue = "普通人员门诊")@PathVariable String doctorway, @ApiParam(defaultValue = "20090102") @PathVariable String jiuzhenriqi,@PathVariable String jiuyifangshi) throws ParseException {
        Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USERNAME, PASSWORD ) );
        Session session = driver.session();

        StatementResult result = session.run( "MATCH (s:AllData)-[:Link]->(t:Xmsm) where s.yka266='"+yka266+"' and t.doctorway='"+doctorway+"' return s,t");
        JSONArray jsonArray = new JSONArray();
        String flag = "不在时间范围内";
        while ( result.hasNext() )
        {
//            JSONObject jsonObject = new JSONObject();
            Record record = result.next();
//            jsonArray.add(record.get("t").asMap());

            Map maps = record.get("t").asMap();

            String starttime = (String) maps.get("starttime");
            String endtime = (String) maps.get("endtime");
            Date jiuzhenDate = format.parse(jiuzhenriqi);
            if(starttime != null && endtime !=null){
                Date startDate = format.parse(starttime);
                Date endDate = format.parse(endtime);
                if(startDate.compareTo(jiuzhenDate) < 0 && endDate.compareTo(jiuzhenDate) > 0){
                    flag = "在时间内";
                }
            }
            if(starttime == null && endtime != null){
                Date endDate = format.parse(endtime);
                if(endDate.compareTo(jiuzhenDate) > 0){
                    flag = "在时间内";
                }
            }
            if(starttime != null && endtime == null){
                Date startDate = format.parse(starttime);
                if(startDate.compareTo(jiuzhenDate) < 0 ){
                    flag = "在时间内";
                }
            }
            if(starttime == null && endtime == null){
                flag = "在时间内";
            }
        }

        if(flag.equals("不在时间范围内")){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("审查结果：","无效审查");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("原因：","该项目不在有效时间范围内");
            jsonArray.add(jsonObject);
            jsonArray.add(jsonObject1);
        }else {
            StatementResult result1 = session.run( "MATCH (s:AllData)-[:Link]->(t:Xmsm) where s.yka266='"+yka266+"' and t.doctorway='"+doctorway+"' return s,t");
            JSONArray jsonArray1 = new JSONArray();
            while ( result1.hasNext() )
            {
                Record record = result.next();
                Map maps = record.get("t").asMap();
                String limitation = (String) maps.get("limitation");
                String doctorway2 = (String)maps.get("doctorway");

//                if()

            }
        }

        session.close();
        driver.close();
        return ResponseUtil.success(jsonArray);
    }

    /*
        1.	限儿童
    输入：医保商品名编码、年龄
    输出：是否限儿童
    if 年龄 >18 && 医保商品名编码.限儿童 == 1
    输出 限儿童
    年龄是另加判断
    * */
    @ApiOperation(value="限儿童",tags={"限儿童"},notes="eg: yka266 = YP10014918 ， age = 19")
    @GetMapping(value = "/xiandingertong/{yka266}/{age}")
    public ResponseUtil xiandingertong(@ApiParam(defaultValue = "YP10014918")@PathVariable String yka266, @PathVariable int age) {
        Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USERNAME, PASSWORD ) );
        Session session = driver.session();

        StatementResult result = session.run( "MATCH (s:AllData) where s.yka266='"+yka266+"' return s");
        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        Record record = result.next();
        Map maps = record.get("s").asMap();
        String child = (String) maps.get("limitChild");
        System.out.println(child);
        if(child !=null && (child.indexOf("儿童")!=-1) && age > 18){
            jsonObject.put("审查结果","限儿童");
            jsonObject.put("原因","患者【"+age+"】岁，该项目仅限儿童使用");
        }else {
            jsonObject.put("审查结果","审查通过");
        }
//        jsonObject.put("result",record.get("s").asMap());
        jsonArray.add(jsonObject);

        session.close();
        driver.close();
        return ResponseUtil.success(jsonArray);
    }
    /*
    1.	限性别
         输入：医保商品名编码、患者性别
         输出：
        If 编码.限性别 ！= 患者性别
        输出 限性别的值
        若等于，输出 审核通过
    * */
    @ApiOperation(value="限性别",tags={"限性别"},notes="eg: yka266 = YP10014918 ， sex = 女性")
    @GetMapping(value = "/xianxingbie/{yka266}/{sex}")
    public ResponseUtil xianxingbie(@ApiParam(defaultValue = "ZL250102021")@PathVariable String yka266, @ApiParam(defaultValue = "女性")@PathVariable String sex) {
        Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USERNAME, PASSWORD ) );
        Session session = driver.session();

        StatementResult result = session.run( "MATCH (s:AllData) where s.yka266='"+yka266+"' return s");
        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        Record record = result.next();
        Map maps = record.get("s").asMap();
        String sexres = (String) maps.get("limitSex");
        System.out.println(sexres);
        if( sexres !=null && (sexres.indexOf(sex)==-1)){
            if(sex.indexOf("男")!=-1){
                jsonObject.put("审查结果","限女性");
                jsonObject.put("原因","该医保项目仅限女性使用");
            }else {
                jsonObject.put("审查结果","限男性");
                jsonObject.put("原因","该医保项目仅限男性使用");
            }
        }else {
            jsonObject.put("审查结果","审核通过");
        }
        jsonObject.put("result",record.get("s").asMap());
        jsonArray.add(jsonObject);

        session.close();
        driver.close();
        return ResponseUtil.success(jsonArray);
    }

   /*
   1.	限医院级别类型
        输入：医保商品名编码
        输出：医院级别类型
   * */
   @ApiOperation(value="医院级别类型",tags={"医院级别类型"},notes="eg: yka266 = YP10014373")
   @GetMapping(value = "/xianyiyuanleibie/{yka266}")
   public ResponseUtil xianyiyuanleibie(@ApiParam(defaultValue = "YP10014373")@PathVariable String yka266) {
       Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USERNAME, PASSWORD ) );
       Session session = driver.session();

       StatementResult result = session.run( "MATCH (s:AllData) where s.yka266='"+yka266+"' return s");
//       StatementResult result = session.run( "MATCH (s:AllData) where s.yka266='"+yka266+"' return s");
       JSONArray jsonArray = new JSONArray();

       JSONObject jsonObject = new JSONObject();
       Record record = result.next();
       Map maps = record.get("s").asMap();
       String hospitalLevel = (String) maps.get("hospitalLevel");

       jsonObject.put("answer",hospitalLevel);
       jsonObject.put("result",record.get("s").asMap());
       jsonArray.add(jsonObject);

       session.close();
       driver.close();
       return ResponseUtil.success(jsonArray);
   }


    /**
     * 1.	限定适应症条件用药
     * 输入：医保商品名编码
     * 输出：限定适应症提示信息
     * 输出 = '限'+医保商品名编码.适应症
     * “限 A024”
     */
    @ApiOperation(value="限定适应症条件用药",tags={"限定适应症条件用药"},notes="eg: yka266 = YP10014886")
    @GetMapping(value = "/xiandingshiyinzhenyongyao/{yka266}")
    public ResponseUtil xiandingshiyinzhenyongyao(@ApiParam(defaultValue = "YP10014886")@PathVariable String yka266) {
        Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USERNAME, PASSWORD ) );
        Session session = driver.session();

        StatementResult result = session.run( "MATCH (s:AllData) where s.yka266='"+yka266+"' return s");
        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        Record record = result.next();
        Map maps = record.get("s").asMap();
        String limitCode = (String) maps.get("limitCode");

        jsonObject.put("answer","限 "+limitCode);
        jsonObject.put("result",record.get("s").asMap());
        jsonArray.add(jsonObject);

        session.close();
        driver.close();
        return ResponseUtil.success(jsonArray);
    }

    /**
     * 1.	项目与项目匹配
     * 输入：时间（某一天）、医保商品名编码
     * 输出：违反项目匹配
     * 遍历一天的医保商品名编码
     * 根据医保商品名.项目编码组创建两个列表 分别存放A、B组编码的数字部分
     * 如果两个列表中有相同的数字
     * 则提示 违反项目匹配
     *
     * 输入 两次项目编码，取项目编码组 1，2，3  最多6个值 分ab两组  对比数字部分，相同则不匹配
     */
    @ApiOperation(value="项目匹配",tags={"项目匹配"},notes="eg: yka266_1 = YP10014377 ,yka266_2 = YP10014376")
    @GetMapping(value = "/xiangmupipei/{yka266_1}/{yka266_2}")
    public ResponseUtil xiangmupipei(@ApiParam(defaultValue = "YP10014377")@PathVariable String yka266_1,@ApiParam(defaultValue = "YP10014376")@PathVariable String yka266_2) {
        Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USERNAME, PASSWORD ) );
        Session session = driver.session();

        StatementResult result = session.run( "MATCH (s:AllData) where s.yka266='"+yka266_1+"' or s.yka266='"+yka266_2+"' return s");
        JSONArray jsonArray = new JSONArray();
        List<String> codesA = new ArrayList<>();
        List<String> codesB = new ArrayList<>();

        while ( result.hasNext() )
        {
            Record record = result.next();
            Map maps = record.get("s").asMap();
            String projectCode1 = (String) maps.get("projectCode1");
            String projectCode2 = (String) maps.get("projectCode2");
            String projectCode3 = (String) maps.get("projectCode3");
            if(projectCode1 != null){
                if(projectCode1.indexOf("A") > -1){
                    String[] ACodes = projectCode1.split("A");
                    codesA.add(ACodes[1]);
                }else {
                    String[] BCodes = projectCode1.split("B");
                    codesB.add(BCodes[1]);
                }
            }
            if(projectCode2 != null){
                if(projectCode2.indexOf("A")>-1){
                    String[] ACodes = projectCode2.split("A");
                    codesA.add(ACodes[1]);
                }else {
                    String[] BCodes = projectCode2.split("B");
                    codesB.add(BCodes[1]);
                }
            }
            if(projectCode3 != null){
                if(projectCode3.indexOf("A")>-1){
                    String[] ACodes = projectCode3.split("A");
                    codesA.add(ACodes[1]);
                }else {
                    String[] BCodes = projectCode3.split("B");
                    codesB.add(BCodes[1]);
                }
            }
        }

        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("codeA",codesA);
//        jsonObject.put("codeB",codesB);

        HashSet<String> set = new HashSet(codesA);
        set.retainAll(codesB);
        if(set.size() > 0){
            jsonObject.put("审查结果","违反项目匹配");
            jsonObject.put("原因","医保商品名1和医保商品名2不能在同一处方中开具");
        }else {
            jsonObject.put("审查结果","匹配");
        }

        jsonArray.add(jsonObject);
        session.close();
        driver.close();
        return ResponseUtil.success(jsonArray);
    }

}
