package com.db.am.bauhaus.project.steplib;


import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.ResponseBodyExtractionOptions;
import com.jayway.restassured.specification.RequestSpecification;
import net.thucydides.core.steps.ScenarioSteps;
import org.apache.http.HttpStatus;
import com.db.am.bauhaus.project.util.Properties;
import java.util.Objects;
import static com.jayway.restassured.RestAssured.given;

public class RestServices extends ScenarioSteps {


    private Properties prop = new Properties();

    private String api_key = prop.getAPIKey();
    private RequestSpecification getRequest(String endpoint){

        String url = prop.getBaseUri() + endpoint.toLowerCase()+api_key;

        RestAssured.reset();
        return given().relaxedHTTPSValidation().contentType(prop.getContentType()).baseUri(url).log().all();
    }

//    private RequestSpecification getRequest(String endpoint_tag, String endpoint_subtag){
//
//        String url = prop.getBaseUri() + endpoint_tag.toLowerCase()+"/"+endpoint_subtag+api_key;
//
//
//        RestAssured.reset();
//        return given().relaxedHTTPSValidation().contentType(prop.getContentType()).baseUri(url).log().all();
//
//    }

    ResponseBodyExtractionOptions getResponse(String endpoint) {


        //return getRequest(endpoint).when().get().then().statusCode(HttpStatus.SC_OK).extract().body();
        return getRequest(endpoint).when().get().then().statusCode(HttpStatus.SC_BAD_REQUEST).extract().response();


    }

//    ResponseBodyExtractionOptions getResponse(String endpoint_tag, String endpoint_subtag){
//        return getRequest(endpoint_tag,endpoint_subtag).when().get().then().statusCode(HttpStatus.SC_OK).extract().body();
//    }

    boolean isResponseEmpty(ResponseBodyExtractionOptions response){
        return Objects.equals(JsonPath.from(response.asString()), null);
    }

    public String getAPIKeyValue(){

        return prop.getAPIKey();
    }
}
