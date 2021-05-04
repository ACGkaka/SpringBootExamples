package com.kettle.demo.utils;

import org.apache.commons.io.FileUtils;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.steps.insertupdate.InsertUpdateMeta;
import org.pentaho.di.trans.steps.tableinput.TableInputMeta;
import org.springframework.util.ResourceUtils;

import java.io.File;

/**
 * <p> @Title KettleReadUtils
 * <p> @Description Kettle写入工具包
 *
 * @author ACGkaka
 * @date 2021/4/8 10:50
 */
public class KettleWriteUtils {

    /**
     * 数据库连接信息,适用于DatabaseMeta其中 一个构造器DatabaseMeta(String xml)
     */
    private static final String DATABASE_XML_1 =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<connection>" +
                    "<name>demo</name>" +
                    "<server>127.0.0.1</server>" +
                    "<type>MYSQL</type>" +
                    "<access>Native</access>" +
                    "<database>test</database>" +
                    "<port>3306</port>" +
                    "<username>root</username>" +
                    "<password>56215487</password>" +
                    "</connection>";
    private static final String DATABASE_XML_2 =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<connection>" +
                    "<name>utils</name>" +
                    "<server>127.0.0.1</server>" +
                    "<type>MYSQL</type>" +
                    "<access>Native</access>" +
                    "<database>test</database>" +
                    "<port>3306</port>" +
                    "<username>root</username>" +
                    "<password>56215487</password>" +
                    "</connection>";
    /**
     * 创建ktr文件
     *
     * @param args args
     */
    public static void main(String[] args) {
        try {
            KettleEnvironment.init();
            KettleWriteUtils kettleWriteUtils = new KettleWriteUtils();
            TransMeta transMeta = kettleWriteUtils.generateMyOwnTrans();
            String transXml = transMeta.getXML();
            String fileName = "update_insert_Trans.ktr";
            String filePath = ResourceUtils.getURL("classpath:etl").getPath() + File.separator + fileName;
            File file = new File(filePath);
            FileUtils.writeStringToFile(file, transXml, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }

    /**
     * 生成一个转化,把一个数据库中的数据转移到另一个数据库中,只有两个步骤,第一个是表输入,第二个是表插入与更新操作
     *
     * @return 元数据
     * @throws KettleXMLException 生成XML异常
     */
    private TransMeta generateMyOwnTrans() throws KettleXMLException {
        TransMeta transMeta = new TransMeta();
        //设置转化的名称
        transMeta.setName("insert_update");
        //添加转换的数据库连接
        DatabaseMeta databaseMeta1 = new DatabaseMeta(DATABASE_XML_1);
        transMeta.addDatabase(databaseMeta1);
        DatabaseMeta databaseMeta2 = new DatabaseMeta(DATABASE_XML_2);
        transMeta.addDatabase(databaseMeta2);

        //registry是给每个步骤生成一个标识Id用
        PluginRegistry registry = PluginRegistry.getInstance();
        //第一个表输入步骤(TableInputMeta)
        TableInputMeta tableInput = new TableInputMeta();
        String tableInputPluginId = registry.getPluginId(StepPluginType.class, tableInput);
        //给表输入添加一个DatabaseMeta连接数据库
        DatabaseMeta db1 = transMeta.findDatabase("db1");
        tableInput.setDatabaseMeta(db1);
        String sql = "SELECT * FROM t_test1";
        tableInput.setSQL(sql);
        //添加TableInputMeta到转换中
        StepMeta tableInputMetaStep = new StepMeta(tableInputPluginId, "table input", tableInput);
        //给步骤添加在spoon工具中的显示位置
        tableInputMetaStep.setDraw(true);
        tableInputMetaStep.setLocation(100, 100);
        transMeta.addStep(tableInputMetaStep);

        //第二个步骤插入与更新
        InsertUpdateMeta insertUpdateMeta = new InsertUpdateMeta();
        String insertUpdateMetaPluginId = registry.getPluginId(StepPluginType.class, insertUpdateMeta);
        //添加数据库连接
        DatabaseMeta db2 = transMeta.findDatabase("db2");
        insertUpdateMeta.setDatabaseMeta(db2);
        //设置操作的表
        insertUpdateMeta.setTableName("t_test2");
        //设置用来查询的关键字
        insertUpdateMeta.setKeyLookup(new String[]{"id"});
        insertUpdateMeta.setKeyStream(new String[]{"id"});
        insertUpdateMeta.setKeyStream2(new String[]{""});
        insertUpdateMeta.setKeyCondition(new String[]{"="});
        //设置要更新的字段
        String[] updatelookup = {"id","name"} ;
        String[] updateStream = {"id","name"} ;
        Boolean[] updateOrNot = {false,true};
        insertUpdateMeta.setUpdateLookup(updatelookup);
        insertUpdateMeta.setUpdateStream(updateStream);
        insertUpdateMeta.setUpdate(updateOrNot);
        //添加步骤到转换中
        StepMeta insertUpdateStep = new StepMeta(insertUpdateMetaPluginId, "insert_update", insertUpdateMeta);
        insertUpdateStep.setDraw(true);
        insertUpdateStep.setLocation(250, 100);
        transMeta.addStep(insertUpdateStep);
        //添加hop把两个步骤关联起来
        transMeta.addTransHop(new TransHopMeta(tableInputMetaStep, insertUpdateStep));
        return transMeta;
    }
}