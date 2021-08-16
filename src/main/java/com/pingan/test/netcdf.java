package com.pingan.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

public class netcdf {


    public static void main(String[] args) {

        NetcdfFile openNC = null;
        try {
            String filePath = "E:/web_work2/windy-openlayer-project/浪潮流原始数据文件/shenzhen2019_2020120200_tide.nc";
            openNC = NetcdfFile.open(filePath);  //filePath:文件地址
            Variable lat = openNC.findVariable("lat");//获取变量
            System.out.println(lat.read());//读取变量
            Variable lon = openNC.findVariable("lon");
            System.out.println(lon.read());
            Variable pre = openNC.findVariable("time");
            System.out.println(pre);
            int[] varShape = pre.getShape();//获得维度
            float[] o = (float[]) pre.read().copyTo1DJavaArray();//转成java一维数据类型了
            for (int i = 0; i < o.length; i++) {
                System.out.println(o[i]);
            }

//            JSONArray jsonarray = JSONArray.;//转成JSONArray
            JSONObject json = new JSONObject();
            json.put("minLat", lat.read());
            json.put("maxLat", lat.read().getFloat((int) (lat.getSize() - 1)));
            json.put("minLon", lon.read().getFloat(0));
            json.put("maxLon", lon.read().getFloat((int) (lon.getSize() - 1)));
//            json.put("data", jsonarray);
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != openNC) {
                try {
                    openNC.close();//把文件关了
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
