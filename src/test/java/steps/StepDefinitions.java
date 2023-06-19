package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.APIUtil;

public class StepDefinitions {

    APIUtil apiUtil;

    public StepDefinitions(){
        apiUtil = new APIUtil();
    }

    @When("I send {string} to retrieve weather data")
    public void iSendToRetrieveWeatherDataUsingAnd(String spec, DataTable params) {
        apiUtil.CreateAPIRequest(spec, params);
    }

    @Then("I receive a valid HTTP response code (\\d+)$")
    public void iReceiveAValidHTTPResponseCode(Integer responseCode) {
        Assert.assertEquals(responseCode, apiUtil.VerifyAPIResponseCode());
    }

    @And("I can verify that the json schema is correct")
    public void iCanVerifyThatTheJsonSchemaIsCorrect() throws Exception {
        apiUtil.VerifyAPIResponseJsonSchema();
    }

    @And("I can verify that the response data is correct")
    public void iCanVerifyThatTheResponseDataIsCorrect() {
        apiUtil.VerifyAPIResponseValue();
    }

    @And("I can verify that the error {string} is correct")
    public void iCanVerifyThatTheErrorIsCorrect(String reason) {
        Assert.assertEquals(reason, apiUtil.VerifyAPIErrorReason());
    }
}
