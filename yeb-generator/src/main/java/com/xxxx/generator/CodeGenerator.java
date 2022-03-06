package com.xxxx.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import freemarker.template.utility.StringUtil;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodeGenerator {

    public static String scanner(String tip){
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + ":");
        System.out.println(help.toString());
        if (scanner.hasNext()){
            String ipt = scanner.next();
            if (!StringUtils.isEmpty(ipt)){
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "!");
    }
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        AutoGenerator autoGenerator = new AutoGenerator();
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("Fuckyou123");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/yeb?characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai");
        autoGenerator.setDataSource(dataSourceConfig);



        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOpen(true); // 代码生成后打开目录
        globalConfig.setOutputDir(projectPath+"/yeb-server/src/main/java");
        globalConfig.setAuthor("HC");
//        globalConfig.setIdType(IdType.ASSIGN_ID);// id 主键策略
//        globalConfig.setDateType(DateType.ONLY_DATE); // 定义生成的实体类中日期类型
        globalConfig.setSwagger2(true);// 开启Swaggers模式
        globalConfig.setServiceName("%sService");
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
        autoGenerator.setGlobalConfig(globalConfig);


        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.xxxx.server");
        packageConfig.setEntity("pojo");
        packageConfig.setMapper("mapper");
        packageConfig.setController("controller");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        autoGenerator.setPackageInfo(packageConfig);


        StrategyConfig strategyConfig = new StrategyConfig();

//        strategyConfig.setInclude("t_admin"); // 生成单表写法
        // strategyConfig.setInclude("user","product"); // 生成多张表写法。生成所有表，不用配置
        strategyConfig.setTablePrefix("t"+"_"); // 去表前缀 t,根据实际情况填写

        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.no_change);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setInclude(scanner("表名，多个英文逗号分隔").split(","));
        strategyConfig.setTablePrefix("t_");

//        List<TableFill> list = new ArrayList<>();
//        TableFill tableFill1 = new TableFill("create_time", FieldFill.INSERT);
//        TableFill tableFill2 = new TableFill("update_time",FieldFill.INSERT_UPDATE);
//        list.add(tableFill1);
//        list.add(tableFill2);

//        strategyConfig.setTableFillList(list);
        autoGenerator.setStrategy(strategyConfig);

        String templatePath = "/templates/mapper.xml.ftl";

        List<FileOutConfig> focList = new ArrayList<>();

        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/yeb-server/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper"
                        + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        TemplateConfig templateConfig = new TemplateConfig();
        autoGenerator.setCfg(cfg);
        templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());

        autoGenerator.execute();
    }
}
