# schema-harmonia
This project is written with the goal of enabling the Schema Passivity in our code-base.

The primary goal is of this project is to be a re-usable asset that any Java based project and import as a dependency. However, since we do we have PHP code base that may benefit from this as well. For that reason, I have exposed multiple ways in which this `schema-harmonia.jar` can be consued.


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
## Consuming the `schema-harmonia.jar` as stand-alone jar



