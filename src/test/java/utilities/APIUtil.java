package utilities;

import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class APIUtil {
    private static final Logger log = LoggerFactory.getLogger(APIUtil.class);
    protected RequestSpecification requestSpecification;
    protected RequestSpecBuilder specBuilder = new RequestSpecBuilder();
    Response response;

    public APIUtil() {
        specBuilder.setBaseUri("https://api.open-meteo.com/v1/forecast");
    }

    public void CreateAPIRequest(String spec, DataTable params) {
        requestSpecification = RestAssured.given().spec(specBuilder.build());
        List<Map<String, String>> rows = params.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            requestSpecification.queryParam(columns.get("key"), columns.get("value"));
        }
        switch(spec) {
            case "GET":
                response = requestSpecification.get();
                break;
        }
        log.info(((QueryableRequestSpecification) requestSpecification).getURI());
    }

    public Integer VerifyAPIResponseCode() {
        log.info("Status Code: {}",response.getStatusCode());
        response.prettyPrint();
        return response.getStatusCode();
    }

    public void VerifyAPIResponseJsonSchema() throws Exception {
        URL url = getClass().getClassLoader().getResource("schemaValidator.json");
        File schema = Paths.get(url.toURI()).toFile();
        try {
            response.then()
                    .assertThat()
                    .body(JsonSchemaValidator.matchesJsonSchema(schema));
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void VerifyAPIResponseValue() {
        SoftAssert softAssert = new SoftAssert();
        JSONObject jsonObject = new JSONObject(response.asString());
        softAssert.assertEquals(jsonObject.getBigDecimal("latitude"), ((QueryableRequestSpecification) requestSpecification).getQueryParams().get("latitude"));
        softAssert.assertEquals(jsonObject.getBigDecimal("longitude"), ((QueryableRequestSpecification) requestSpecification).getQueryParams().get("longitude"));
        softAssert.assertAll();
    }

    public String VerifyAPIErrorReason() {
        JSONObject jsonObject = new JSONObject(response.asString());
        return jsonObject.getString("reason");
    }

}
