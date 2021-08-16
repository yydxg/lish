package com.pingan.cn.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Movie implements Serializable {

    private String name;
    private String director;
    private MovieType type;
    private String content;

    private Long  commentNum;
}
