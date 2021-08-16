package com.pingan.cn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.http.HeaderElement;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@Entity
@Table(name = "person")
public class Person {
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid2")
	@GeneratedValue(generator = "idGenerator")
	private String id;

	/*@Pattern(regexp = "^(\\+86)*0*((13[0-9])|(14[0-9])||(15[0-9])|(18[0-9]))\\d{8}$",message = "手机号码格式错误")
	@NotBlank(message = "手机号码不能为空")
	@Column(name = "cus_phone", nullable = false)
	private String customerPhone;*/

	@NotBlank(message = "姓名不能为空")
	@Column(name = "name")
	private String name;

	@Enumerated(EnumType.STRING)
	private SexType sex = SexType.MEN;

	@Column(name = "age")
	private int age;

	@Column(name = "card_no")
	private String cardNo;

	/*@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate; //出行日期

	@Column(name = "start_city")
	private String startCity;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	private Date arriveDate; //到达日期

	@Column(name = "arrive_city")
	private String arriveCity;*/

	@NotBlank(message = "当前所在城市不能为空")
	@Column(name = "current_city")
	private String currentCity;

	@Column(name = "lon")
	private double lon;

	@Column(name = "lat")
	private double lat;

	@Enumerated(EnumType.STRING)
	private HealthState healthState = HealthState.YES;

	@JoinColumn(name = "connect_id")
	private String connectId;

}
