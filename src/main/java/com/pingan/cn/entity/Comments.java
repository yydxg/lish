package com.pingan.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comments implements Serializable {

    private String say;

    private Date date = new Date();

    @Override
    public String toString() {
        return "Comments{" +
                "say='" + say + '\'' +
                ", date=" + date +
                '}';
    }
}
