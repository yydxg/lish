package com.pingan.cn.ningbomap.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import com.pingan.cn.guanqu.Diaoshui;
import com.pingan.cn.guanqu.DiaoshuiDao;
import com.pingan.cn.ningbomap.dao.PrjWeilanDao;
import com.pingan.cn.ningbomap.entity.PrjWeilan;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.geolatte.geom.codec.Wkt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("prjWeilanService")
public class PrjWeilanService {
    private static GeometryFactory geometryFactory = new GeometryFactory();

    @Autowired
    private PrjWeilanDao actionDao;

    public PrjWeilan saveAction(PrjWeilan entity){
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

    public PrjWeilan saveAction3(){
        try {
            PrjWeilan entity = new PrjWeilan();
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

    public PrjWeilan saveAction2(){
        try {
            PrjWeilan entity = new PrjWeilan();
            org.geolatte.geom.Polygon polygon = (org.geolatte.geom.Polygon) Wkt.fromWkt("SRID=4326;POLYGON ((30 10, 10 20, 20 40, 40 40, 30 10))");
//            entity.setGeometry(polygon);
            return actionDao.save(entity);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
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

    /** 读 **/
    public void getSiteMap() {
        URL dataUrl = this.getClass().getClassLoader().getResource("public\\station.json");  //  259
        try{
            BufferedReader br =new BufferedReader(new FileReader(new File(dataUrl.toURI())));
            String s = null;
            while((s = br.readLine()) != null){  // s 为原生的json串
//                System.out.println("00=="+s);
                JSONObject jo = new JSONObject(); // 创建一个包含原始json串的json对象
                JSONArray features = jo.getJSONArray("features"); //找到features的json数组
                for (int i = 0; i < features.size(); i++) {
                    JSONObject info = features.getJSONObject(i); // 获得features的第i个对象

                    JSONObject geometry = info.getJSONObject("geometry");
                    JSONObject properties = info.getJSONObject("properties");

//                    List list  = geometry.getJSONArray("coordinates").toJavaList();  // 获得经纬度
//                    System.out.println(Double.parseDouble(list.get(0).toString()));
//                    System.out.println(Double.parseDouble(list.get(1).toString()));
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**写**/
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
