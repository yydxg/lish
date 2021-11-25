package com.pingan.cn.netcdf;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pingan.cn.dao.ActionDao;
import com.pingan.cn.entity.Action;
import io.swagger.models.auth.In;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucar.ma2.*;
import ucar.nc2.Attribute;
import ucar.nc2.NCdumpW;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

import java.io.*;
import java.util.*;

@Service("parseService")
public class ParseService {

//    /**
//     * 从全球nc数据中裁剪指定区域的数据
//     *
//     * @param resourPath nc文件所在位置路径
//     *  @param startLon  指定区域开始的经度
//     *  @param startLat  指定区域开始的纬度
//     * @param latCount  纬度要读取多少个点
//     * @param lonCount  经度要读取多少个点
//     * @param begin    从时间纬度的第几层开始读取
//     * @param end      第几层结束
//     * @return   指定区域的数据
//     */
//    public static short[][] readNCfile(String resourPath, int latCount, int lonCount, float startLon, float startLat,int begin,
//                                       int end) {
//        try {
//            NetcdfFile ncFile = NetcdfFile.open(resourPath); //获取源文件
//            Variable v = ncFile.findVariable("qpf_ml"); //读取qpf_ml的变量，
//            //
//            short[][] values = null;
//            for (int i = begin; i < end; i++) {    //本读取的qpf_ml是一个3维数据，时间、经度、维度，一下子把3维数据全部读出来会很大，时间维度是24层，所以通过遍历时间维度获取数据，i为时间维度的层数
//
//                int[] origin = { i, (int) ((latNorth - startLat) * 100), (int) ((startLon - lonEast) * 100) };//origin 设置维度准备从哪个位置开始读取数据
//                int[] shape = { 1, latCount, lonCount };//shape 和origin对应，设置读取多少点后结束
//                short[][] temp = (short[][]) v.read(origin, shape).reduce(0).copyToNDJavaArray(); //去掉时间维度，变为二维
//
//                if (values != null) {
//                    for (int j = 0; j < latCount; j++) {
//                        for (int k = 0; k < lonCount; k++) {
//                            values[j][k] += temp[j][k];
//                        }
//                    }
//                }else {
//                    values = temp;
//                }
//
//            }
//            ncFile.close();
//            return values;
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (InvalidRangeException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    /* 读取和打印经纬度变量，了解数据组织结构 */
    // NC数据格式：经度:从左到右（正走），纬度：从下到上（正走）
    //.read()：是读取这个变量所有的数据。
    public static void main(String[] args) {
        //        String filename = "E:\\web_work2\\nc_project\\19800101_ocean_bhd\\19800101_ocean_bhd.nc";

        /*String NC_PATH = "E:/web_work2/nc_project/bigdata/atoms/198102_atmos_cs.nc";
        String rootPath = "E:/web_work2/nc_project/ncjson/atmos_cs/flow/";
        String timeAlias = "time";
        String uAlias = "u10";
        String vAlias = "v10";
        String lonAlias = "lon";
        String latAlias = "lat";
        int hourOffset = 3;
        ParseService.parseAtomsFlow(NC_PATH,rootPath,timeAlias,uAlias,vAlias,lonAlias,latAlias,hourOffset);*/

        String NC_PATH = "E:/web_work2/nc_project/bigdata/ocean_scs/1980/19800101_ocean_scs.nc";
        String rootPath = "E:/web_work2/nc_project/ncjson/ocean_scs/flow/";
        String timeAlias = "time";
        String levAlias = "lev";
        String uAlias = "u";
        String vAlias = "v";
        String uLonAlias = "lon_u";
        String uLatAlias = "lat_u";
        String vLonAlias = "lon_v";
        String vLatAlias = "lat_v";
        int hourOffset = 3;
        ParseService.parseOceanFlow(NC_PATH,rootPath,timeAlias,levAlias,uAlias,vAlias,uLonAlias,uLatAlias,vLonAlias,vLatAlias,hourOffset);

//        String NC_PATH =  "E:/web_work2/nc_project/bigdata/ocean_scs/1980/19800101_ocean_scs.nc";
//         String rootPath = "E:/web_work2/nc_project/ncjson/ocean_scs/static/ssh";
//        String NC_PATH =  "E:\\web_work2\\nc_project\\bigdata\\wave\\198001_wave_cs.nc";
//        String rootPath = "E:/web_work2/nc_project/ncjson/wave_cs/static/zerop";
        /*String NC_PATH =  "E:\\web_work2\\nc_project\\bigdata\\atoms\\198102_atmos_cs.nc";
        String rootPath = "E:/web_work2/nc_project/ncjson/atmos_cs/static/t2m";
        String timeAlias = "time";
        String typeAlias = "t2m";
        String lonAlias = "lon";
        String latAlias = "lat";
        int hourOffset = 3;
        int latOffset = 2;
        int lonOffset = 2;
        ParseService.parse_3d_static(NC_PATH,rootPath,timeAlias,typeAlias,lonAlias,latAlias,latOffset,lonOffset,hourOffset);*/


//        String NC_PATH = "E:\\web_work2\\nc_project\\bigdata\\ocean_bhd\\1980\\19800101_ocean_bhd.nc";
//        String rootPath = "E:/web_work2/nc_project/ncjson/ocean_bhd/static/temp";
        /*String NC_PATH = "E:\\web_work2\\nc_project\\bigdata\\ocean_scs\\1980\\19800101_ocean_scs.nc";
        String rootPath = "E:/web_work2/nc_project/ncjson/ocean_scs/static/salt";
        String startYear = "1980/01/01-0000";
        String timeAlias = "time";
        String typeAlias = "salt";
        String levAlias = "lev";
        String lonAlias = "lon_r";
        String latAlias = "lat_r";
        int hourOffset = 3;
        int latOffset = 2;
        int lonOffset = 2;
        ParseService.parse_4d_static(NC_PATH,rootPath,startYear,timeAlias,levAlias,typeAlias,lonAlias,latAlias,latOffset,lonOffset,hourOffset);*/


        /*String NC_PATH =  "E:\\web_work2\\nc_project\\bigdata2\\bigdata_atm1990_t2m.nc";
        String rootPath = "E:/web_work2/nc_project/ncjson/bigdata_atm1990/static/T2";
        String year = "1990/01/01-0000";
        String typeAlias = "T2";
        String lonAlias = "lon";
        String latAlias = "lat";
        int hourOffset = 3;
        int latOffset = 5;
        int lonOffset = 5;
        int sliceNum = 1;
        ParseService.parse_3d_static_bigdata(NC_PATH,rootPath,year,typeAlias,lonAlias,latAlias,latOffset,lonOffset,hourOffset,sliceNum);*/

        /*String U_NC_PATH = "E:/web_work2/nc_project/bigdata2/bigdata_atm1990_u10.nc";
        String V_NC_PATH = "E:/web_work2/nc_project/bigdata2/bigdata_atm1990_v10.nc";
        String rootPath = "E:/web_work2/nc_project/ncjson/bigdata_atm/flow/";
        String startYear = "1990/01/01-0000";
        String uAlias = "U10";
        String vAlias = "V10";
        String lonAlias = "lon";
        String latAlias = "lat";
        int hourOffset = 3;
        int latOffset = 2;
        int lonOffset = 2;
        ParseService.parse_3d_flow_bigdata(U_NC_PATH,V_NC_PATH,rootPath,startYear,uAlias,
                vAlias,lonAlias,latAlias,latOffset,lonOffset,hourOffset);*/

        /*String NC_PATH =  "E:\\web_work2\\nc_project\\bigdata2\\ww3_LMN_1992.nc";
        String rootPath = "E:/web_work2/nc_project/ncjson/ww3_LMN/static/LMN";
        String year = "1990/01/01-0000";
        String typeAlias = "LMN";
        int hourOffset = 3;
        int latOffset = 4;
        int lonOffset = 4;
        int sliceNum = 1;
        ParseService.parse_3d_static_www(NC_PATH,rootPath,year,typeAlias,latOffset,lonOffset,hourOffset,sliceNum);*/

        /*String filePath = "E:\\web_work2\\nc_project\\bigdata\\typhoon_besttrack\\CMABSTdata\\CH1950BST.txt";
        String fileName = "CH1950BST";
        String outFullPath = "E:\\web_work2\\nc_project\\ncjson\\typhoon_besttrack";
        ParseService.parseTxt(filePath,fileName,outFullPath);*/

    }

    public static void parseTxt(String filePath,String fileName,String outFullPath){
        try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                java.util.ArrayList lists = new ArrayList<>();
                java.util.ArrayList featureArrs = new ArrayList<>();
                Map<String, Object> jsonMap = new HashMap<>();
                while((lineTxt = bufferedReader.readLine()) != null){
//                    System.out.println(lineTxt);
                    if (lineTxt.startsWith("66666")){
                        if (featureArrs.size() > 0){
                            jsonMap.put("features",featureArrs);
                            lists.add(jsonMap);
                            jsonMap =  new HashMap<>();
                            featureArrs = new ArrayList<>();
                        }
                        String flbz = lineTxt.substring(0,5).trim();
                        String gjbh = lineTxt.substring(5,10).trim();
                        String jlhs = lineTxt.substring(10,15).trim();
                        String qxxh = lineTxt.substring(15,20).trim();
                        String qxbh = lineTxt.substring(20,25).trim();
                        String zjjl = lineTxt.substring(26,28).trim();
                        String jgxss = lineTxt.substring(28,30).trim();
                        String rdqxmc = lineTxt.substring(30,50).trim();
                        String sjxcrq = lineTxt.substring(65,73).trim();

                        jsonMap.put("type","FeatureCollection");
                        jsonMap.put("flbz",flbz);
                        jsonMap.put("gjbh",gjbh);
                        jsonMap.put("jlhs",jlhs);
                        jsonMap.put("qxxh",qxxh);
                        jsonMap.put("qxbh",qxbh);
                        jsonMap.put("zjjl",zjjl);
                        jsonMap.put("jgxss",jgxss);
                        jsonMap.put("rdqxmc",rdqxmc);
                        jsonMap.put("sjxcrq",sjxcrq);

                    }else {
                        String date = lineTxt.substring(0,10).trim();
                        String qdbj = lineTxt.substring(10,12).trim();
                        double lat = Integer.parseInt(lineTxt.substring(13,16).trim())*0.1;
                        double lon = Integer.parseInt(lineTxt.substring(17,22).trim())*0.1;
                        String pres = lineTxt.substring(23,26).trim();
                        String wnd = lineTxt.substring(32,34).trim();
                        Map<String, Object> featuremap = new HashMap<>();
                        Map<String, Object> propertiesmap = new HashMap<>();
                        Map<String, Object> geometrymap = new HashMap<>();
                        featuremap.put("type","Feature");
                        propertiesmap.put("date",date);
                        propertiesmap.put("qdbj",qdbj);
                        propertiesmap.put("pres",pres);
                        propertiesmap.put("wnd",wnd);
                        if(lineTxt.length()==39){
                            String owd = lineTxt.substring(37,39).trim();
                            propertiesmap.put("owd",owd);
                        }
                        featuremap.put("properties",propertiesmap);
                        geometrymap.put("type","Point");
                        geometrymap.put("coordinates",new Double[]{lon,lat});
                        featuremap.put("geometry",geometrymap);
                        featureArrs.add(featuremap);
                    }
                }
                String jsonString = JSONArray.toJSONString(lists);

                JsonUtil.createJsonFile(jsonString, outFullPath, fileName);
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }

    /**
     *
     * @param NC_PATH   nc文件路径
     * @param rootPath  根路径
     * @param timeAlias 时间名称
     * @param typeAlias 解析类型变量名称
     * @param lonAlias 经度名称
     * @param latAlias 维度名称
     * @param latOffset 纬度间隔取值
     * @param lonOffset 经度间隔取值
     */
    public static void parse_3d_static(String NC_PATH,String rootPath,String timeAlias,String typeAlias,
                                       String lonAlias,String latAlias,Integer latOffset,Integer lonOffset,Integer hourOffset){
        NetcdfFile ncfile = null;
        String long_name = "",units = "";
        double scale_factor = 0;

        try {
            ncfile = NetcdfFile.open(NC_PATH);
            Variable typeBean = ncfile.findVariable(typeAlias);

            Variable lonBean = ncfile.findVariable(lonAlias);
            Variable latBean = ncfile.findVariable(latAlias);
            Variable timeBean = ncfile.findVariable(timeAlias);
            int[] uShape = typeBean.getShape();
            System.out.println(Arrays.toString(uShape));
            ArrayFloat.D1 timeArray = (ArrayFloat.D1) timeBean.read();
            ArrayFloat.D1 lonArray = (ArrayFloat.D1) lonBean.read();
            ArrayFloat.D1 latArray = (ArrayFloat.D1) latBean.read();

            List<Attribute> typeAttributes = typeBean.getAttributes();
            for(Attribute typeAttr : typeAttributes){
                if("long_name".equals(typeAttr.getName())){
                    long_name = typeAttr.getStringValue();
                }
                if("units".equals(typeAttr.getName())){
                    units = typeAttr.getStringValue();
                }
                if("scale_factor".equals(typeAttr.getName())){
                    scale_factor = typeAttr.getValues().getDouble(0);
                }
            }

            if (null != typeBean) {
                Array typeData = typeBean.read();
                Index typeIndex = typeData.getIndex();

                for (int i=0; i < uShape[0]; i = i+hourOffset) { //uShape[0]
                    long addHours = (long)timeArray.get(i);
                    String endday = DateUtil.getEndtimeStr("1980/01/01-0000",addHours,"yyyy/MM/dd-HHmm");
//                    AnsiConsole.out.println( Ansi.ansi().fg(Ansi.Color.GREEN).a("开始处理【"+endday+"】数据...").reset() );

                    Map<String, Object> jsonMap = new HashMap<>();
                    java.util.ArrayList featureArrs = new ArrayList<>();
                    double minVal = typeData.getDouble(typeIndex.set(0,0,0)) * scale_factor,
                            maxVal = typeData.getDouble(typeIndex.set(0,0,0)) * scale_factor;

                    for (int j=0; j < uShape[1]; j = j + latOffset) { //维度
                        float latValue = (float)latArray.get(j);
                        for (int k=0; k < uShape[2]; k = k + lonOffset){ //经度
                            float lonValue = (float)lonArray.get(k);
                            double val = typeData.getDouble(typeIndex.set(i,j,k)) * scale_factor;
                            /*if(Double.isNaN(val) ){
                                double  val2 = typeData.getDouble(typeIndex.set(i,j,k));
                                Object val3 = typeData.getObject(typeIndex.set(i,j,k));
                                System.out.println(val2);
                                System.out.println(val3);
                            }*/
                            if(!Double.isNaN(val) && (val != 32767 * scale_factor)){
                                Map<String, Object> featuremap = new HashMap<>();
                                Map<String, Object> propertiesmap = new HashMap<>();
                                Map<String, Object> geometrymap = new HashMap<>();
                                featuremap.put("type","Feature");
                                propertiesmap.put("v",val);
                                featuremap.put("properties",propertiesmap);
                                geometrymap.put("type","Point");
                                geometrymap.put("coordinates",new Float[]{lonValue,latValue});
                                featuremap.put("geometry",geometrymap);
                                featureArrs.add(featuremap);

                                if(Double.isNaN(minVal)){
                                    minVal = val;
                                }
                                if(Double.isNaN(maxVal)){
                                    maxVal = val;
                                }
                                if(minVal > val){
                                    minVal = val;
//                                    if(minVal == -32.768){
//                                        System.out.println(lonValue);
//                                        System.out.println(latValue);
//                                    }
                                }
                                if(maxVal < val){
                                    maxVal = val;
//                                    if(maxVal == 32.767){
//                                        System.out.println(lonValue);
//                                        System.out.println(latValue);
//                                    }
                                }
                            }
                        }
                    }

                    jsonMap.put("type","FeatureCollection");
                    jsonMap.put("long_name",long_name);
                    jsonMap.put("units",units);
                    jsonMap.put("reftime",endday);
                    jsonMap.put("maxvalue",maxVal);
                    jsonMap.put("minvalue",minVal);
                    jsonMap.put("lonrange",lonArray.get(0)+"-"+lonArray.get((int)(lonArray.getSize()-1)));
                    jsonMap.put("latrange",latArray.get(0)+"-"+latArray.get((int)(latArray.getSize()-1)));
                    jsonMap.put("features",featureArrs);
                    String jsonString = JSONObject.toJSONString(jsonMap);

                    String[] pathAndTime = endday.split("-");
                    String pathName = pathAndTime[0];
                    String timeName = pathAndTime[1];
                    String fullPath = rootPath.endsWith("/")?rootPath +  pathName:rootPath + "/" + pathName;

                    JsonUtil.createJsonFile(jsonString, fullPath, timeName);
                }
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        } finally {
            if (null != ncfile)
                try {
                    ncfile.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
        }
    }

    /**
     *
     * @param NC_PATH
     * @param rootPath
     * @param year          1990/01/01-0000
     * @param typeAlias
     * @param lonAlias
     * @param latAlias
     * @param latOffset
     * @param lonOffset
     * @param sliceNum  文件分片个数
     */
    public static void parse_3d_static_bigdata(String NC_PATH,String rootPath,String year,String typeAlias,
                                       String lonAlias,String latAlias,Integer latOffset,Integer lonOffset,Integer hourOffset,
                                               Integer sliceNum){
        NetcdfFile ncfile = null;
        String long_name = "",units = "";

        try {
            ncfile = NetcdfFile.open(NC_PATH);
            Variable typeBean = ncfile.findVariable(typeAlias);

            Variable lonBean = ncfile.findVariable(lonAlias);
            Variable latBean = ncfile.findVariable(latAlias);
            int[] typeShape = typeBean.getShape();

            ArrayFloat.D1 lonArray = (ArrayFloat.D1) lonBean.read();
            ArrayFloat.D1 latArray = (ArrayFloat.D1) latBean.read();


            Array firstTypeData = typeBean.read(new int[3], new int[] {1, typeShape[1], typeShape[2]}).reduce(0);
            Index firstTypeIndex = firstTypeData.getIndex();
            double minVal = firstTypeData.getDouble(firstTypeIndex.set(0,0))
                    ,maxVal = firstTypeData.getDouble(firstTypeIndex.set(0,0));

            if (null != typeBean) {
                int[] origin = new int[3];

                int[] size = new int[] {1, typeShape[1], typeShape[2]};
                for (int i = 0; i < typeShape[0]; i = i+hourOffset) { // typeShape[0]
                    origin[0] = i;
                    Array typeData = typeBean.read(origin, size).reduce(0);
                    Index typeIndex = typeData.getIndex();

                    String endday = DateUtil.getEndtimeStr(year,i,"yyyy/MM/dd-HHmm");
//                    AnsiConsole.out.println( Ansi.ansi().fg(Ansi.Color.GREEN).a("开始处理【"+endday+"】数据...").reset() );

                    for(int l = 0;l < sliceNum; l++){
                        java.util.ArrayList featureArrs = new ArrayList<>();
                        Map<String, Object> jsonMap = new HashMap<>();

                        int jiange = typeShape[1]/sliceNum;
                        int start = jiange * l;
                        for (int j = start; j < start+jiange; j = j + latOffset) { //维度
                            double latValue = (double)latArray.get(j);
//                            latValue=(double)(Math.round(latValue*1000)/1000);
                            for (int k = 0; k < typeShape[2]; k = k + lonOffset){ //经度
                                double lonValue = (float)lonArray.get(k);
//                                lonValue=(double)(Math.round(lonValue*1000)/1000);
                                float val = typeData.getFloat(typeIndex.set(j,k));
                                if(!Double.isNaN(val) && (val != 32767)){
                                    Map<String, Object> featuremap = new HashMap<>();
                                    Map<String, Object> propertiesmap = new HashMap<>();
                                    Map<String, Object> geometrymap = new HashMap<>();
                                    featuremap.put("type","Feature");
                                    propertiesmap.put("v",val);
                                    featuremap.put("properties",propertiesmap);
                                    geometrymap.put("type","Point");
                                    geometrymap.put("coordinates",new Double[]{lonValue,latValue});
                                    featuremap.put("geometry",geometrymap);
                                    featureArrs.add(featuremap);

                                    if(Double.isNaN(minVal)){
                                        minVal = val;
                                    }
                                    if(Double.isNaN(maxVal)){
                                        maxVal = val;
                                    }
                                    if(minVal > val){
                                        minVal = val;
                                    }
                                    if(maxVal < val){
                                        maxVal = val;
                                    }
                                }

                            }
                        }

                        jsonMap.put("type","FeatureCollection");
                        jsonMap.put("long_name",long_name);
                        jsonMap.put("units",units);
                        jsonMap.put("reftime",endday);
                        jsonMap.put("maxvalue",maxVal);
                        jsonMap.put("minvalue",minVal);
                        jsonMap.put("features",featureArrs);
                        String jsonString = JSONObject.toJSONString(jsonMap);

                        String[] pathAndTime = endday.split("-");
                        String pathName = pathAndTime[0];
                        String timeName = pathAndTime[1];
                        String fullPath = rootPath.endsWith("/")?rootPath +  pathName:rootPath + "/" + pathName;

                        if(sliceNum == 1){
                            JsonUtil.createJsonFile(jsonString, fullPath, timeName);
                        }else {
                            JsonUtil.createJsonFile(jsonString, fullPath, timeName+"_"+l);
                        }
                    }
                }
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        } finally {
            if (null != ncfile)
                try {
                    ncfile.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
        }
    }

    public static void parse_3d_static_www(String NC_PATH,String rootPath,String year,String typeAlias,
                                               Integer latOffset,Integer lonOffset,Integer hourOffset, Integer sliceNum){
        NetcdfFile ncfile = null;
        String long_name = "",units = "";

        try {
            ncfile = NetcdfFile.open(NC_PATH);
            Variable typeBean = ncfile.findVariable(typeAlias);

            int[] typeShape = typeBean.getShape();
            System.out.println(typeShape[1]);
            int startLon = 30;
            int endLon = 115;
            double jiangeLon = 0.083333333333;
            int startLat = -30;
            int endLat = 35;
            double jiangeLat = 0.083333333333;

            if (null != typeBean) {
                int[] origin = new int[3];

                int[] size = new int[] {1, typeShape[1], typeShape[2]};
                for (int i = 0; i < typeShape[0]; i = i+hourOffset) { // typeShape[0]
                    origin[0] = i;
                    Array typeData = typeBean.read(origin, size).reduce(0);
                    Index typeIndex = typeData.getIndex();

                    String endday = DateUtil.getEndtimeStr(year,i,"yyyy/MM/dd-HHmm");
//                    AnsiConsole.out.println( Ansi.ansi().fg(Ansi.Color.GREEN).a("开始处理【"+endday+"】数据...").reset() );

                    double minVal = typeData.getDouble(typeIndex.set(0,0)),
                            maxVal = typeData.getDouble(typeIndex.set(0,0));
                    System.out.println(minVal);
                    for(int l = 0;l < sliceNum; l++){
                        Map<String, Object> jsonMap = new HashMap<>();
                        java.util.ArrayList featureArrs = new ArrayList<>();

                        int jiange = typeShape[1]/sliceNum;
                        int start = jiange * l;
                        for (int j = start; j < start+jiange; j = j + latOffset) { //维度
                            double latValue = startLat + j * jiangeLat;
//                            latValue=(double) (Math.round(latValue*1000)/1000);
                            for (int k = 0; k < typeShape[2]; k = k + lonOffset){ //经度
                                double lonValue = startLon + k * jiangeLon;
//                                lonValue= (double) (Math.round(lonValue*1000)/1000);
                                float val = typeData.getFloat(typeIndex.set(j,k));
                                if(!Double.isNaN(val) && (val != 32767)){
                                    Map<String, Object> featuremap = new HashMap<>();
                                    Map<String, Object> propertiesmap = new HashMap<>();
                                    Map<String, Object> geometrymap = new HashMap<>();
                                    featuremap.put("type","Feature");
                                    propertiesmap.put("v",val);
                                    featuremap.put("properties",propertiesmap);
                                    geometrymap.put("type","Point");
                                    geometrymap.put("coordinates",new Double[]{lonValue,latValue});
                                    featuremap.put("geometry",geometrymap);
                                    featureArrs.add(featuremap);

                                    if(Double.isNaN(minVal)){
                                        minVal = val;
                                    }
                                    if(Double.isNaN(maxVal)){
                                        maxVal = val;
                                    }
                                    if(minVal > val){
                                        minVal = val;
                                    }
                                    if(maxVal < val){
                                        maxVal = val;
                                    }
                                }
                            }
                        }

                        jsonMap.put("type","FeatureCollection");
                        jsonMap.put("long_name",long_name);
                        jsonMap.put("units",units);
                        jsonMap.put("reftime",endday);
                        jsonMap.put("maxvalue",maxVal);
                        jsonMap.put("minvalue",minVal);
                        jsonMap.put("features",featureArrs);
                        String jsonString = JSONObject.toJSONString(jsonMap);

                        String[] pathAndTime = endday.split("-");
                        String pathName = pathAndTime[0];
                        String timeName = pathAndTime[1];
                        String fullPath = rootPath.endsWith("/")? rootPath +  pathName : rootPath + "/" + pathName;

                        if(sliceNum == 1) { // 分为一片的时候不带序号后缀
                            JsonUtil.createJsonFile(jsonString, fullPath, timeName);
                        }else {
                            JsonUtil.createJsonFile(jsonString, fullPath, timeName+"_"+l);
                        }
                    }
                }
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        } finally {
            if (null != ncfile)
                try {
                    ncfile.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
        }
    }

    public static void parse_4d_static(String NC_PATH,String rootPath,String startYear ,String timeAlias,String levAlias,String typeAlias,
                                       String lonAlias,String latAlias,Integer latOffset,Integer lonOffset,Integer hourOffset){
        NetcdfFile ncfile = null;
        String long_name = "",units = "";
        double scale_factor = 0,add_offset=0;

        try {
            ncfile = NetcdfFile.open(NC_PATH);
            Variable typeBean = ncfile.findVariable(typeAlias);

            Variable lonBean = ncfile.findVariable(lonAlias);
            Variable latBean = ncfile.findVariable(latAlias);
            Variable timeBean = ncfile.findVariable(timeAlias);
            Variable levBean = ncfile.findVariable(levAlias);
            int[] uShape = typeBean.getShape();

            ArrayFloat.D1 timeArray = (ArrayFloat.D1) timeBean.read();
            ArrayFloat.D1 lveArray = (ArrayFloat.D1) levBean.read();
            ArrayFloat.D1 lonArray = (ArrayFloat.D1) lonBean.read();
            ArrayFloat.D1 latArray = (ArrayFloat.D1) latBean.read();

            List<Attribute> typeAttributes = typeBean.getAttributes();
            for(Attribute typeAttr : typeAttributes){
                if("long_name".equals(typeAttr.getName())){
                    long_name = typeAttr.getStringValue();
                }
                if("units".equals(typeAttr.getName())){
                    units = typeAttr.getStringValue();
                }
                if("scale_factor".equals(typeAttr.getName())){
                    scale_factor = typeAttr.getValues().getDouble(0);
                }
                if("add_offset".equals(typeAttr.getName())){
                    add_offset = typeAttr.getValues().getDouble(0);
                }
            }

            if (null != typeBean) {
                Array typeData = typeBean.read();
                Index typeIndex = typeData.getIndex();

                for (int i=0; i < uShape[0]; i = i + hourOffset) { //uShape[0]
                    long addHours = (long) timeArray.get(i);
                    String endday = DateUtil.getEndtimeStr(startYear, addHours, "yyyy/MM/dd-HHmm");

//                    AnsiConsole.out.println( Ansi.ansi().fg(Ansi.Color.GREEN).a("开始处理【"+endday+"】数据...").reset() );
                    for (int lNum = 0; lNum < 6; lNum++){ // uShape[1]
                        // 0,100,200,1000,2000,3000米位于实际值的索引
                        int[] realLevIndex = new int[]{0,19,22,32,35,37};
                        int levValue = (int)lveArray.get(realLevIndex[lNum]);
                        Map<String, Object> jsonMap = new HashMap<>();
                        java.util.ArrayList featureArrs = new ArrayList<>();
                        // 求最大，最小值
//                        double minVal = 0.0, maxVal = 0.0;
                        double minVal = typeData.getDouble(typeIndex.set(i,realLevIndex[lNum],0,0)) * scale_factor,
                                maxVal = typeData.getDouble(typeIndex.set(i,realLevIndex[lNum],0,0)) * scale_factor;

                        for (int j = 0; j < uShape[2]; j = j + latOffset) { //维度
                            float latValue = (float) latArray.get(j);
                            for (int k = 0; k < uShape[3]; k = k + lonOffset) { //经度
                                float lonValue = (float) lonArray.get(k);
                                double val = typeData.getDouble(typeIndex.set(i, realLevIndex[lNum], j, k)) * scale_factor;
                                if(!Double.isNaN(val) && (val != 32767 * scale_factor)) {
                                    Map<String, Object> featuremap = new HashMap<>();
                                    Map<String, Object> propertiesmap = new HashMap<>();
                                    Map<String, Object> geometrymap = new HashMap<>();
                                    featuremap.put("type", "Feature");
                                    propertiesmap.put("v", val + add_offset);
                                    featuremap.put("properties", propertiesmap);
                                    geometrymap.put("type", "Point");
                                    geometrymap.put("coordinates", new Float[]{lonValue, latValue});
                                    featuremap.put("geometry", geometrymap);
                                    featureArrs.add(featuremap);

                                    if(Double.isNaN(minVal)){
                                        minVal = val;
                                    }
                                    if(Double.isNaN(maxVal)){
                                        maxVal = val;
                                    }
                                    if (minVal > val) {
                                        minVal = val;
                                    }
                                    if (maxVal < val) {
                                        maxVal = val;
                                    }
                                }
                            }
                        }

                        jsonMap.put("type", "FeatureCollection");
                        jsonMap.put("long_name", long_name);
                        jsonMap.put("units", units);
                        jsonMap.put("reftime", endday);
                        jsonMap.put("maxvalue", maxVal);
                        jsonMap.put("minvalue", minVal);
                        jsonMap.put("features", featureArrs);
                        String jsonString = JSONObject.toJSONString(jsonMap);

                        String[] pathAndTime = endday.split("-");
                        String pathName = pathAndTime[0];
                        String timeName = pathAndTime[1];
                        String fullPath = rootPath.endsWith("/") ? rootPath +"/"+ levValue + pathName : rootPath +"/"+ levValue + "/" + pathName ;

                        JsonUtil.createJsonFile(jsonString, fullPath, timeName);
                    }
                }
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        } finally {
            if (null != ncfile)
                try {
                    ncfile.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
        }
    }

    public static void parseOceanFlow(String NC_PATH,String rootPath,String timeAlias,String levAlias,
                                      String uAlias,String vAlias, String uLonAlias,String uLatAlias,String vLonAlias,String vLatAlias,Integer hourOffset){
        NetcdfFile ncfile = null;
        int nx = 0,ny = 0;
        float lo1 = 0,la1 = 0,lo2 = 0,la2 = 0,dx = 0,dy = 0;
        String u_long_name = "",u_units = "", v_long_name = "",v_units = "";
        double u_scale_factor = 0,v_scale_factor =0;

        try {
            ncfile = NetcdfFile.open(NC_PATH);
            Variable uBean = ncfile.findVariable(uAlias);
            Variable vBean = ncfile.findVariable(vAlias);
            Variable timeBean = ncfile.findVariable(timeAlias);
            Variable levBean = ncfile.findVariable(levAlias);
            Variable lonBean = ncfile.findVariable(uLonAlias);
            Variable latBean = ncfile.findVariable(uLatAlias);
            System.out.println(uBean.getDimensionsString());
            int[] uShape = uBean.getShape();
            int[] vShape = vBean.getShape();

            ArrayFloat.D1 timeArray = (ArrayFloat.D1) timeBean.read();
            ArrayFloat.D1 lveArray = (ArrayFloat.D1) levBean.read();

            List<Attribute> uAttributes = uBean.getAttributes();
            for(Attribute uAttr : uAttributes){
                if("long_name".equals(uAttr.getName())){
                    u_long_name = uAttr.getStringValue();
                }
                if("units".equals(uAttr.getName())){
                    u_units = uAttr.getStringValue();
                }
                if("scale_factor".equals(uAttr.getName())){
                    u_scale_factor = uAttr.getValues().getDouble(0);
                }
            }

            List<Attribute> vAttributes = vBean.getAttributes();
            for(Attribute vAttr : vAttributes){
                if(vAttr.getName().equals("long_name")){
                    v_long_name = vAttr.getStringValue();
                }
                if(vAttr.getName().equals("units")){
                    v_units = vAttr.getStringValue();
                }
                if("scale_factor".equals(vAttr.getName())){
                    v_scale_factor = vAttr.getValues().getDouble(0);
                }
            }

            List<Attribute> lonAttributes = lonBean.getAttributes();
            nx =  lonBean.getShape(0);
            for(Attribute lonAttr : lonAttributes){
                if(lonAttr.getName().equals("actual_range")){
                    lo1 = lonAttr.getValues().getFloat(0);
                    lo2 = lonAttr.getValues().getFloat(1);
                }
            }

            ny =  latBean.getShape(0);
            List<Attribute> latAttributes = latBean.getAttributes();
            for(Attribute latAttr : latAttributes){
                if(latAttr.getName().equals("actual_range")){
                    la1 = latAttr.getValues().getFloat(0);
                    la2 = latAttr.getValues().getFloat(1);
                }
            }

            dx = (lo2 - lo1)/(nx-1);
            dy = (la2 - la1)/(ny-1);

            if (null != vBean && null != uBean) {
                Array uData = uBean.read();
                Array vData = vBean.read();
                Index uIndex = uData.getIndex();
                Index vIndex = vData.getIndex();

                for (int i=0; i < uShape[0]; i = i + hourOffset) { //时间维度
                    long addHours = (long) timeArray.get(i);
                    String endday = DateUtil.getEndtimeStr("1980/01/01-0000", addHours, "yyyy/MM/dd-HHmm");
//                    AnsiConsole.out.println( Ansi.ansi().fg(Ansi.Color.GREEN).a("开始处理【"+endday+"】数据...").reset() );

                    for (int lNum = 0; lNum < 6; lNum++){ // uShape[1]
                        // 0,100,200,1000,2000,3000米位于实际值的索引
                        int[] realLevIndex = new int[]{0,19,22,32,35,37};
                        int levValue = (int)lveArray.get(realLevIndex[lNum]);
                        java.util.ArrayList uArrs = new ArrayList<>();
                        java.util.ArrayList vArrs = new ArrayList<>();
                        for (int j = uShape[2]-1; j > -1; j--) { // 纬度
                            for (int k = 0; k < uShape[3]; k++) { // 经度
                                if((uData.getDouble(uIndex.set(i, realLevIndex[lNum], j, k)) != 32767)){
                                    uArrs.add(uData.getDouble(uIndex.set(i, realLevIndex[lNum], j, k)) * u_scale_factor);
//                                    vArrs.add(vData.getDouble(vIndex.set(i, realLevIndex[lNum], j, k)) * v_scale_factor);
                                }else {
                                    uArrs.add(0);
//                                    vArrs.add(0);
                                }
                            }
                        }

                        for (int j = vShape[2]-1; j > -1; j--) { // 纬度
                            for (int k = 0; k < vShape[3]; k++) { // 经度
                                if((vData.getDouble(vIndex.set(i, realLevIndex[lNum], j, k)) != 32767)){
                                    vArrs.add(vData.getDouble(vIndex.set(i, realLevIndex[lNum], j, k)) * v_scale_factor);
                                }else {
                                    vArrs.add(0);
                                }
                            }
                        }

                        //构造UV JSON对象
                        List<Object> uvlist = new ArrayList<Object>();
                        Map<String, Object> umap = new HashMap<>();
                        Map<String, Object> vmap = new HashMap<>();
                        Map<String, Object> uHeadermap = new HashMap<>();
                        uHeadermap.put("parameterCategory", 2);
                        uHeadermap.put("parameterNumber", 2);
                        uHeadermap.put("parameterNumberName", u_long_name);
                        uHeadermap.put("parameterUnit", u_units);
                        uHeadermap.put("nx", nx);
                        uHeadermap.put("ny", ny);
                        uHeadermap.put("lo1", lo1);
                        uHeadermap.put("la1", la2);
                        uHeadermap.put("lo2", lo2);
                        uHeadermap.put("la2", la2+(la2-la1));
                        uHeadermap.put("dx", dx);
                        uHeadermap.put("dy", dy);
                        uHeadermap.put("reftime", endday);

                        Map<String, Object> vHeadermap = new HashMap<>();
                        vHeadermap.put("parameterCategory", 2);
                        vHeadermap.put("parameterNumber", 3);
                        vHeadermap.put("parameterNumberName", v_long_name);
                        vHeadermap.put("parameterUnit", v_units);
                        vHeadermap.put("nx", nx);
                        vHeadermap.put("ny", ny);
                        vHeadermap.put("lo1", lo1);
                        vHeadermap.put("la1", la2);
                        vHeadermap.put("lo2", lo2);
                        vHeadermap.put("la2", la2+(la2-la1));
                        vHeadermap.put("dx", dx);
                        vHeadermap.put("dy", dy);
                        vHeadermap.put("reftime", endday);

                        umap.put("header", uHeadermap);
                        umap.put("data", uArrs.toArray());
                        vmap.put("header", vHeadermap);
                        vmap.put("data", vArrs.toArray());
                        uvlist.add(umap);
                        uvlist.add(vmap);
                        String jsonString = JSONArray.toJSONString(uvlist);

                        String[] pathAndTime = endday.split("-");
                        String pathName = pathAndTime[0];
                        String timeName = pathAndTime[1];
                        String fullPath = rootPath.endsWith("/") ? rootPath + levValue +"/"+ pathName : rootPath + "/" +levValue+"/"+ pathName;
                        JsonUtil.createJsonFile(jsonString, fullPath, timeName);
                    }
                }
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        } finally {
            if (null != ncfile)
                try {
                    ncfile.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
        }
    }

    /**
     *     NC_PATH : nc的文件路径
     *     fileAlias : 自定义别名
     */
    public static void parseAtomsFlow(String NC_PATH,String rootPath,String timeAlias,String uAlias,
                                      String vAlias,String lonAlias,String latAlias,Integer hourOffset){
        NetcdfFile ncfile = null;
        int nx = 0,ny = 0;
        float lo1 = 0,la1 = 0,lo2 = 0,la2 = 0,dx = 0,dy = 0;
        String u_long_name = "",u_units = "", v_long_name = "",v_units = "";
        double u_scale_factor = 0,v_scale_factor =0;

        try {
            ncfile = NetcdfFile.open(NC_PATH);
            Variable uBean = ncfile.findVariable(uAlias);
            Variable vBean = ncfile.findVariable(vAlias);
            Variable timeBean = ncfile.findVariable(timeAlias);
            Variable lonBean = ncfile.findVariable(lonAlias);
            Variable latBean = ncfile.findVariable(latAlias);
            System.out.println(uBean.getDimensionsString() + vBean.getDimensionsString());
            int[] uShape = uBean.getShape();
            int[] vShape = vBean.getShape();

            ArrayFloat.D1 timeArray = (ArrayFloat.D1) timeBean.read();

            List<Attribute> uAttributes = uBean.getAttributes();
            for(Attribute uAttr : uAttributes){
                if("long_name".equals(uAttr.getName())){
                    u_long_name = uAttr.getStringValue();
                }
                if("units".equals(uAttr.getName())){
                    u_units = uAttr.getStringValue();
                }
                if("scale_factor".equals(uAttr.getName())){
                    u_scale_factor = uAttr.getValues().getDouble(0);
                }
            }

            List<Attribute> vAttributes = vBean.getAttributes();
            for(Attribute vAttr : vAttributes){
                if(vAttr.getName().equals("long_name")){
                    v_long_name = vAttr.getStringValue();
                }
                if(vAttr.getName().equals("units")){
                    v_units = vAttr.getStringValue();
                }
                if("scale_factor".equals(vAttr.getName())){
                    v_scale_factor = vAttr.getValues().getDouble(0);
                }
            }

            List<Attribute> lonAttributes = lonBean.getAttributes();
            nx =  lonBean.getShape(0);
            for(Attribute lonAttr : lonAttributes){
                if(lonAttr.getName().equals("actual_range")){
                    lo1 = lonAttr.getValues().getFloat(0);
                    lo2 = lonAttr.getValues().getFloat(1);
                }
            }

            ny =  latBean.getShape(0);
            List<Attribute> latAttributes = latBean.getAttributes();
            for(Attribute latAttr : latAttributes){
                if(latAttr.getName().equals("actual_range")){
                    la1 = latAttr.getValues().getFloat(0);
                    la2 = latAttr.getValues().getFloat(1);
                }
            }

            dx = (lo2 - lo1)/(nx-1);
            dy = (la2 - la1)/(ny-1);

            if (null != vBean && null != uBean) {
//                int[] origin = new int[]{0, 0, 0};
//                int[] size = new int[]{1, 2, vShape[2]};
//                ArrayShort data2D = (ArrayShort.D2) vBean.read(origin, size).reduce();
//                ArrayShort data1D = (ArrayShort.D1) data2D.reduce();
//                Array data1D = data2D.reduce();
//                int[] data = (int[])data3D.copyTo1DJavaArray();
//                System.out.println(data2D);
//                System.out.println("读取所有：\n" + NCdumpW. printArray(data2D, "v10", null));
                Array uData = uBean.read();
                Array vData = vBean.read();
                Index uIndex = uData.getIndex();
                Index vIndex = vData.getIndex();

                for (int i=0; i < uShape[0]; i = i+hourOffset) { //uShape[0]
                    long addHours = (long)timeArray.get(i);
                    String endday = DateUtil.getEndtimeStr("1980/01/01-0000",addHours,"yyyy/MM/dd-HHmm");
//                    AnsiConsole.out.println( Ansi.ansi().fg(Ansi.Color.GREEN).a("开始处理【"+endday+"】数据...").reset() );

                    java.util.ArrayList uArrs = new ArrayList<>();
                    java.util.ArrayList vArrs = new ArrayList<>();
                    for (int j=0; j < uShape[1]; j++) {
                        for (int k=0; k < uShape[2]; k++){
                            uArrs.add(uData.getDouble(uIndex.set(i,j,k)) * u_scale_factor );
                            vArrs.add(vData.getDouble(vIndex.set(i,j,k)) * v_scale_factor );
                        }
                    }
                    //构造UV JSON对象
                    List<Object> uvlist = new ArrayList<Object>();
                    Map<String, Object> umap = new HashMap<>();
                    Map<String, Object> vmap = new HashMap<>();
                    Map<String, Object> uHeadermap = new HashMap<>();
                    uHeadermap.put("parameterCategory",2);
                    uHeadermap.put("parameterNumber", 2);
                    uHeadermap.put("parameterNumberName", u_long_name);
                    uHeadermap.put("parameterUnit", u_units);
                    uHeadermap.put("nx", nx);
                    uHeadermap.put("ny", ny);
                    uHeadermap.put("lo1", lo1);
                    uHeadermap.put("la1", la2);
                    uHeadermap.put("lo2", lo2);
                    uHeadermap.put("la2", la2+(la2-la1));
                    uHeadermap.put("dx", dx);
                    uHeadermap.put("dy", dy);
                    uHeadermap.put("reftime",endday);

                    Map<String, Object> vHeadermap = new HashMap<>();
                    vHeadermap.put("parameterCategory",2);
                    vHeadermap.put("parameterNumber", 3);
                    vHeadermap.put("parameterNumberName", v_long_name);
                    vHeadermap.put("parameterUnit", v_units);
                    vHeadermap.put("nx", nx);
                    vHeadermap.put("ny", ny);
                    vHeadermap.put("lo1", lo1);
                    vHeadermap.put("la1", la2);
                    vHeadermap.put("lo2", lo2);
                    vHeadermap.put("la2", la2+(la2-la1));
                    vHeadermap.put("dx", dx);
                    vHeadermap.put("dy", dy);
                    vHeadermap.put("reftime",endday);

                    umap.put("header",uHeadermap);
                    umap.put("data",uArrs.toArray());
                    vmap.put("header",vHeadermap);
                    vmap.put("data",vArrs.toArray());
                    uvlist.add(umap);
                    uvlist.add(vmap);
                    String jsonString = JSONArray.toJSONString(uvlist);

                    String[] pathAndTime = endday.split("-");
                    String pathName = pathAndTime[0];
                    String timeName = pathAndTime[1];
                    String fullPath = rootPath.endsWith("/")?rootPath +  pathName:rootPath + "/" + pathName;
                    JsonUtil.createJsonFile(jsonString, fullPath, timeName);
                }
            }
//            System.out.println("time变量维度："+ Arrays.toString(v1.getShape())+"\n dimension："+v1.getDimensionsString());
//            System.out.println(NCdumpW.printArray(lonArray, "lon", null));
        } catch (Exception ioe) {
            ioe.printStackTrace();
        } finally {
            if (null != ncfile)
                try {
                    ncfile.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
        }
    }


    public static void parse_3d_flow_bigdata(String U_NC_PATH, String V_NC_PATH, String rootPath, String startYear, String uAlias,
                                             String vAlias, String lonAlias, String latAlias, Integer latOffset,Integer lonOffset,Integer hourOffset){
        NetcdfFile u_ncfile = null,v_ncfile = null;
        int nx = 0,ny = 0;
        float lo1 = 0,la1 = 0,lo2 = 0,la2 = 0,dx = 0,dy = 0;
        String u_long_name = "Zonal Component of Wind",u_units = "m/s", v_long_name = "Meridional Component of Wind",v_units = "m/s";

        try {
            u_ncfile = NetcdfFile.open(U_NC_PATH);
            v_ncfile = NetcdfFile.open(V_NC_PATH);

            Variable uBean = u_ncfile.findVariable(uAlias);
            Variable vBean = v_ncfile.findVariable(vAlias);

            Variable u_lonBean = u_ncfile.findVariable(lonAlias);
            Variable u_latBean = u_ncfile.findVariable(latAlias);

            Variable v_lonBean = v_ncfile.findVariable(lonAlias);
            Variable v_latBean = v_ncfile.findVariable(latAlias);

            ArrayFloat.D1 lonArray = (ArrayFloat.D1) u_lonBean.read();
            lo1 = lonArray.get(0);
            lo2 = lonArray.get((int)lonArray.getSize()-1);
//            System.out.println(lonArray.get(0) +" -- "+ lonArray.get((int)lonArray.getSize()-1));
            ArrayFloat.D1 latArray = (ArrayFloat.D1) u_latBean.read();
            la1 = latArray.get(0);
            la2 = latArray.get((int)latArray.getSize()-1);

            nx =  v_lonBean.getShape(0);
            ny =  v_latBean.getShape(0);

            dx = (lo2 - lo1)/(nx-1);
            dy = (la2 - la1)/(ny-1);
            int[] uShape = uBean.getShape();

            if (null != vBean && null != uBean) {
                int[] origin = new int[3];

                int[] size = new int[] {1, uShape[1], uShape[2]};
                for (int i = 0; i < uShape[0]; i=i+hourOffset) { // uShape[0]
                    origin[0] = i;
                    Array vData = vBean.read(origin, size).reduce(0);
                    Array uData = uBean.read(origin, size).reduce(0);
                    Index vIndex = vData.getIndex();
                    Index uIndex = uData.getIndex();

                    String endday = DateUtil.getEndtimeStr(startYear,i,"yyyy/MM/dd-HHmm");
//                    AnsiConsole.out.println( Ansi.ansi().fg(Ansi.Color.GREEN).a("开始处理【"+endday+"】数据...").reset() );

                        java.util.ArrayList uArrs = new ArrayList<>();
                        java.util.ArrayList vArrs = new ArrayList<>();

                        for (int j = 0; j < uShape[1]; j = j + latOffset) { //维度
                            for (int k = 0; k < uShape[2]; k = k + lonOffset) { //经度
                                float uVal = uData.getFloat(uIndex.set(j,k));
                                float vVal = vData.getFloat(vIndex.set(j,k));
                                uArrs.add(uVal);
                                vArrs.add(vVal);
                            }
                        }
                        //构造UV JSON对象
                        List<Object> uvlist = new ArrayList<Object>();
                        Map<String, Object> umap = new HashMap<>();
                        Map<String, Object> vmap = new HashMap<>();
                        Map<String, Object> uHeadermap = new HashMap<>();
                        uHeadermap.put("parameterCategory", 2);
                        uHeadermap.put("parameterNumber", 2);
                        uHeadermap.put("parameterNumberName", u_long_name);
                        uHeadermap.put("parameterUnit", u_units);
                        uHeadermap.put("nx", nx/lonOffset);
                        uHeadermap.put("ny", ny/latOffset);
                        uHeadermap.put("lo1", lo1);
                        uHeadermap.put("la1", la2);
                        uHeadermap.put("lo2", lo2);
                        uHeadermap.put("la2", la2+(la2-la1));
                        uHeadermap.put("dx", dx*lonOffset);
                        uHeadermap.put("dy", dy*latOffset);
                        uHeadermap.put("reftime", endday);

                        Map<String, Object> vHeadermap = new HashMap<>();
                        vHeadermap.put("parameterCategory", 2);
                        vHeadermap.put("parameterNumber", 3);
                        vHeadermap.put("parameterNumberName", v_long_name);
                        vHeadermap.put("parameterUnit", v_units);
                        vHeadermap.put("nx", nx/lonOffset);
                        vHeadermap.put("ny", ny/latOffset);
                        vHeadermap.put("lo1", lo1);
                        vHeadermap.put("la1", la2);
                        vHeadermap.put("lo2", lo2);
                        vHeadermap.put("la2", la2+(la2-la1));
                        vHeadermap.put("dx", dx*lonOffset);
                        vHeadermap.put("dy", dy*latOffset);
                        vHeadermap.put("reftime", endday);

                        umap.put("header", uHeadermap);
                        umap.put("data", uArrs.toArray());
                        vmap.put("header", vHeadermap);
                        vmap.put("data", vArrs.toArray());
                        uvlist.add(umap);
                        uvlist.add(vmap);
                        String jsonString = JSONArray.toJSONString(uvlist);

                        String[] pathAndTime = endday.split("-");
                        String pathName = pathAndTime[0];
                        String timeName = pathAndTime[1];
                        String fullPath = rootPath.endsWith("/") ? rootPath + pathName : rootPath + "/" + pathName;
                        if( 1 == 1 ){
                            JsonUtil.createJsonFile(jsonString, fullPath, timeName);
                        }else {
                            JsonUtil.createJsonFile(jsonString, fullPath, timeName+"_"+i);
                        }
                    }
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        } finally {
            if (null != u_ncfile || null != v_ncfile)
                try {
                    u_ncfile.close();
                    v_ncfile.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
        }
    }

    public static void  readNc(){
        String filename = "E:\\web_work2\\nc_project\\19800101_ocean_bhd\\198102_atmos_cs.nc";
        NetcdfFile dataFile = null;
        try {
            dataFile = NetcdfFile.open(filename, null);
            // Get the latitude and longitude Variables.
            Variable latVar = dataFile.findVariable("lat");
            Variable lonVar = dataFile.findVariable("lon");

            ArrayFloat.D1 latArray;
            ArrayFloat.D1 lonArray;

            latArray = (ArrayFloat.D1) latVar.read();
            lonArray = (ArrayFloat.D1) lonVar.read();

            System.out.println(NCdumpW.printArray(latArray, "lat", null));
            System.out.println(NCdumpW.printArray(lonArray, "lon", null));

            // The file is closed no matter what by putting inside a try/catch block.
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return;

        }  finally {
            if (dataFile != null)
                try {
                    dataFile.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
        }
        System.out.println("*** SUCCESS reading example file " + filename);
    }
}
