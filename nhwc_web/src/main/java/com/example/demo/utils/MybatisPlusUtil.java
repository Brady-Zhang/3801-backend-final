package com.example.demo.utils;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MybatisPlusUtil {
    /** author */
    public static final String AUTHOR = "dd";

    /** name the class */
    /**
     * Entity naming
     */
    public static final String FILE_NAME_ENTITY = "%sEntity";
    /**
     * MAPPER naming
     */
    public static final String FILE_NAME_MAPPER = "%sMapper";
    /**
     * xml naming
     */
    public static final String FILE_NAME_XML = "%sMapper";
    /**
     * Service naming
     */
    public static final String FILE_NAME_SERVICE = "%sService";
    /**
     * ServiceImpl naming
     */
    public static final String FILE_NAME_SERVICE_IMPL = "%sDO";
    /**
     * Controller naming
     */
    public static final String FILE_NAME_CONTROLLER = "%sController";

    /**
     Package naming, can be customized based on the specifics of your project to determine the storage path after generation.
     The default path for 'entity' is parent_directory.entity
     The default path for 'mapper' is parent_directory.mapper
     The default path for 'service' is parent_directory.service
     The default path for 'serviceImpl' is parent_directory.service.impl
     The default path for 'controller' is parent_directory.controller
     */

    /**
     * PARENT naming
     */
    public static final String PACKAGE_NAME_PARENT = "com.example.demo";
    /**
     * Entity naming
     */
    public static final String PACKAGE_NAME_ENTITY = "entity";
    /**
     * MAPPER naming
     */
    public static final String PACKAGE_NAME_MAPPER = "mapper";
    /**
     * xml naming
     */
    public static final String PACKAGE_NAME_XML = "sys";
    /**
     * Service naming
     */
    public static final String PACKAGE_NAME_SERVICE = "service";
    /**
     * ServiceImpl naming
     */
    public static final String PACKAGE_NAME_SERVICE_IMPL = "service.impl";
    /**
     * Controller naming
     */
    public static final String PACKAGE_NAME_CONTROLLER = "control";

    /**
     * read console input
     */
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("please enter" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("please enter the right" + tip + "！");
    }

    /**
     * Run this main method for code generation
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setFileOverride(true);
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setSwagger2(true); // 实体属性 Swagger2 注解
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);

        gc.setEntityName(FILE_NAME_ENTITY);
        gc.setMapperName(FILE_NAME_MAPPER);
        gc.setXmlName(FILE_NAME_XML);
        gc.setServiceName(FILE_NAME_SERVICE);
        gc.setServiceImplName(FILE_NAME_SERVICE_IMPL);
        gc.setControllerName(FILE_NAME_CONTROLLER);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/nhwc");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        //包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(null);
        pc.setParent(PACKAGE_NAME_PARENT);
        pc.setController(PACKAGE_NAME_CONTROLLER);
        pc.setService(PACKAGE_NAME_SERVICE);
        pc.setServiceImpl(PACKAGE_NAME_SERVICE_IMPL);
        pc.setMapper(PACKAGE_NAME_MAPPER);
        pc.setEntity(PACKAGE_NAME_ENTITY);
        pc.setXml(PACKAGE_NAME_XML);
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        // 设置表前缀
        strategy.setTablePrefix("IEMR_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/"
                        + "/" + tableInfo.getMapperName() + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        mpg.execute();
    }
}
