# schema-harmonia
This project is written with the goal of enabling the Schema Passivity in our code-base.

As we are moving more and more toward being a full cloud-native application, we need to be more resilient in the situation where the code and schema may not be in-sync.
There can be many situation which can cause the code and schema to be not in-sync like, missing migration, migration inflight, etc. In any case, we shouldn't create failures and outage. We should try to adopt a more graceful resolution in such cases. This is the main focus of `schema-harmonia`

This project is hoping to be a re-usable asset that any Java based project and import as a dependency to leverage the Schema Passivity it provides.

We do we have PHP code base that may benefit from this as well. For that reason, as a future enhancement we can expose ways in which this `schema-harmonia.jar` can be consumed in a standalone manner and enable non-Java based project to utilize it as well.

test
# Pre-requisite
- JDK 11+
- Maven 3.x

## Consuming in a java project
1. Clone the project `git clone git@github.com:pemba-sherpa-sage/schema-harmonia.git`
2. Install `mvn clean install`
3. Add the below on your `pom.xml` `dependency` section
```xml
    <dependency>
      <groupId>com.intacct</groupId>
      <artifactId>schema-harmonia</artifactId>
      <version>0.0.1-SNAPSHOT</version>
    </dependency>

```

4. Provide database login info in `config.properties` at `/java/main/resource/config.properties`
```
db.url=jdbc:oracle:thin:@localhost:55001:ORCLCDB
db.user=gsmuser
db.password=athena
```
5. Code usage
```java
        :
        :
        //create an instance of SchemaHaronia usign the defautl configuration 
        Harmonia schema = SchemaHarmonia.getInstance();

        //returns a bolean value true if Customers table found; false otherwise
        boolean customer = schema.hasTable("CUSTOMERS");
        
        //returns a boolean value true if City column is found in Customer table; false otherwise
        boolean city = schema.hasColumnName("CUSTOMERS", "CITY");
        :
        :
```
## [Future Work] Consuming the `schema-harmonia.jar` as stand-alone jar
1. Step 1
2. Step 2




