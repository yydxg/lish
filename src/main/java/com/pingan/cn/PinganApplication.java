package com.pingan.cn;

import com.pingan.cn.controller.CommonController;
import com.pingan.cn.entity.Common;
import com.pingan.cn.netcdf.ParseService;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@EnableSwagger2
@SpringBootApplication
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@ComponentScan(basePackages = {"com.pingan.cn.*","com.pingan.cn.supermap" })
//@ComponentScan(basePackages = {"com.pingan.cn.*","com.pingan.cn.ningbomap.*","com.pingan.cn.guanqu" })
@ComponentScan(basePackages = {"com.pingan.cn.ningbomap.*" })
@Component
public class PinganApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PinganApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(PinganApplication.class);
	}

	/**
	 *  启动java -jar 程序包
	 */
	/*public static void main(String[] args) {
		AnsiConsole.systemInstall();
		AnsiConsole.out.println( Ansi.ansi().fg(Ansi.Color.GREEN).a("【system input】:\n\t"+Arrays.toString(args)).reset() );
		Map<String,String> argsMap = new HashMap<>();
		for (String item: args) {
			argsMap.put(item.split("=")[0],item.split("=")[1]);
		}
		String FUN_NAME = argsMap.get("funName");
		switch (FUN_NAME){
			case "parse_atoms_nc":
				parse_atoms_nc(argsMap);
				break;
			case "parse_ocean_nc":
				parse_ocean_nc(argsMap);
				break;
			case "parse_3d_static":
				parse_3d_static(argsMap);
				break;
			case "parse_4d_static":
				parse_4d_static(argsMap);
			default:

				break;
		}

		AnsiConsole.systemUninstall();
	}*/

	public static void parse_3d_static(Map<String,String> argsMap){

	}

	public static void parse_4d_static(Map<String,String> argsMap){

	}

	public static void parse_atoms_nc(Map<String,String> argsMap){
		String NC_PATH = argsMap.get("-ncPath");
		String rootPath = argsMap.get("-rootPath");
		String timeAlias = "time";
		String uAlias = "u10";
		String vAlias = "v10";
		String lonAlias = "lon";
		String latAlias = "lat";
		if( null == NC_PATH || null == rootPath){
			System.out.println(Ansi.ansi().render("@|red 【error】入参有误，参考：\n|@ @|green World|@").reset());
		}else {
//			ParseService.parseAtomsFlow(NC_PATH,rootPath,timeAlias,uAlias,vAlias,lonAlias,latAlias);
		}
	}

	public static void parse_ocean_nc(Map<String,String> argsMap){
//		String NC_PATH = "E:/web_work2/nc_project/bigdata/ocean_bhd/1980/19800101_ocean_bhd.nc";
//		String rootPath = "E:/web_work2/nc_project/ncjson/ocean/flow/"; // static
//		String fileAlias = "ocean_bhd";
		String NC_PATH = argsMap.get("-ncPath");
		String rootPath = argsMap.get("-rootPath");
		String timeAlias = "time";
		String levAlias = "lev";
		String uAlias = "u";
		String vAlias = "v";
		String uLonAlias = "lon_u";
		String uLatAlias = "lat_u";
		String vLonAlias = "lon_v";
		String vLatAlias = "lat_v";
		if( null == NC_PATH || null == rootPath){
			System.out.println(Ansi.ansi().render("@|red 【error】入参有误，参考：\n|@ @|green " +
					"java -jar xxx.jar -=parse_ocean_nc -ncPath=|@").reset());
		}else {
//			ParseService.parseOceanFlow(NC_PATH,rootPath,timeAlias,levAlias,uAlias,vAlias,uLonAlias,uLatAlias,vLonAlias,vLatAlias);
		}
	}
}
