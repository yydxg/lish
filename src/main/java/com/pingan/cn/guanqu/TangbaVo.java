package com.pingan.cn.guanqu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TangbaVo extends Tangba{
    private Shuiwei shuiwei;
}
