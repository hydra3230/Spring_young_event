package com.young.zhang.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class MybatisPlusCodeGenerator {
    public static String scanner(String tip){
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("Please input "+ tip +":");
        System.out.println(help.toString());
        if(scanner.hasNext()){
            String ipt = scanner.next();
            if (StringUtils.isNoneBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("Please input correct " + tip + "!");
    }

    public static void main(String[] args){
        //代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setFileOverride(true);
        globalConfig.setOpen(false);
        globalConfig.setAuthor("Young.Zhang");
        globalConfig.setIdType(IdType.AUTO);
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
        globalConfig.setServiceName("%sService");
        globalConfig.setDateType(DateType.ONLY_DATE);
        autoGenerator.setGlobalConfig(globalConfig);

        //数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/big_event?useUnicode=true&characterEncoding=utf-8&serverTimezone=Hongkong");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("Bams!23");
        autoGenerator.setDataSource(dataSourceConfig);

        //包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.young.zhang");
        pc.setMapper("mapper");
        pc.setXml("mapper.xml");
        pc.setEntity("pojo");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");
        autoGenerator.setPackageInfo(pc);

        //策略配置
        StrategyConfig sc = new StrategyConfig();
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        sc.setEntityLombokModel(true);
        sc.setRestControllerStyle(true);
        sc.setControllerMappingHyphenStyle(true);
        sc.setLogicDeleteFieldName("deleted");

        //设置自动填充配置
        TableFill tf = new TableFill("create_time", FieldFill.INSERT);
        TableFill md = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(tf);
        tableFills.add(md);
        sc.setTableFillList(tableFills);

        //乐观锁
        sc.setVersionFieldName("version");
        sc.setInclude(scanner("table name could splited by comma").split(","));
        autoGenerator.setStrategy(sc);

        autoGenerator.execute();

    }
}

