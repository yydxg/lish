package com.pingan.cn.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pingan.cn.common.utils.ResponseUtil;
import io.swagger.annotations.Api;
import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Path;
import org.neo4j.driver.v1.types.Relationship;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

@Api(tags = "neo4j")
@RestController
@RequestMapping(value = "/api/neo4j")
public class Neo4jController {

    private static String URI = "bolt://localhost:7687";
    private static String USERNAME = "neo4j";
    private static String PASSWORD = "123";


    @GetMapping(value = "/search/route/{start}/{end}")
    public ResponseUtil searchRoute(@PathVariable String start,@PathVariable String end) {
        Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USERNAME, PASSWORD ) );
        Session session = driver.session();

        StatementResult result  = session.run( "MATCH (start:NAPoints{oid:'"+start+"'}), (end:NAPoints{oid:'"+end+"'})\n" +
                "MATCH p=shortestPath((start)-[:Routes*]-(end)) RETURN p");
        JSONArray jsonArray = new JSONArray();
        int count = 0,count1=0;
        int distance = 0;
        while(result.hasNext()){
            Record record = result.next();
            List<Value> value = record.values();
            for(Value i:value){
                Path path = i.asPath();
                Iterator nodes = path.nodes().iterator();
                //主要是这里的代码
                while (nodes.hasNext()){
                    count1++;
                    Node node = (Node)nodes.next();
                    System.out.println( node.asMap());
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("p",node.asMap());
                    jsonArray.add(node.asMap());
                }
                //处理路径中的关系，暂时没用到
                Iterator relationships = path.relationships().iterator();
                //Iterator nodes = path.nodes().iterator();//得到path中的节点


                while(relationships.hasNext()){
                    count++;
                    Relationship relationship = (Relationship) relationships.next();
                    long startNodeId = relationship.startNodeId();
                    long endNodeId = relationship.endNodeId();
                    String relType = relationship.type();
                    System.out.println("关系"+count+"： ");
                    System.out.println("关系类型："+relType);
                    System.out.println("from "+startNodeId+"-----"+"to "+endNodeId);
                    System.out.println("关系属性如下：");
                    //得到关系属性的健
                    Iterator relKeys = relationship.keys().iterator();
                    //这里处理关系属性
                    while(relKeys.hasNext()){
                        String relKey = relKeys.next().toString();
                        String relValue = relationship.get(relKey).asObject().toString();
                        System.out.println(relKey+"-----"+relValue);

                        distance +=  Integer.parseInt(relationship.get("distance").asString());

                    }
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }
            }
        }
        session.close();
        driver.close();
        JSONObject result2 = new JSONObject();
        result2.put("res",jsonArray);
        result2.put("distance",distance);
        return ResponseUtil.success(result2);
    }

    /**
     * 直接查找下面节点
     * @param name
     * @return
     */
    @GetMapping(value = "/search/{name}")
    public ResponseUtil search2(@PathVariable String name) {
        Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USERNAME, PASSWORD ) );
        Session session = driver.session();

//        StatementResult result  = session.run( "MATCH (s:Chemicalknowledge) -[:has]->(t:Chemicalknowledge) where s.name='"+name+"' return s,t");
        StatementResult result  = session.run( "MATCH (s:WuliZhangjie) -[:extends]->(t:WuliZhangjie) where s.name='"+name+"' return s,t");
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

    /**
     * 条件查找
     * @param sName
     * @param tName
     * @return
     */
    @GetMapping(value = "/search/{sName}/{tName}")
    public ResponseUtil search(@PathVariable String sName, @PathVariable String tName) {
        Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USERNAME, PASSWORD ) );
        Session session = driver.session();

//        StatementResult result  = session.run( "MATCH (s:Chemicalknowledge) <-[*1..10]->(t:Chemicalknowledge) where s.name=~'.*"+sName+".*' and t.name=~'.*"+tName+".*' return s,t");
        StatementResult result  = session.run( "MATCH (s:WuliZhangjie) <-[*1..10]->(t:WuliZhangjie) where s.name=~'.*"+sName+".*' and t.name=~'.*"+tName+".*' return s,t");
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

    /**
     * 查询所有
     * @return
     */
    @GetMapping(value = "/all")
    public ResponseUtil all() {
        Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USERNAME, PASSWORD ) );
        Session session = driver.session();

//        StatementResult result2 = session.run( "MATCH (s)-[r:has]->(t) RETURN s,t");
        StatementResult result2 = session.run( "MATCH (s)-[r:extends]->(t) RETURN s,t");
        JSONArray jsonArray2 = new JSONArray();
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
        result3.put("res",jsonArray2);
        return ResponseUtil.success(result3);
    }
}
