package com.pingan.cn.controller.neo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pingan.cn.common.utils.ResponseUtil;
import io.swagger.annotations.Api;
import org.neo4j.driver.v1.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "JavaCodeNeo4j")
@RestController
@RequestMapping(value = "/api/neo/kc")
public class KeChengController {
    private static String URI = "bolt://localhost:7687";
    private static String USERNAME = "neo4j";
    private static String PASSWORD = "123";

    @GetMapping(value = "/search/{flag}")
    public ResponseUtil search(@PathVariable String flag) {
        /**
         * value="JAVAEE">JAVAEE</Option>
         *                 <Option value="JAVASE">JAVASE</Option>
         *                 <Option value="JAVAWEB">JAVAWEB</Option>
         *                 <Option value="JSJWL">计算机网络</Option>
         */
        ResponseUtil result = new ResponseUtil();
        switch (flag){
            case "JAVASE"://类中有对象
                result =  JAVASE();
                break;
            case "JAVAEE"://类中有变量
                result = JAVAEE();
                break;
            case "JSJWL"://类中有函数
                result = JSJWL();
                break;
            case "JAVAWEB":
                result = JAVAWEB();
            default:
                break;
        }
        return result;
    }

    private static ResponseUtil JAVASE(){
        Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USERNAME, PASSWORD ) );
        Session session = driver.session();
        StatementResult result = session.run( "MATCH (s)- [:A_se_r]-> (t)- [:A_se_r] -> (p) return s,t,p");

        JSONArray jsonArray = new JSONArray();
        while ( result.hasNext() )
        {
            JSONObject jsonObject = new JSONObject();
            Record record = result.next();
            jsonObject.put("s",record.get("s").asMap());
            jsonObject.put("t",record.get("t").asMap());
            jsonObject.put("p",record.get("p").asMap());
            jsonArray.add(jsonObject);
        }
        session.close();
        driver.close();
        return ResponseUtil.success(jsonArray);
    }

    private static ResponseUtil JAVAEE(){
        Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USERNAME, PASSWORD ) );
        Session session = driver.session();
//        StatementResult result = session.run( "match (s)-[:A_ee_r]->(t) return labels(s) as ls,labels(t) as lt,s,t");
        StatementResult result = session.run( "MATCH (s)- [:A_ee_r]-> (t)- [:A_ee_r] -> (p) return s,t,p");

        JSONArray jsonArray = new JSONArray();
        while ( result.hasNext() )
        {
            JSONObject jsonObject = new JSONObject();
            Record record = result.next();
//            jsonObject.put("ls",record.get("ls").asList());
//            jsonObject.put("lt",record.get("lt").asList());
            jsonObject.put("s",record.get("s").asMap());
            jsonObject.put("t",record.get("t").asMap());
            jsonObject.put("p",record.get("p").asMap());
            jsonArray.add(jsonObject);
        }
        session.close();
        driver.close();
        return ResponseUtil.success(jsonArray);
    }

    private static ResponseUtil JAVAWEB(){
        Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USERNAME, PASSWORD ) );
        Session session = driver.session();
//        StatementResult result = session.run( "match (s)-[:A_ee_r]->(t) return labels(s) as ls,labels(t) as lt,s,t");
        StatementResult result = session.run( "MATCH (s)- [:A_web_r]-> (t)- [:A_web_r] -> (p) return s,t,p");

        JSONArray jsonArray = new JSONArray();
        while ( result.hasNext() )
        {
            JSONObject jsonObject = new JSONObject();
            Record record = result.next();
//            jsonObject.put("ls",record.get("ls").asList());
//            jsonObject.put("lt",record.get("lt").asList());
            jsonObject.put("s",record.get("s").asMap());
            jsonObject.put("t",record.get("t").asMap());
            jsonObject.put("p",record.get("p").asMap());
            jsonArray.add(jsonObject);
        }
        session.close();
        driver.close();
        return ResponseUtil.success(jsonArray);
    }


    private static ResponseUtil JSJWL(){
        Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USERNAME, PASSWORD ) );
        Session session = driver.session();
        StatementResult result = session.run( "MATCH (s)-[r:A_xianxiu]->(t) RETURN s,t");

        JSONArray jsonArray = new JSONArray();
        while ( result.hasNext() )
        {
            JSONObject jsonObject = new JSONObject();
            Record record = result.next();
            jsonObject.put("s",record.get("s").asMap());
            jsonObject.put("t",record.get("t").asMap());
            jsonArray.add(jsonObject);
        }
        session.close();
        driver.close();
        return ResponseUtil.success(jsonArray);
    }

}
