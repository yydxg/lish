package com.pingan.cn.controller.neo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pingan.cn.common.utils.ResponseUtil;
import io.swagger.annotations.Api;
import org.neo4j.driver.v1.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "JavaCodeNeo4j")
@RestController
@RequestMapping(value = "/api/neo/javaCode")
public class JavaCodeNeo4jController {

    private static String URI = "bolt://localhost:7687";
    private static String USERNAME = "neo4j";
    private static String PASSWORD = "123";


    @GetMapping(value = "/search/{flag}/{sName}/{tName}")
    public ResponseUtil search(@PathVariable String flag, @PathVariable String sName, @PathVariable String tName) {
        Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USERNAME, PASSWORD ) );
        Session session = driver.session();

        StatementResult result = null;
        switch (flag){
            case "R1":
                System.out.println(sName+"\n"+tName);
                if(sName.trim().equals("ALL") && tName.trim().equals("ALL")){
                    result = session.run( "MATCH (s:One1)-[:R]->(t:Two1) return s,t");
                }else if(sName.trim().equals("ALL")){
                    result = session.run( "MATCH (s:One1)-[:R]->(t:Two1) where t.name=~'.*"+tName+".*' return s,t");
                }else if (tName.trim().equals("ALL")){
                    result = session.run( "MATCH (s:One1)-[:R]->(t:Two1) where s.name=~'.*"+sName+".*' return s,t");
                }else {
                    result = session.run( "MATCH (s:One1)-[:R]->(t:Two1) where s.name=~'.*"+sName+".*' and t.name=~'.*"+tName+".*' return s,t");
                }
                break;
            case "c-has-o"://类中有对象
                result = session.run( "MATCH (s:A_class)-[:A_has]->(t:A_object) where s.name=~'.*"+sName+".*' and t.name=~'.*"+tName+".*' return s,t");
                break;
        }
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

    @GetMapping(value = "/all")
    public ResponseUtil all() {
        Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USERNAME, PASSWORD ) );
        Session session = driver.session();
        /*session.run( "CREATE (a:Person {name: {name}, title: {title}})",
                parameters( "name", "Arthur001", "title", "King001" ) );*/

        StatementResult result = session.run( "MATCH (f:A_fun)<-[]-(c:A_class)-[]->(o:A_object) with f,c,o match (v:A_var)<-[]-(c) return f,c,o,v");
        StatementResult result2 = session.run( "MATCH (s:A_class)-[]->(t:A_class) return s,t");
        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        while ( result.hasNext() )
        {
            JSONObject jsonObject = new JSONObject();
            Record record = result.next();

            jsonObject.put("v",record.get("v").asMap());
            jsonObject.put("f",record.get("f").asMap());
            jsonObject.put("c",record.get("c").asMap());
            jsonObject.put("o",record.get("o").asMap());
            jsonArray.add(jsonObject);
        }
        while ( result2.hasNext() )
        {
            JSONObject jsonObject = new JSONObject();
            Record record = result2.next();
            jsonObject.put("s",record.get("s").asMap());
            jsonObject.put("t",record.get("t").asMap());
            jsonArray2.add(jsonObject);
        }
        session.close();
        driver.close();

        JSONObject result3 = new JSONObject();
        result3.put("res1",jsonArray);
        result3.put("res2",jsonArray2);
        return ResponseUtil.success(result3);
    }

    @GetMapping(value = "/check/{type}")
    public ResponseUtil check(@PathVariable Integer type) {
        Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USERNAME, PASSWORD ) );
        Session session = driver.session();
        StatementResult result = null;
        switch (type){
            case 1: //实例化未使用
                result = session.run( "MATCH (s:A_class)-[:A_has]->(t:A_object) where toInteger(t.showNum) <2 return s,t");
                break;
            case 2: //访问权限问题
                result = session.run( "MATCH (s:A_object),(t:A_fun) where s.fun = t.name and t.qx='public' return s,t");
                break;
            case 3: //继承问题
                result = session.run( "MATCH (f:A_fun)<-[:A_has]-(c:A_class)<-[:A_extends]-(s:A_class)-[:A_has]->(t:A_object) where t.fun = f.funName  return c,s,t,f");
                break;
        }

        JSONArray jsonArray = new JSONArray();
        while ( result.hasNext() ){
            JSONObject jsonObject = new JSONObject();
            Record record = result.next();
            jsonObject.put("s",record.get("s").asMap());
            jsonObject.put("t",record.get("t").asMap());
            jsonArray.add(jsonObject);
        }
        session.close();
        driver.close();
        JSONObject result3 = new JSONObject();
        result3.put("res1",jsonArray);
        return ResponseUtil.success(jsonArray);
    }

    @PostMapping(value = "/excute")
    public ResponseUtil excute(@RequestBody List<String> cypherSqls) {
        Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USERNAME, PASSWORD ) );
        Session session = driver.session();
        try{
            StatementResult result = session.run(cypherSqls.get(0));
            while ( result.hasNext() ){
                JSONObject jsonObject = new JSONObject();
                Record record = result.next();
                jsonObject.put("s",record.asMap());
            }
            return ResponseUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }
}
