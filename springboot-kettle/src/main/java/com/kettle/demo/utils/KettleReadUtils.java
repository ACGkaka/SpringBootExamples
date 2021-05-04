package com.kettle.demo.utils;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.springframework.util.ResourceUtils;

import java.io.File;

/**
 * <p> @Title KettleReadUtils
 * <p> @Description Kettle读取工具包
 *
 * @author ACGkaka
 * @date 2021/4/8 10:50
 */
public class KettleReadUtils {


    /**
     * 调用 utils ktr
     *
     * @param path 文件路径
     */
    public static void runKtr(String path) {
        try {
            KettleEnvironment.init();
            EnvUtil.environmentInit();
            TransMeta transMeta = new TransMeta(path);
            Trans trans = new Trans(transMeta);
            trans.execute(null);
            trans.waitUntilFinished();
            if (trans.getErrors() > 0) {
                throw new Exception("Errors during transformation execution!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用 utils job
     *
     * @param paraNames 多个参数名
     * @param paraValues 多个参数值
     * @param jobPath 如： String fName= "D:\\utils\\aaa.kjb";
     */
    public static void runJob(String[] paraNames, String[] paraValues, String jobPath) {
        try {
            KettleEnvironment.init();
            JobMeta jobMeta = new JobMeta(jobPath, null);
            Job job = new Job(null, jobMeta);
            // 向Job 脚本传递参数，脚本中获取参数值：${参数名}
            if (paraNames != null && paraValues != null) {
                for (int i = 0; i < paraNames.length && i < paraValues.length; i++) {
                    job.setVariable(paraNames[i], paraValues[i]);
                }
            }
            job.start();
            job.waitUntilFinished();
            if (job.getErrors() > 0) {
                throw new Exception("Errors during job execution!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        String fileName = "update_insert_Trans.ktr";
        String filePath = ResourceUtils.getURL("classpath:etl").getPath() + File.separator + fileName;
        runKtr(filePath);
    }
}
