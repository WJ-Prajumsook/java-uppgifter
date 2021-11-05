package org.wj.prajumsook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.wj.prajumsook.model.Account;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class StepDefinitions {

    private List<Account> accountList;
    private Account accountResponse;
    private Account account;
    private String baseUrl = "http://localhost:9090/api/account/";
    private Client client = ClientBuilder.newClient();
    private ObjectMapper objectMapper = new ObjectMapper();
    private String accountId;

    // Create account
    @Given("I want to create a new account")
    public void i_want_to_create_a_new_account() {
        account = new Account()
                .setId("cucumber_test_account")
                .setBalance("123.000")
                .setAccountName("Cucumber Account");
    }
    @When("I create a new account")
    public void i_create_a_new_account() throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(account);

        Response response = client.target(baseUrl).request().post(Entity.json(json));
        String accountJson = response.readEntity(String.class);
        accountResponse = objectMapper.readValue(accountJson, Account.class);
    }
    @Then("My account should be created")
    public void my_account_should_be_created() {
        assertNotNull(accountResponse);
    }

    // List account
    @Given("I want to view all my account")
    public void i_want_to_view_all_my_account() {
        accountList = new ArrayList<>();
    }
    @When("I view Account tab in Web page")
    public void i_view_account_tab_in_web_page() throws JsonProcessingException {
        Response response = client.target(baseUrl).request().get();
        String accountJson = response.readEntity(String.class);
        accountList = objectMapper.readValue(accountJson, new TypeReference<List<Account>>() {});
    }
    @Then("I should see all my account")
    public void i_should_see_all_my_account() {
        assertTrue(accountList.size() > 0);
    }

    // View account
    @Given("I want to view one of my account")
    public void i_want_to_view_one_of_my_account() {
        accountId = "cucumber_test_account";
    }
    @When("I request to view an account with account id")
    public void i_request_to_view_an_account_with_account_id() throws JsonProcessingException {
        String url = baseUrl + "/" + accountId;
        Response response = client.target(url).request().get();
        String accountJson = response.readEntity(String.class);
        accountResponse = objectMapper.readValue(accountJson, Account.class);
    }
    @Then("I should see my account")
    public void i_should_see_my_account() {
        assertTrue(accountId.equalsIgnoreCase(accountResponse.getId()));
    }

}
