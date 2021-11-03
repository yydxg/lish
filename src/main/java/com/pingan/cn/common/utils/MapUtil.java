package com.pingan.cn.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.WKTReader;
import org.geotools.geojson.GeoJSONUtil;
import org.geotools.geojson.geom.GeometryJSON;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;

public class MapUtil {
    /**
     * wkt和geoJson互相转的工具类
     */

        private static WKTReader reader = new WKTReader();

        private static final String GEO_JSON_TYPE="GeometryCollection";

        private static final String WKT_TYPE="GEOMETRYCOLLECTION";

        public static void main(String[] args) {
            String wkt = "GEOMETRYCOLLECTION(POINT(4 6),LINESTRING(4 6,7 10))";
            String wkt0="POLYGON((1 1,5 1,5 5,1 5,1 1),(2 2,2 3,3 3,3 2,2 2))";
            JSONObject jsonObject = wktToJson(wkt);
            System.out.println(jsonObject);
            String s = jsonToWkt(jsonObject);
            System.out.println("s = " + s);
        }

        /**
         * wkt转Json
         * @param wkt
         * @return
         */
        public static JSONObject wktToJson(String wkt) {
            String json = null;
            JSONObject jsonObject=new JSONObject();
            try {
                Geometry geometry = reader.read(wkt);
                StringWriter writer = new StringWriter();
                GeometryJSON geometryJSON=new GeometryJSON();
                geometryJSON.write(geometry,writer);

                json = writer.toString();
                jsonObject = JSONObject.parseObject(json);

            } catch (Exception e) {
                System.out.println("WKT转GeoJson出现异常");
                e.printStackTrace();
            }
            return jsonObject;
        }

        /**
         *  geoJson转wkt
         * @param jsonObject
         * @return
         */
        public static String jsonToWkt(JSONObject jsonObject) {
            String wkt = null;
            String type = jsonObject.getString("type");
            GeometryJSON gJson = new GeometryJSON();
            try {
                // {"geometries":[{"coordinates":[4,6],"type":"Point"},{"coordinates":[[4,6],[7,10]],"type":"LineString"}],"type":"GeometryCollection"}
                if(MapUtil.GEO_JSON_TYPE.equals(type)){
                    // 由于解析上面的json语句会出现这个geometries属性没有采用以下办法
                    JSONArray geometriesArray = jsonObject.getJSONArray("geometries");
                    // 定义一个数组装图形对象
                    int size = geometriesArray.size();
                    Geometry[] geometries=new Geometry[size];
                    for (int i=0; i<size; i++){
                        String str = geometriesArray.get(i).toString();
                        // 使用GeoUtil去读取str
                        Reader reader = GeoJSONUtil.toReader(str);
                        Geometry geometry = gJson.read(reader);
                        geometries[i]=geometry;
                    }
                    GeometryCollection geometryCollection = new GeometryCollection(geometries, new GeometryFactory());
                    wkt = geometryCollection.toText();
                }else {
                    Reader reader = GeoJSONUtil.toReader(jsonObject.toString());
                    Geometry read = gJson.read(reader);
                    wkt=read.toText();
                }
            } catch (IOException e){
                System.out.println("GeoJson转WKT出现异常");
                e.printStackTrace();
            }
            return wkt;
        }
}
