# springboot-easypoi

> **EasyPOI** 是一款开源的基于`Java`语言的`Excel操作工具`。
> * 官方文档1：[http://easypoi.mydoc.io/](http://easypoi.mydoc.io/)
> * 官方文档2：[http://doc.wupaas.com/docs/easypoi/easypoi-1c0u6ksp2r091](http://doc.wupaas.com/docs/easypoi/easypoi-1c0u6ksp2r091)
> * 官方源码：[https://gitee.com/lemur/easypoi.git](https://gitee.com/lemur/easypoi.git)
> * 官方测试案例：[https://gitee.com/lemur/easypoi-test.git](https://gitee.com/lemur/easypoi-test.git)

## **1.导入Excel**
* 官方Demo：
  [https://github.com/alibaba/easyexcel/blob/master/src/test/java/com/alibaba/easyexcel/test/demo/read/ReadTest.java](https://github.com/alibaba/easyexcel/blob/master/src/test/java/com/alibaba/easyexcel/test/demo/read/ReadTest.java)
```java
    /**
     * 导入Excel
     */
    public List<User> importExcel(MultipartFile file) throws Exception {
        // 导入
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        return ExcelImportUtil.importExcel(file.getInputStream(), User.class, params);
    }
```

**导入文件：**
<img src="https://img-blog.csdnimg.cn/20210610164423223.png" width="60%" />


**导入结果：**
<img src="https://img-blog.csdnimg.cn/20210610164446580.png" width="60%" />


## **2.简单导出Excel**
```java
    /**
     * 注解导出
     */
    public void exportExcel(HttpServletResponse response) throws IOException {
        // 导出
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode("用户-导出.xls", "utf-8"));
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户数据", "sheet1"), User.class, list);
        workbook.write(response.getOutputStream());
    }
```

**导出结果：**
<img src="https://img-blog.csdnimg.cn/20210610164423223.png" width="60%" />

## **3.模板导出Excel**
```java
    /**
     * 模板导出
     */
    public void exportByTemplate(HttpServletResponse response) throws IOException {
        // 模板导出
        String templatePath = "/excel/export-template.xlsx";
        InputStream templateInputStream = this.getClass().getResourceAsStream(templatePath);
        // HSSFWorkbook 扩展名xls XSSFWorkbook 扩展名xlsx
        Workbook templateWorkbook = new XSSFWorkbook(templateInputStream);
        TemplateExportParams params = new TemplateExportParams("用户数据", 1);
        params.setTemplateWb(templateWorkbook);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);

        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode("用户-模板导出.xls", "utf-8"));
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        workbook.write(response.getOutputStream());
    }
```

**文件位置：**
<img src="https://img-blog.csdnimg.cn/20210610164551281.png" width="60%" />


**模板文件：**
<img src="https://img-blog.csdnimg.cn/20210610164643650.png" width="60%" />


**导出结果：**
<img src="https://img-blog.csdnimg.cn/20210610164753409.png" width="60%" />