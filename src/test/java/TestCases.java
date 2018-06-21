import com.codeborne.selenide.Configuration;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.junit.Assert.assertEquals;


public class TestCases {
    private static final String BASE_URL_API = "https://api.github.com/search/repositories";
    private static final String BASE_URL = "https://github.com/";

    private static void login(String l, String p) {
        $("#login_field").val(l);
        $("#password").val(p).pressEnter();
    }

    @Before
    public void SetUP() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        Configuration.browser = "chrome";
        open(BASE_URL);
    }


    @Test
    public void testCase1() throws JSONException {
        Response response = given().when().
                get("https://api.github.com/search/repositories?q=selenide&sort=stars");
        assertEquals(200, response.getStatusCode());
        System.out.println(response.getStatusCode());
        List<String> Name = from(response.asString()).get("items.name");
        List<String> Opys = from(response.asString()).get("items.description");
        List<String> language = from(response.asString()).get("items.language");
        List<String> license = from(response.asString()).get("items.license.name");
        List<Integer> star = from(response.asString()).get("items.stargazers_count");
        SetUP();
        $(By.xpath("/html/body/div[1]/header/div/div[2]/div/div/div/div/form/label/input[1]"))
                .setValue("Selenide").pressEnter();


        String namerepos = $(By.xpath("//*[@id=\"js-pjax-container\"]/div/div[1]/div[2]/div/ul/div[1]/div[1]/h3/a/em")).getText();

        String description = $(By.xpath("//*[@id=\"js-pjax-container\"]/div/div[1]/div[2]/div/ul/div[1]/div[1]/p")).getText();

        String language1 = $(By.xpath("//*[@id=\"js-pjax-container\"]/div/div[1]/div[2]/div/ul/div[1]/div[2]")).getText();

        String license1 = $(By.xpath("//*[@id=\"js-pjax-container\"]/div/div[1]/div[2]/div/ul/div[1]/div[1]/div/p[1]")).getText();

        String stars1 = $(By.xpath("//*[@id=\"js-pjax-container\"]/div/div[1]/div[2]/div/ul/div[1]/div[3]/a")).getText();
        if (namerepos.equals(Name.get(0))) {
            System.out.println("Repository equals");
        }
        if (description.equals(Opys.get(0))) {
            System.out.println("Description equals");
        }
        if (language1.equals(language.get(0))) {
            System.out.println("Language equals");
        }

        if (license1.equalsIgnoreCase(license.get(0))) {
            System.out.println("License equals");
        }
        if (Integer.parseInt(stars1) == (star.get(0))) {
            System.out.println("Stars Equals");
        }

        JSONObject jsonResponse = new JSONObject(response.asString());
        String capital = jsonResponse.getString("total_count");
        Assert.assertEquals(capital, "527");
    }

    @Test
    public void TestCase2() {
        Response response = given().when().
                get("https://api.github.com/search/repositories?q=selenide&sort=stars");
        assertEquals(200, response.getStatusCode());
        System.out.println(response.getStatusCode());
        SetUP();
        $(By.name("q")).setValue("Selenid").pressEnter();
        $(".select-menu.select-menu-modal-right").click();
        $(".select-menu-item", 1).click();
        sleep(5000);
    }

    @Test
    public void testCase3() {
        String username = "KraaS1";
        String pass = "6233199717i";
        String repName = "testRepository";
        String json =
                "{\n" +
                        "  \"name\": \"" + repName + "\",\n" +
                        "  \"description\": \"This is your first repository\",\n" +
                        "  \"auto_init\" : true \n" +
                        "}";
        given()
                .auth().preemptive().basic(username, pass)
                .when()
                .contentType("application/json; charset=UTF-8")
                .body(json)
                .post("https://api.github.com/user/repos");
        SetUP();
        open(BASE_URL + "/login");
        login(username, pass);
        open(BASE_URL + "/" + username + "?tab=repositories");
        $("#your-repos-filter").val(username + "/" + repName).pressEnter();
        $("#user-repositories-list .mb-1").click();
        $(".reponav .octicon-gear").click();
        $(".btn-danger", 4).click();
        $(".input-block", 1).val(repName).pressEnter();
        Response comparison = given().when().get("https://api.github.com/repos/"+username+"/"+repName);
        assertEquals(404, comparison.getStatusCode());
        sleep(3000);
    }
}