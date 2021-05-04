# kettle-demo

> * **Kettle**是一款国外开源的ETL（数据抽取/转换/加载）工具，现在更名为PDI（Pentaho Data Integeration）。
> * Kettle 本意是水壶的意思，表达了数据流的含义，纯 Java 编写，可以在Window、Linux、Unix上运行，绿色无需安装，数据抽取高效稳定。
> * 支持两种脚本文件，transformation 和 job。

## 部署

1. 下载之后需要将lib中的jar包添加到依赖当中；

2. 如果想将jar包install到本地Maven仓库，参考 `bats/0_install.bat`；

3. 如果想将jar包deploy到私服Maven仓库，参考 `bats/1_deploy.bat`。