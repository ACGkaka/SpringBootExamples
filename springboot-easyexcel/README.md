# springboot-easyexcel

> **EasyExcel**，是一款阿里开源的基于`Java`语言的`Excel操作工具`。
> * 官网：[https://www.yuque.com/easyexcel](https://www.yuque.com/easyexcel)
> * 官方文档：[https://www.yuque.com/easyexcel/doc/easyexcel](https://www.yuque.com/easyexcel/doc/easyexcel)
> * Github：[https://github.com/alibaba/easyexcel](https://github.com/alibaba/easyexcel)

## **1.导入Excel**
* 官方Demo：
  [https://github.com/alibaba/easyexcel/blob/master/src/test/java/com/alibaba/easyexcel/test/demo/read/ReadTest.java](https://github.com/alibaba/easyexcel/blob/master/src/test/java/com/alibaba/easyexcel/test/demo/read/ReadTest.java)
```java
    /**
     * 最简单的读
     * <p>1. 创建excel对应的实体对象
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器
     * <p>3. 直接读即可
     */
    public List<User> importExcel(MultipartFile file) throws IOException {
        // 写法1：
        byte[] bytes = file.getBytes();
        InputStream inputStream = file.getInputStream();
        /// 不获取读取到的数据
//        EasyExcel.read(inputStream, User.class, new UserListener()).sheet().doRead();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        List<User> users = EasyExcel.read(inputStream, User.class, new UserListener()).sheet().doReadSync();

        // 写法2：
        inputStream = new ByteArrayInputStream(bytes);
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(inputStream, User.class, new UserListener()).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
        return users;
    }
```

**导入文件：**
<img src="https://img-blog.csdnimg.cn/2021061011054850.png" width="60%" />


**导入结果：**
<img src="https://img-blog.csdnimg.cn/20210610110639872.png" width="60%" />


## **2.简单导出Excel**
```java
    /**
     * 最简单的写
     * <p>1. 创建excel对应的实体对象
     * <p>2. 直接写即可
     */
    public void exportExcel(HttpServletResponse response) throws IOException {
        // 写法1
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode("用户-导出.xlsx", "utf-8"));
        EasyExcel.write(response.getOutputStream(), User.class).sheet("用户").doWrite(list);

        // 写法2
//        ExcelWriter excelWriter = null;
//        try {
//            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
//            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode("用户-导出.xlsx", "utf-8"));
//            excelWriter = EasyExcel.write(response.getOutputStream(), User.class).build();
//            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
//            excelWriter.write(list, writeSheet);
//        } finally {
//            // 千万别忘记finish 会帮忙关闭流
//            if (excelWriter != null) {
//                excelWriter.finish();
//            }
//        }
    }
```

**导出结果：**
<img src="https://img-blog.csdnimg.cn/20210610110222742.png" width="60%" />

## **3.模板导出Excel**
```java
 /**
     * 模板导出（失败了会返回一个有部分数据的Excel）
     */
    public void exportByTemplate(HttpServletResponse response) throws IOException {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        // 填充list 的时候还要注意 模板中{.} 多了个点 表示list
        String templatePath = "/excel/export-template.xlsx";
        InputStream templateInputStream = this.getClass().getResourceAsStream(templatePath);

        // 方案1 一下子全部放到内存里面 并填充
        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode("用户-模板导出.xlsx", "utf-8"));
        EasyExcel.write(response.getOutputStream(), User.class).withTemplate(templateInputStream).sheet().doFill(list);

        // 方案2 分多次 填充 会使用文件缓存（省内存）
//        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
//        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode("用户-模板导出.xlsx", "utf-8"));
//        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), User.class).withTemplate(templateInputStream).build();
//        WriteSheet writeSheet = EasyExcel.writerSheet().build();
//        excelWriter.fill(list1, writeSheet);
//        excelWriter.fill(list2, writeSheet);
//        // 千万别忘记关闭流
//        excelWriter.finish();
    }
```

**文件位置：**
<img src="https://img-blog.csdnimg.cn/20210610110047176.png" width="60%" />


**模板文件：**
<img src="https://img-blog.csdnimg.cn/20210610110133810.png" width="60%" />


**导出结果：**
<img src="https://img-blog.csdnimg.cn/20210610110356301.png" width="60%" />