package com.pingan.cn.controller.neo;

import com.alibaba.fastjson.JSONObject;
import org.neo4j.driver.v1.*;

import java.util.Scanner;

public class DoExcuteNeo4j {
    private static String URI = "bolt://localhost:7687";
    private static String USERNAME = "neo4j";
    private static String PASSWORD = "123";

    private static Config nossl = Config.build().withEncryptionLevel(Config.EncryptionLevel.NONE).toConfig();
    private static Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USERNAME, PASSWORD ) ,nossl);
    private static Session session = driver.session();
//    private static int p = 2;
//    private static int s = 2;

//    转二进制
    public static String toBinary(int num, int digits) {
        int value = 1 << digits | num;
        String bs = Integer.toBinaryString(value); //0x20 | 这个是为了保证这个string长度是6位数
        return  bs.substring(1);
    }

    private static void calculate2memory_EH(int s,int p){

        System.out.println("【内部】-----已连接数据库----");
        // 执行删除所有Vertex 节点，包括关系
        session.run("match (v:Vertex) detach delete v");
        System.out.println("【内部】-----清空数据库----");

        int num = (int)Math.pow(2,s+p+1);
        System.out.println("【内部】一共创建了："+num+"个节点。");
        String[] binarys = new String[num];
        for (int i=0;i<num;i++){
            String binary = toBinary(i,s+p+1);
//           System.out.println(binary);
            binarys[i] = binary;
            session.run("create (:Vertex{name:'"+binary+"'})");
        }

        int relCount = 0;
        for (int j=0; j < binarys.length; j++){
            String currentBinary = binarys[j];
            for(int k = j; k < binarys.length; k++){
                String toMatchBinary = binarys[k];
                if(match(currentBinary,toMatchBinary,s,p)){
//                    System.out.println("记住下标："+j+"----"+k);
                    relCount ++;
                    session.run("match (fromV:Vertex{name:'"+currentBinary+"'}), (toV:Vertex{name:'"+toMatchBinary+"'}) merge (fromV)-[:Link{cost:1}]->(toV)");
                }
            }
        }
        System.out.println("【内部】一共创建了："+relCount+"个关系。");

    }

    private static boolean match(String binary1,String binary2,int s,int p){
        //第一种匹配，尾位不同，其余相同
        String binary1exceptlast = binary1.substring(0, binary1.length() -1);
        String binary2exceptlast = binary2.substring(0, binary2.length() -1);
        String binary1last = binary1.substring(binary1.length() -1);
        String binary2last = binary2.substring(binary2.length() -1);
        if(binary1exceptlast.equals(binary2exceptlast) && !binary1last.equals(binary2last)){
            System.out.println("【内部】匹配第一种，建立关系："+binary1+"---"+binary2);
            return true;
        }
        //匹配第二种 0到s-1 有一位不同，s到s+p-1位置每一位相同，s+p相同都为0
        String binary1_0s1 = binary1.substring(0, s);
        String binary2_0s1 = binary2.substring(0, s);
        String binary1_ssp1 = binary1.substring(s, s+p);
        String binary2_ssp1 = binary2.substring(s, s+p);
//        System.out.println(binary1_0s1);
//        System.out.println(binary2_0s1);
//        System.out.println(binary1_ssp1);
//        System.out.println(binary2_ssp1);
//        System.out.println(binary1_ssp1.equals(binary2_ssp1));
        if(binary1last.equals("0") && binary2last.equals("0") && binary1_ssp1.equals(binary2_ssp1)){
            if(hasOneDifferent(binary1_0s1.toCharArray(),binary2_0s1.toCharArray())){
                System.out.println("【内部】匹配第二种，建立关系："+binary1+"---"+binary2);
                return true;
            }
        }
        // 匹配第三种
        if(binary1last.equals("1") && binary2last.equals("1") && binary1_0s1.equals(binary2_0s1)
                && hasOneDifferent(binary1_ssp1.toCharArray(),binary2_ssp1.toCharArray())) {
            System.out.println("【内部】匹配第三种，建立关系：" + binary1 + "---" + binary2);
            return true;
        }

        return false;
    }

    private static boolean hasOneDifferent(char[] firstArr,char[] secondArr){
        int count = 0;
        for(int i=0;i < firstArr.length;i++){
            char first = firstArr[i];
            char second = secondArr[i];
            if(first != second){
                count++;
            }
        }
//        System.out.println(count);
        if(count==1){
            return true;
        }
        return false;
    }

    private static void deleteFault(Scanner sc,int sum){
        StatementResult result = session.run("MATCH (n:Vertex) return count(n) as total");
        while ( result.hasNext() ){
            Record record = result.next();
            int total = record.get("total").asInt();
            if(total < sum){
                System.out.println("节点总数小于输入故障点数，不执行删除，请重新输入！");
                int sum2 = sc.nextInt();
                deleteFault(sc,sum2);
            }else {
                session.run("MATCH (n:Vertex) with n limit "+sum+" detach delete n");
            }
        }
    }

    private static void zxscs(String binary){
        StatementResult result = session.run("");
        while ( result.hasNext() ){
            Record record = result.next();

        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入交换超立方网络的参数s（整数大于0）:");
        int s = sc.nextInt();  //读取字符串型输入
        System.out.println("请输入交换超立方网络的参数p（整数大于0）:");
        int p = sc.nextInt();    //读取整型输入
        System.out.println("------------------------------------------\n 下面开始执行超立方运算 \n");
        calculate2memory_EH(s,p);
        System.out.println("-------------------------------------------\n 结束！已完成EH（s,p）的建立 \n");
        System.out.println("请输入故障点集合的节点数（小于上面打印的节点总数）:");
        int sum = sc.nextInt();    //读取整型输入
        deleteFault(sc,sum);
        System.out.println("完成故障点删除！");
        System.out.println("Over.");
    }
}
