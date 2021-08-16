package com.pingan.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Yichan")
@NoArgsConstructor
@AllArgsConstructor
public class Yichan {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String name_en;

    private String date;

    private String longitude;

    private String latitude;

    private String area;

    private String criteria_txt;

    private String category;

    private String category_short;

    private String states_name_en;

    private String region_en;

    private String iso_code;

    private String udnp_code;

    private String transboundary;

    private String formdate;

    private String name_china;

    private String short_description_china;

    private String istentative;

    private String province;

    private String site_scope;

    private String historical_time_start;

    private String historical_time_end;

}
