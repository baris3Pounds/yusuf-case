package com.threepounds.caseproject;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Test {

  public static void main(String[] args) throws IOException, InterruptedException {
    int counter = 113;
    String snowflakeSchemaNamePrefix = "TEST_";
    String protocolNamePrefix = "test ";
    String snowflakeSchemaName =  snowflakeSchemaNamePrefix + counter;
    String protocolName =  protocolNamePrefix + counter;

    OkHttpClient client = new OkHttpClient.Builder()
        .build();
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(mediaType, "{\r\n\t\"id\": \"c714a5af-e628-47cf-95b6-2eb618b09a28\",\r\n\t\"snowflakeSchema\": \""+snowflakeSchemaName+"\",\r\n\t\"version\": \"1.0\",\r\n\t\"name\": \""+protocolName+"\",\r\n\t\"description\": \"Protocol for testing different data types from EWBSS\",\r\n\t\"views\": [\r\n\t\t{\r\n\t\t\t\"snowflakeTable\": \"DATA_TYPES\",\r\n\t\t\t\"name\": \"Data Types\",\r\n\t\t\t\"description\": \"\",\r\n\t\t\t\"fields\": [\r\n\t\t\t\t{\r\n\t\t\t\t\t\"snowflakeColumn\": \"STRING\",\r\n\t\t\t\t\t\"name\": \"String\",\r\n\t\t\t\t\t\"dataType\": \"string\",\r\n\t\t\t\t\t\"mapping\": null\r\n\t\t\t\t},\r\n\t\t\t\t{\r\n\t\t\t\t\t\"snowflakeColumn\": \"NUMBER\",\r\n\t\t\t\t\t\"name\": \"Number\",\r\n\t\t\t\t\t\"dataType\": \"number\",\r\n\t\t\t\t\t\"mapping\": null\r\n\t\t\t\t},\r\n\t\t\t\t{\r\n\t\t\t\t\t\"snowflakeColumn\": \"DATE\",\r\n\t\t\t\t\t\"name\": \"Date\",\r\n\t\t\t\t\t\"dataType\": \"datetime\",\r\n\t\t\t\t\t\"mapping\": null\r\n\t\t\t\t},\r\n\t\t\t\t{\r\n\t\t\t\t\t\"snowflakeColumn\": \"PERCENT\",\r\n\t\t\t\t\t\"name\": \"Percent\",\r\n\t\t\t\t\t\"dataType\": \"number\",\r\n\t\t\t\t\t\"mapping\": null\r\n\t\t\t\t},\r\n\t\t\t\t{\r\n\t\t\t\t\t\"snowflakeColumn\": \"FRACTION\",\r\n\t\t\t\t\t\"name\": \"Fraction\",\r\n\t\t\t\t\t\"dataType\": \"number\",\r\n\t\t\t\t\t\"mapping\": null\r\n\t\t\t\t},\r\n\t\t\t\t{\r\n\t\t\t\t\t\"snowflakeColumn\": \"CURRENCY\",\r\n\t\t\t\t\t\"name\": \"Currency\",\r\n\t\t\t\t\t\"dataType\": \"number\",\r\n\t\t\t\t\t\"mapping\": null\r\n\t\t\t\t},\r\n\t\t\t\t{\r\n\t\t\t\t\t\"snowflakeColumn\": \"GENERAL\",\r\n\t\t\t\t\t\"name\": \"General\",\r\n\t\t\t\t\t\"dataType\": \"number\",\r\n\t\t\t\t\t\"mapping\": null\r\n\t\t\t\t},\r\n\t\t\t\t{\r\n\t\t\t\t\t\"snowflakeColumn\": \"GENERAL2\",\r\n\t\t\t\t\t\"name\": \"General2\",\r\n\t\t\t\t\t\"dataType\": \"string\",\r\n\t\t\t\t\t\"mapping\": null\r\n\t\t\t\t},\r\n\t\t\t\t{\r\n\t\t\t\t\t\"snowflakeColumn\": \"TEST_CASE\",\r\n\t\t\t\t\t\"name\": \"Test case\",\r\n\t\t\t\t\t\"dataType\": \"string\",\r\n\t\t\t\t\t\"mapping\": null\r\n\t\t\t\t}\r\n\t\t\t]\r\n\t\t}\r\n\t]\r\n}");
    Request request = new Request.Builder()
        .url("https://common-sa-dev.d-boron-euw1.idbs-dev.com/protocol-management-service/api/v1/data-2452/protocols?user=robot&comment=testing&message=testingmessage")
        .method("POST", body)
        .addHeader("Content-Type", "application/json")
        .addHeader("X-API-KEY", "emh2dlNReWE3Vm9rU1lzU2pSNVg5YitMSGE4M21CM2NJNGVxU2toZVMvWT0K")
        .build();
    Response response = client.newCall(request).execute();
    counter++;

    for (int i = 0; i < 1000; i++) {
      int index = (counter + i);
      snowflakeSchemaName = snowflakeSchemaNamePrefix + index;
      protocolName = protocolNamePrefix + index;

      RequestBody newbody = RequestBody.create(mediaType, "{\r\n\t\"id\": \"c714a5af-e628-47cf-95b6-2eb618b09a28\",\r\n\t\"snowflakeSchema\": \""+snowflakeSchemaName+"\",\r\n\t\"version\": \"1.0\",\r\n\t\"name\": \""+protocolName+"\",\r\n\t\"description\": \"Protocol for testing different data types from EWBSS\",\r\n\t\"views\": [\r\n\t\t{\r\n\t\t\t\"snowflakeTable\": \"DATA_TYPES\",\r\n\t\t\t\"name\": \"Data Types\",\r\n\t\t\t\"description\": \"\",\r\n\t\t\t\"fields\": [\r\n\t\t\t\t{\r\n\t\t\t\t\t\"snowflakeColumn\": \"STRING\",\r\n\t\t\t\t\t\"name\": \"String\",\r\n\t\t\t\t\t\"dataType\": \"string\",\r\n\t\t\t\t\t\"mapping\": null\r\n\t\t\t\t},\r\n\t\t\t\t{\r\n\t\t\t\t\t\"snowflakeColumn\": \"NUMBER\",\r\n\t\t\t\t\t\"name\": \"Number\",\r\n\t\t\t\t\t\"dataType\": \"number\",\r\n\t\t\t\t\t\"mapping\": null\r\n\t\t\t\t},\r\n\t\t\t\t{\r\n\t\t\t\t\t\"snowflakeColumn\": \"DATE\",\r\n\t\t\t\t\t\"name\": \"Date\",\r\n\t\t\t\t\t\"dataType\": \"datetime\",\r\n\t\t\t\t\t\"mapping\": null\r\n\t\t\t\t},\r\n\t\t\t\t{\r\n\t\t\t\t\t\"snowflakeColumn\": \"PERCENT\",\r\n\t\t\t\t\t\"name\": \"Percent\",\r\n\t\t\t\t\t\"dataType\": \"number\",\r\n\t\t\t\t\t\"mapping\": null\r\n\t\t\t\t},\r\n\t\t\t\t{\r\n\t\t\t\t\t\"snowflakeColumn\": \"FRACTION\",\r\n\t\t\t\t\t\"name\": \"Fraction\",\r\n\t\t\t\t\t\"dataType\": \"number\",\r\n\t\t\t\t\t\"mapping\": null\r\n\t\t\t\t},\r\n\t\t\t\t{\r\n\t\t\t\t\t\"snowflakeColumn\": \"CURRENCY\",\r\n\t\t\t\t\t\"name\": \"Currency\",\r\n\t\t\t\t\t\"dataType\": \"number\",\r\n\t\t\t\t\t\"mapping\": null\r\n\t\t\t\t},\r\n\t\t\t\t{\r\n\t\t\t\t\t\"snowflakeColumn\": \"GENERAL\",\r\n\t\t\t\t\t\"name\": \"General\",\r\n\t\t\t\t\t\"dataType\": \"number\",\r\n\t\t\t\t\t\"mapping\": null\r\n\t\t\t\t},\r\n\t\t\t\t{\r\n\t\t\t\t\t\"snowflakeColumn\": \"GENERAL2\",\r\n\t\t\t\t\t\"name\": \"General2\",\r\n\t\t\t\t\t\"dataType\": \"string\",\r\n\t\t\t\t\t\"mapping\": null\r\n\t\t\t\t},\r\n\t\t\t\t{\r\n\t\t\t\t\t\"snowflakeColumn\": \"TEST_CASE\",\r\n\t\t\t\t\t\"name\": \"Test case\",\r\n\t\t\t\t\t\"dataType\": \"string\",\r\n\t\t\t\t\t\"mapping\": null\r\n\t\t\t\t}\r\n\t\t\t]\r\n\t\t}\r\n\t]\r\n}");
      Request requestLoop = new Request.Builder()
          .url("https://common-sa-dev.d-boron-euw1.idbs-dev.com/protocol-management-service/api/v1/data-2452/protocols?user=robot&comment=testing&message=testingmessage")
          .method("POST", newbody)
          .addHeader("Content-Type", "application/json")
          .addHeader("X-API-KEY", "emh2dlNReWE3Vm9rU1lzU2pSNVg5YitMSGE4M21CM2NJNGVxU2toZVMvWT0K")
          .build();
      Response responseLoop = client.newCall(requestLoop).execute();
      System.out.println(responseLoop);
      Thread.sleep(500);
    }
  }
}
