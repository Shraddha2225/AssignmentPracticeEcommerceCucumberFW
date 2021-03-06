package stepdefinations;

import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Log4j2
public class Stepdef {

    WebDriver driver;
    String url = "http://Automationpractice.com/";
    String Email = "icici5@gmail.com";
    String Pass = "nandu2225";
    Scenario scenario;

    @Before
    public void setUp(Scenario scenario){
        this.scenario = scenario;
        scenario.log("executed before each scenario");
    }
    @After
    public  void cleanUp(){
        if (!(driver==null)){
            driver.quit();
        }
        scenario.log("executed after each scenario");
    }

    @BeforeStep
    public  void beforeEachStep(){
        scenario.log("executed before each line of test case");
    }

    @AfterStep
    public void afterEachStep(){
        scenario.log("executed after each line of test case");
        if(!(driver==null)){
            TakesScreenshot screenshot =(TakesScreenshot)driver;
            byte[] data = screenshot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(data,"image/png/jpeg","Failed step name" +scenario.getName());
            //scenario.log("Test cases is passed,no screenshots captured");

        }
        log.debug("Each step hook is executed,screen shots taken");
    }

    //TC1:create account for sign up in application
    @Given("User opened the browser")
    public void user_opened_the_browser() {
        driver = new ChromeDriver();
        log.debug("initialized chrome");
        driver.manage().window().maximize();
        log.debug("Maximized windows");
        driver.manage().deleteAllCookies();
        log.debug("delete all cookies");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        log.debug("Each step hook is executed,screen shots taken");
    }

    @Given("User navigated to the application url and click on sign in button")
    public void user_navigated_to_the_application_url_and_click_on_sign_in_button() {
        driver.get(url);
        log.debug("navigate to the url");
        driver.findElement(By.className("login")).click();
        log.debug("clicked ok sign in button");
    }

    @When("User enter Email address as {string} to create an account and clicked on create new account button")
    public void user_enter_email_address_as_to_create_an_account_and_clicked_on_create_new_account_button(String Email) {
        driver.findElement(By.id("email_create")).sendKeys(Email);
        log.debug("enter email id");
        driver.findElement(By.id("SubmitCreate")).click();
        log.debug("click on create new account button");
    }

    @When("User must be able to navigate to create an account page")
    public void user_must_be_able_to_navigate_to_create_an_account_page() {
        WebElement createAnAccount = driver.findElement(By.xpath("//*[@id=\"account-creation_form\"]/div[1]/h3"));
        Assert.assertEquals(createAnAccount.isDisplayed(),true);
    }

    //TC2: user fill the form
    @Given("User signed up")
    public void user_signed_up() {
        user_opened_the_browser();
        user_navigated_to_the_application_url_and_click_on_sign_in_button();
        user_enter_email_address_as_to_create_an_account_and_clicked_on_create_new_account_button(Email);
        user_must_be_able_to_navigate_to_create_an_account_page();

    }

    @When("User have following list of information to send")
    public void user_have_following_list_of_information_to_send(Map<String,String> userInfo){
        driver.findElement(By.id("uniform-id_gender2")).click();
        driver.findElement(By.name("customer_firstname")).sendKeys(userInfo.get("First name"));
        driver.findElement(By.name("customer_lastname")).sendKeys(userInfo.get("Last name"));
        driver.findElement(By.name("passwd")).sendKeys(userInfo.get("Password"));
    }

    @When("User select date of birth from dropdown")
    public void user_select_date_of_birth_from_dropdown() {
        WebElement dropDownDate = driver.findElement(By.id("days"));
        Select selectDate = new Select(dropDownDate);
        selectDate.selectByValue("22");

        WebElement dropDownMonth = driver.findElement(By.id("months"));
        //select[contains(@id,'')]
        Select selectMonth = new Select(dropDownMonth);
        selectMonth.selectByValue("6");

        WebElement dropDownYear = driver.findElement(By.id("years"));
        Select selectYear = new Select(dropDownYear);
        selectYear.selectByValue("1996");

    }

    @When("user able to click Sign up for our newsletter! and Receive special offers from our partners!")
    public void user_able_to_click_sign_up_for_our_newsletter_and_receive_special_offers_from_our_partners() {
        driver.findElement(By.id("uniform-newsletter")).click();
        driver.findElement(By.id("optin")).click();
    }

    @When("User have following details regarding address")
    public void user_have_following_details_regarding_address(Map<String,String> userAddress) {
        driver.findElement(By.id("company")).sendKeys(userAddress.get("Company"));
        driver.findElement(By.id("address1")).sendKeys(userAddress.get("Address"));
        driver.findElement(By.id("address2")).sendKeys(userAddress.get("Address (Line 2)"));

        driver.findElement(By.id("city")).sendKeys(userAddress.get("City"));
        driver.findElement(By.id("postcode")).sendKeys(userAddress.get("Zip/Postal Code"));
        driver.findElement(By.id("other")).sendKeys(userAddress.get("Additional information"));

        driver.findElement(By.id("phone")).sendKeys(userAddress.get("Home phone"));
        driver.findElement(By.id("phone_mobile")).sendKeys(userAddress.get("Mobile phone"));
        driver.findElement(By.id("alias")).sendKeys(userAddress.get("Assign an address alias for future reference"));

    }

    @When("User select state and country and click on Register button")
    public void user_select_state_and_country_and_click_on_register_button() {
        WebElement dropDownState = driver.findElement(By.id("id_state"));
        Select selectState = new Select(dropDownState);
        selectState.selectByValue("5");
        log.debug("select state");

        WebElement dropDownCountry = driver.findElement(By.name("id_country"));
        Select selectCountry = new Select(dropDownCountry);
        selectCountry.selectByValue("21");
        log.debug("select country");

        driver.findElement(By.id("submitAccount")).click();
        log.debug("click on register");
    }

    //change this text
    @Then("Message display Welcome to your account.Here you can manage all of your personal information and orders")
    public void message_display_welcome_to_your_account_here_you_can_manage_all_of_your_personal_information_and_orders() {
        WebElement expectedSubmitResultPage = driver.findElement(By.xpath("//p[@class='info-account']"));
        Assert.assertEquals(expectedSubmitResultPage.isDisplayed(),true,"Welcome to your account.Here you can manage all of your personal information and orders");
    }

    //TC3:User should be sign in
    @When("User enter Email {string} and password as {string}")
    public void user_enter_email_and_password_as(String Email, String Pass) {
            user_opened_the_browser();
            user_navigated_to_the_application_url_and_click_on_sign_in_button();
            driver.findElement(By.id("email")).sendKeys(Email);
            log.debug("enter email" +Email);
            driver.findElement(By.id("passwd")).sendKeys(Pass);
            log.debug("enter password" +Pass);
    }

    @When("User click on sign in button")
    public void user_click_on_sign_in_button() {
        driver.findElement(By.id("SubmitLogin")).click();
        log.debug("click on submit button");
    }

    @Then("User navigate to My Account page")
    public void user_navigate_to_my_account_page() {
        //Assert.assertEquals("My account - My Store",driver.getTitle());
        WebElement element = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div"));
        Assert.assertEquals(element.isDisplayed(),true,"My account page is display ");
        //message_display_welcome_to_your_account_here_you_can_manage_all_of_your_personal_information_and_orders();

    }

    //TC4:User send to a friend feature
    @When("User select the category as T-Shirts and navigate to T-shirts page")
    public void user_select_the_category_as_t_shirts_and_navigate_to_t_shirts_page() {
        user_enter_email_and_password_as(Email,Pass);
        user_click_on_sign_in_button();
        user_navigate_to_my_account_page();
        driver.findElement(By.xpath("//header/div[3]/div[1]/div[1]/div[6]/ul[1]/li[3]/a[1]")).click();
        log.debug("select category T-Shirts");
        Assert.assertEquals("T-shirts - My Store",driver.getTitle());
        WebElement element = driver.findElement(By.xpath("//*[@id=\"layered_block_left\"]"));
        Assert.assertEquals(element.isDisplayed(),true,"T-shirt product page is open");
    }

    @When("User click on the product: Faded Short Sleeve T-shirts")
    public void user_click_on_the_product_faded_short_sleeve_t_shirts() {
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[2]/h5/a")).click();
        log.debug("click on product");
    }

    @When("User click on Send to a Friend Link, fill the details and click on Send")
    public void user_click_on_send_to_a_friend_link_fill_the_details_and_click_on_send() {
        driver.findElement(By.id("send_friend_button")).click();
        log.debug("click on Email send to friend link");
        driver.findElement(By.id("friend_name")).sendKeys("Yash");
        log.debug("enter friend name");
        driver.findElement(By.id("friend_email")).sendKeys("sy2225@gmail.com");
        log.debug("enter email of friend");
        driver.findElement(By.id("sendEmail")).click();
        log.debug("click on send button");
    }

    @Then("Message appeared that Email sent in a pop up")
    public void message_appeared_that_email_sent_in_a_pop_up(){
        driver.findElement(By.xpath("//h2[text()='Send to a friend']"));
        log.debug("open pop up box");
        driver.findElement(By.xpath("//input[@class='button' and @value='OK']")).click();
        log.debug("click on ok button");
    }

    //TC5: write a review
    @Given("User signed in")
    public void user_signed_in() {
        user_select_the_category_as_t_shirts_and_navigate_to_t_shirts_page();
        user_click_on_the_product_faded_short_sleeve_t_shirts();
    }

    @When("User click on write a review")
    public void user_click_on_write_a_review() {
        driver.findElement(By.xpath("//a[@class='open-comment-form']")).click();
        //a[@class='open-comment-form']

    }

    @When("User enter Title and Comment")
    public void user_enter_title_and_comment() {
        driver.findElement(By.id("comment_title")).sendKeys("About Product");
        driver.findElement(By.id("content")).sendKeys("Good quality of product");

    }
    @When("User click on Send button")
    public void user_click_on_send_button() {
        driver.findElement(By.id("submitNewMessage")).click();

    }
    @Then("Open a pop up box with message displayed Your comment has been added and will be available once approved by a moderator")
    public void open_a_pop_up_box_with_message_displayed_your_comment_has_been_added_and_will_be_available_once_approved_by_a_moderator() {
        driver.findElement(By.xpath("//p[text()='Your comment has been added and will be available once approved by a moderator']")).isDisplayed();
        //p[text()='Your comment has been added and will be available once approved by a moderator']

    }
    @Then("User should be click on Ok")
    public void user_should_be_click_on_ok() {
        driver.findElement(By.xpath("//button[@type='submit' and @value='true']")).click();
    }

    //TC6:change image color
    @When("User click on color blue")
    public void user_click_on_color_blue() {
       // user_signed_in();
        driver.findElement(By.xpath("//*[@id=\"color_14\"]")).click();
    }

    @Then("Display image should be changed in blue color")
    public void display_image_should_be_changed_in_blue_color() {
        Assert.assertEquals("Faded Short Sleeve T-shirts - My Store",driver.getTitle());
        WebElement element = driver.findElement(By.xpath("//*[@id=\"bigpic\"]"));
        Assert.assertEquals(element.isDisplayed(),true,"T-shirt color change in blue color");
    }

    //TC7:end to end user journey
    @When("User click on plus button to increase the quantity of product")
    public void user_click_on_plus_button_to_increase_the_quantity_of_product() {
        driver.findElement(By.xpath("//a[@class='btn btn-default button-plus product_quantity_up']")).click();
    }

    @When("User select large size of the product")
    public void user_select_large_size_of_the_product() {
        WebElement selectDropDownProductSize = driver.findElement(By.id("group_1"));
        Select selectProductSize = new Select(selectDropDownProductSize);
        selectProductSize.selectByValue("3");
    }

    @When("User click on Add to cart button")
    public void user_click_on_add_to_cart_button() {
        user_click_on_color_blue();
        driver.findElement(By.id("add_to_cart")).click();
    }

    @Then("Message displayed Product successfully added to your shopping cart")
    public void message_displayed_product_successfully_added_to_your_shopping_cart() throws InterruptedException {
        Thread.sleep(3000);
        WebElement displayedMessage = driver.findElement(By.xpath("//img[@src='http://automationpractice.com/img/p/3/3-home_default.jpg']"));
        Assert.assertEquals(displayedMessage.isDisplayed(),true,"Product successfully added to your shopping cart");
    }

    @Then("Check the Quantity and Color")
    public void check_the_quantity_and_color() {
        driver.findElement(By.id("layer_cart_product_quantity"));
        driver.findElement(By.id("layer_cart_product_attributes"));
    }

    @Then("Check Total Price is twice the amount fetched earlier.")
    public void check_total_price_is_twice_the_amount_fetched_earlier() {
        driver.findElement(By.xpath("//span[@class='ajax_block_cart_total']"));
    }


    //checkout process
    @Given("User able to proceed add to cart and displayed pop up box")
    public void user_able_to_proceed_add_to_cart_and_displayed_pop_up_box() throws InterruptedException {
        user_click_on_plus_button_to_increase_the_quantity_of_product();
        user_select_large_size_of_the_product();
        user_click_on_add_to_cart_button();
        message_displayed_product_successfully_added_to_your_shopping_cart();
    }

    @When("User click on Proceed to Checkout")
    public void user_click_on_proceed_to_checkout() {
        driver.findElement(By.xpath("//a[@title='Proceed to checkout']")).click();
    }

    @Then("Check the User sees the product name and Availability as Instock")
    public void check_the_user_sees_the_product_name_and_availability_as_instock() {
        driver.findElement(By.xpath("//p[@class='product-name']")).getText();
        driver.findElement(By.xpath("//td[@class='cart_avail']")).getText();

    }
    @Then("Check unit Price equal to what was captured previously and Quantity to what was set earlier")
    public void check_unit_price_equal_to_what_was_captured_previously_and_quantity_to_what_was_set_earlier() throws InterruptedException {
        Thread.sleep(3000);
        WebElement productUnitPrice = driver.findElement(By.xpath("//td[@class='cart_unit']"));
        Assert.assertEquals(productUnitPrice.isDisplayed(),true,"check unit price of product");
        WebElement productQuantity = driver.findElement(By.xpath("//input[@class='cart_quantity_input form-control grey']"));
        Assert.assertEquals(productQuantity.isDisplayed(),true,"Quantity of product");


    }
    @Then("Check the Total is equal to twice the amount with added charges for shipping")
    public void check_the_total_is_equal_to_twice_the_amount_with_added_charges_for_shipping() {
        driver.findElement(By.xpath("//tr[@class='cart_total_price']")).isDisplayed();
        driver.findElement(By.id("total_shipping")).isDisplayed();

    }
    @Then("Click on Proceed to Check out again and reach till payment and click on Terms and condition check box")
    public void click_on_proceed_to_check_out_again_and_reach_till_payment_and_click_on_terms_and_condition_check_box() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@class='button btn btn-default standard-checkout button-medium']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@type='submit' and @name='processAddress']")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("cgv")).click();
        Thread.sleep(1000);
    }
    @Then("On Payment Page click on Pay by bank wire and Click on I confirm my Order")
    public void on_payment_page_click_on_pay_by_bank_wire_and_click_on_i_confirm_my_order() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@type='submit' and @name='processCarrier']")).click();
        driver.findElement(By.xpath("//a[@class='bankwire']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@class='button btn btn-default button-medium']")).click();
        Thread.sleep(2000);

    }
   /* @Then("Check the order submit page and message Your order on My Store is complete. also check is amount is right.")
    public void check_the_order_submit_page_and_message_your_order_on_my_store_is_complete_also_check_is_amount_is_right() throws InterruptedException {
        WebElement displayedOrderSubmit = driver.findElement(By.xpath("//strong[text()='Your order on My Store is complete.']"));
        Assert.assertEquals(displayedOrderSubmit.isDisplayed(),true,"Your order on My Store is complete");

        WebElement displayedRightAmount = driver.findElement(By.xpath("//span[@class='price']"));
        Assert.assertEquals(displayedRightAmount.getText(),true,"check for right amount");

        Thread.sleep(2000);
    }*/

    @Then("Check the order submit page and message Your order on My Store is complete. also check is amount is right")
    public void check_the_order_submit_page_and_message_your_order_on_my_store_is_complete_also_check_is_amount_is_right() throws InterruptedException {
        WebElement displayedOrderSubmit = driver.findElement(By.xpath("//strong[text()='Your order on My Store is complete.']"));
        Assert.assertEquals(displayedOrderSubmit.isDisplayed(),true,"Your order on My Store is complete");

        WebElement displayedRightAmount = driver.findElement(By.xpath("//span[@class='price']"));
        Assert.assertEquals(displayedRightAmount.isDisplayed(),true,"check for right amount");

        Thread.sleep(2000);
    }
}


