
dependencias:

<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>5.4.10.Final</version>
</dependency>

conectar servidor [WINDOWS] :

cd D:\Entregas\DI_DB\hsqldb-2.5.0\hsqldb
java -cp lib/hsqldb.jar org.hsqldb.Server -database.0 file:data/demo_db -dbname.0 xdb