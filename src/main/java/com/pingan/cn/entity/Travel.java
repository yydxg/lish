package com.pingan.cn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "travel")
public class Travel {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "p_id")
    private String personId;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "lon")
    private double lon;

    @Column(name = "lat")
    private double lat;

    @Column(name = "play")
    private String play;

    @Column(name = "stay_hour")
    private double stay;//逗留时间
}
