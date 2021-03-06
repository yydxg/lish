package com.pingan.cn.spatialdatamanage.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.guanqu.Diaoshui;
import com.pingan.cn.ningbomap.entity.Point1;
import com.pingan.cn.spatialdatamanage.dao.Point1Dao;
import com.pingan.cn.spatialdatamanage.dto.Point1Dto;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.geolatte.geom.C2D;
import org.geolatte.geom.codec.Wkt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("point1Service")
public class Point1Service {
    private static GeometryFactory geometryFactory = new GeometryFactory();

    @Autowired
    private Point1Dao actionDao;

    public Point1 saveAction(Point1 entity){
        try {
//            Polygon polygon = geometryFactory.createPolygon(new Coordinate[]{new Coordinate(120.153576,30.287459),new Coordinate(121.153576,30.287459),new Coordinate(120.153576,31.287459),new Coordinate(120.153576,30.287459)});
//            polygon.setSRID(4326);
//            entity.setGeometry(polygon);
            return actionDao.save(entity);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Point1 saveAction3(){
        try {
            Point1 entity = new Point1();
            List<Point> points = new ArrayList<>();
            points.add(new Point(120.153576,30.287459));
            points.add(new Point(121.153576,30.287459));
            points.add(new Point(120.153576,31.287459));
            points.add(new Point(120.153576,30.287459));

            Polygon polygon1 = new Polygon(points);
//            entity.setGeometry(polygon1);
            return actionDao.save(entity);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Point1 saveAction2(Point1Dto action){
        try {
            Point1 entity = new Point1();
//            BeanUtils.copyProperties(entity, action, SpringUtil.getNullPropertyNames(entity));
//            org.geolatte.geom.Polygon polygon = (org.geolatte.geom.Polygon) Wkt.fromWkt("SRID=4326;POLYGON ((30 10, 10 20, 20 40, 40 40, 30 10))");
            org.geolatte.geom.Point point = (org.geolatte.geom.Point) Wkt.fromWkt(action.getWkt());

            entity.setOthers(action.getOthers());
            entity.setGeometry(point);
            return actionDao.save(entity);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteById(String id){
        try {
            actionDao.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Point1 findById(String id){
        return actionDao.findById(id).get();
    }

    /*public static boolean importGeojson(String geojsonpath, String tablename) throws IOException {
        if (!validateGeojson(geojsonpath, true)) return false;
        DataStore pgDatastore = postgisDataStore.getInstance();
        FeatureJSON featureJSON = new FeatureJSON();
        FeatureCollection featureCollection = featureJSON.readFeatureCollection(new FileInputStream(geojsonpath));
        SimpleFeatureType geojsontype = (SimpleFeatureType) featureCollection.getSchema();
        SimpleFeatureTypeBuilder typeBuilder = new SimpleFeatureTypeBuilder();
        typeBuilder.init(geojsontype);
        typeBuilder.setName(tablename);
        SimpleFeatureType newtype = typeBuilder.buildFeatureType();
        pgDatastore.createSchema(newtype);

        FeatureIterator iterator = featureCollection.features();
        FeatureWriter<SimpleFeatureType, SimpleFeature> featureWriter = pgDatastore.getFeatureWriterAppend(tablename, Transaction.AUTO_COMMIT);        while (iterator.hasNext()) {
            Feature feature = iterator.next();
            SimpleFeature simpleFeature = featureWriter.next();
            Collection<Property> properties = feature.getProperties();
            Iterator<Property> propertyIterator = properties.iterator();
            while (propertyIterator.hasNext()) {
                Property property = propertyIterator.next();
                simpleFeature.setAttribute(property.getName().toString(), property.getValue());
            }
            featureWriter.write();
        }
        iterator.close();
        featureWriter.close();
        pgDatastore.dispose();
        return true;
    }*/

    /** ??? **/
    public void getSiteMap() {
        URL dataUrl = this.getClass().getClassLoader().getResource("public\\station.json");  //  259
        try{
            BufferedReader br =new BufferedReader(new FileReader(new File(dataUrl.toURI())));
            String s = null;
            while((s = br.readLine()) != null){  // s ????????????json???
//                System.out.println("00=="+s);
                JSONObject jo = new JSONObject(); // ????????????????????????json??????json??????
                JSONArray features = jo.getJSONArray("features"); //??????features???json??????
                for (int i = 0; i < features.size(); i++) {
                    JSONObject info = features.getJSONObject(i); // ??????features??????i?????????

                    JSONObject geometry = info.getJSONObject("geometry");
                    JSONObject properties = info.getJSONObject("properties");

//                    List list  = geometry.getJSONArray("coordinates").toJavaList();  // ???????????????
//                    System.out.println(Double.parseDouble(list.get(0).toString()));
//                    System.out.println(Double.parseDouble(list.get(1).toString()));
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**???**/
    public void jsonOutPut(Map map) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            mapper.writeValue(new File("D:/river-site.json"), map);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /*public boolean update(Diaoshui action){
        if(action == null || StrUtil.isBlank(action.getId())){
            return  false;
        }
        Optional<Diaoshui> byId = actionDao.findById(action.getId());
        if (byId.isPresent()){
            Diaoshui oldAction = actionDao.findById(action.getId()).get();
            BeanUtils.copyProperties(action,oldAction, SpringUtil.getNullPropertyNames(action));
            try {
                actionDao.saveAndFlush(oldAction);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public List<Diaoshui> findAll(){
        return actionDao.findAll();
    }

    public Diaoshui findById(String id){
        return actionDao.findById(id).get();
    }

    public boolean deleteById(String id){
        try {
            actionDao.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBatch(String[] ids){
        ArrayList<Diaoshui> arrayList = new ArrayList<>();
        for (String id:ids) {
            Diaoshui entity = findById(id);
            if (entity != null){
                arrayList.add(entity);
            }
        }
        try {
            actionDao.deleteInBatch(arrayList);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }*/
}
