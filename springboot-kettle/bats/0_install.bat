
:: 本地 install kettle-core.jar包
CALL mvn install:install-file -Dfile=kettle-core-7.1.0.0-12.jar  -DgroupId=pentaho-kettle  -DartifactId=kettle-core  -Dversion=7.1.0.0-12  -Dpackaging=jar 

:: 本地 install kettle-engine.jar包
CALL mvn install:install-file -Dfile=kettle-engine-7.1.0.0-12.jar  -DgroupId=pentaho-kettle  -DartifactId=kettle-engine  -Dversion=7.1.0.0-12  -Dpackaging=jar 

:: 本地 install metastore.jar包
CALL mvn install:install-file -Dfile=metastore-7.1.0.0-12.jar  -DgroupId=pentaho-kettle  -DartifactId=metastore  -Dversion=7.1.0.0-12  -Dpackaging=jar 

pause