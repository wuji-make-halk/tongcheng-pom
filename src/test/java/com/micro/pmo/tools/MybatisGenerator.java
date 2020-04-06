package com.micro.pmo.tools;


import java.sql.SQLException;

import com.micro.pmo.tools.utils.DbMetaDataUtils;


public class MybatisGenerator {

    public static void main(String[] args) throws SQLException {
    	new GeneratorBuilder("share", "Share",
                DbMetaDataUtils.getTable("share"), "分享")
                .build(true);
    }
}
