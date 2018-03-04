package api.testrunner;

//import com.jayway.restassured.RestAssured;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

public class StepDefinitions {

	public static RequestSpecification rspec;
	Response response;
	Properties props;
	
	
	@Before
	public void before(){
		loadProps();
		//RestAssured.baseURI = Coalesce(RestAssured.baseURI , props.getProperty("BASE_URI"));
		//RestAssured.baseURI = props.getProperty("BASE_URI");
		RestAssured.baseURI = System.getenv("BASE_URI");
		if(rspec==null)rspec = RestAssured.requestSpecification;
	}
	
	//utility method...to be public
	private static <T> T Coalesce(T param, T param1)
	{
		return (param!=null && param.toString().trim()!="" && param.toString().trim()!="http://localhost")? param : param1;
	}
	
	private void loadProps(){
		try {
			props = new Properties();
			//File file = new File("src/test/resources/config.properties");
			FileInputStream ins = new FileInputStream("src/test/resources/config.properties");
			props.load(ins);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Given("^A request for FixedFees API with all correct headers$")
	public void a_request_for_FixedFees_API_with_all_correct_headers() throws Throwable {
		
		rspec.given().header("x-ottg-caller-application","postman").
		header("x-ottg-im-principal-type","USER_SESSION").header("x-ottg-im-community-id","GCM69516").
		header("x-ottg-caller-application-host","localhost").header("x-ottg-principal-orgid","GC10524412CU").
		header("x-ottg-principal-serviceid","929877").header("Content-Type","application/json").
		header("x-ottg-principal-userid","GCP3O8875F1Y6").header("x-ottg-caller-application-timestamp","27/02/2018").header("Accept-Encoding","gzip,deflate").header("Accept","application/json")
		.header("Host","10.96.82.143:8084").header("Connection","Keep-Alive").header("User-Agent","Apache-HttpClient/4.1.1 (java 1.5)");
	}
	
	@Given("^the request has a valid businessUnitId \"([^\"]*)\"$")
	public void the_request_has_a_valid_businessUnitId(String businessUnitIdVal) throws Throwable {
	    rspec.given().pathParam("businessUnitId", businessUnitIdVal);
	}

	@Given("^pay for value is \"([^\"]*)\"$")
	public void pay_for_value_is(String payForVal) throws Throwable {
	   rspec.given().queryParam("where_payFor", payForVal).log().all();
	}

	@When("^user sends the request to the API$")
	public void user_sends_the_request_to_the_API() throws Throwable {
		
		//Response response = RestAssured.when().get("/accounting/businessUnits/GC15423437YQ/fixedFeesSELF?where_payFor=SELF");
		response = rspec.get("/accounting/businessUnits/{businessUnitId}/fixedFees");
		System.out.println(response.asString());
		
	}

	@Then("^user retrieves a success response from the API$")
	public void user_retrieves_a_success_response_from_the_API() throws Throwable {
		response.contentType().equals("application/json");
	}

	@Then("^response contains fixed fees information associated to the businessunit ID$")
	public void response_contains_fixed_fees_information_associated_to_the_businessunit_ID() throws Throwable {
	    response.body().path("fixedFeeName[0]", "TG_ANNUAL_FEE_SELF");
	}
	
	@Given("^A request for TradingPartnerRelations API with all correct headers$")
	public void a_request_for_TradingPartnerRelations_API_with_all_correct_headers() throws Throwable {
		
		rspec = RestAssured.given().header("x-ottg-caller-application","postman").
				header("x-ottg-im-principal-type","USER_SESSION").header("x-ottg-im-community-id","GCM69516").
				header("x-ottg-caller-application-host","localhost").header("x-ottg-principal-orgid","GC10524412CU").
				header("x-ottg-principal-serviceid","929877").header("Content-Type","application/json").
				header("x-ottg-principal-userid","GCP3O8875F1Y6").header("x-ottg-caller-application-timestamp","27/02/2018").header("Accept-Encoding","gzip,deflate").header("Accept","application/json")
				.header("Host","qtotcoa.qa.gxsonline.net:8080").header("Connection","Keep-Alive").header("User-Agent","Apache-HttpClient/4.1.1 (java 1.5)");
	}

	@Given("^the request has all valid Trading Partner Relationship Details$")
	public void the_request_has_all_valid_Trading_Partner_Relationship_Details() throws Throwable {
	    JSONObject json = new JSONObject();
	    json.put("transactingEntity", "GC00000101M1");
	    json.put("partnerEntity", "GC00000102M1");
	    json.put("transactingServiceMap", "dm");
	    json.put("partnerServiceMap", "dm");
	    json.put("deletionDate", "");
	    json.put("billableEntity", "GC00000101M1");
	    
	    rspec.body(json.toString());
	}

	@When("^user sends the request to Trading Partner Relations API$")
	public void user_sends_the_request_to_Trading_Partner_Relations_API() throws Throwable {
	    response = rspec.put("/accounting/tradingPartnerRelations");
	    System.out.println(response.asString());
	}

	@Then("^user recieves a success response from Trading Partner Relationship API$")
	public void user_recieves_a_success_response_from_Trading_Partner_Relationship_API() throws Throwable {
	   
	}

	@Then("^response body contains transactionEntity related information$")
	public void response_body_contains_transactionEntity_related_information() throws Throwable {
	    
	}
	
	@Given("^A request for Fixed Fees API to assign fixed fees for BU with all correct headers$")
	public void a_request_for_Fixed_Fees_API_to_assign_fixed_fees_for_BU_with_all_correct_headers() throws Throwable {
		
		rspec = RestAssured.given().header("x-ottg-caller-application","postman").
		header("x-ottg-im-principal-type","USER_SESSION").header("x-ottg-im-community-id","GCM69516").
		header("x-ottg-caller-application-host","localhost").header("x-ottg-principal-orgid","GC10524412CU").
		header("x-ottg-principal-serviceid","929877").header("Content-Type","application/json").
		header("x-ottg-principal-userid","GCP3O8875F1Y6").header("x-ottg-caller-application-timestamp","1/03/2018").header("Accept-Encoding","gzip,deflate").header("Accept","application/json")
		.header("Host","10.96.82.143:8084").header("Connection","Keep-Alive").header("User-Agent","Apache-HttpClient/4.1.1 (java 1.5)");
	}

	@Given("^the request has all valid Fixed Fees inputs$")
	public void the_request_has_all_valid_Fixed_Fees_inputs() throws Throwable {
		JSONArray jsonArray = new JSONArray();
		JSONObject json = new JSONObject();
	    json.put("buId", "GC00000101M1");
	    json.put("fixedFeeName", "TG_ANNUAL_FEE_SELF");
	    json.put("payingBuId", "GC00000101M1");
	    json.put("billingStartDate", "2017-11-29T05:24:02.546Z");
	    json.put("billingEndDate", "2017-11-29T05:24:02.546Z");
	    json.put("overrideBuSuppression", Integer.parseInt("0"));
	    json.put("serviceMap", "dm");
	    
	    
	    rspec.body(jsonArray.put(json).toString());
	    
	}

	@Given("^assignType \"([^\"]*)\" is specified in the request$")
	public void bu_is_specified_in_the_request(String assignTypeVal) throws Throwable {
	    rspec.given().queryParam("assignType",assignTypeVal).log().all();
	}

	@When("^user posts the those information to Fixed Fees API$")
	public void user_posts_the_those_information_to_Fixed_Fees_API() throws Throwable {
	    response = rspec.post("/accounting/fixedFees");
	    System.out.println(response.asString());
	}

	@Then("^user recieves a success response from API$")
	public void user_recieves_a_success_response_from_API() throws Throwable {
	   
	}

	@Then("^the record is created$")
	public void the_record_is_created() throws Throwable {
	  
	}

}
