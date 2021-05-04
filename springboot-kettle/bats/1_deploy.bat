
:: 私服 deploy kettle-core.jar包
CALL mvn deploy:deploy-file -Dfile=kettle-core-7.1.0.0-12.jar  -DgroupId=pentaho-kettle  -DartifactId=kettle-core  -Dversion=7.1.0.0-12  -Dpackaging=jar -Durl=http://192.168.1.132/nexus/content/repositories/Third/ -DrepositoryId=服务ID

:: 私服 deploy kettle-engine.jar包
CALL mvn deploy:deploy-file -Dfile=kettle-engine-7.1.0.0-12.jar  -DgroupId=pentaho-kettle  -DartifactId=kettle-engine  -Dversion=7.1.0.0-12  -Dpackaging=jar  -Durl=http://192.168.1.132/nexus/content/repositories/Third/ -DrepositoryId=服务ID

:: 私服 deploy metastore.jar包
CALL mvn deploy:deploy-file -Dfile=metastore-7.1.0.0-12.jar  -DgroupId=pentaho-kettle  -DartifactId=metastore  -Dversion=7.1.0.0-12  -Dpackaging=jar  -Durl=http://192.168.1.132/nexus/content/repositories/Third/ -DrepositoryId=服务ID

pause