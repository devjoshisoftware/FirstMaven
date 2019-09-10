import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FirstMaven {
    protected static WebDriver driver;

    public String generateemail(String startValue)
    {
    String email=startValue.concat(new Date().toString());
    return email;}

    public static String randomDate(){
        DateFormat format = new SimpleDateFormat("ddMMyyHHmmss");
        return format.format(new Date());
    }


    @BeforeMethod
    public void beforemethod() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\Resources\\BrowserDriver\\chromedriver.exe");

        //open the Browser
        driver = new ChromeDriver();
        //maximise the browser window screen
        //driver.manage().window().fullscreen();
        driver.manage().window().maximize();
        //set implicity wait for driver object
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        //open the website
        driver.get("http://demo.nopcommerce.com/");

        //-----------------------------------------------------------------------------------------------------------
        //click on register button
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        //enter firstname
        driver.findElement(By.id("FirstName")).sendKeys("Dev");
        //enter lastname
        driver.findElement(By.xpath("//input[@name='LastName']")).sendKeys("Joshi");
        //generate diff mail id everytime
        driver.findElement(By.name("Email")).sendKeys("tester"+randomDate()+"@test.com");
        System.out.println("tester"+randomDate()+"@test.com");
        //enter password
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("1234567");
        //enter confirm password
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("1234567");
        //closer the browser window
        driver.findElement(By.xpath("//input[@id='register-button']")).click();
    }
    @Test
    public void usershouldBeRegisterSuccesfully() {

        //Check Expectedresult
        String Expectedresult="Your registration completed";
        //path for Actual result
        String Actualresult= driver.findElement(By.xpath("//div[@class='result']")).getText();
        //check the result is match or not
        Assert.assertEquals(Actualresult,Expectedresult);
    }

    @Test
    public void userShouldBeAbleToReferAProductToFriend() {
        //click on Register
        driver.findElement(By.xpath("//input[@name='register-continue']")).click();
        //select mac book pro
        driver.findElement(By.xpath("//h2/a[@href=\"/apple-macbook-pro-13-inch\"]")).click();
        //click on email on friend
        driver.findElement(By.xpath("//input[@value='Email a friend']")).click();
        //enter friend mail
        driver.findElement(By.xpath("//input[@id='FriendEmail']")).sendKeys("milankg18@gmail.com");
        //enter your mail id
        driver.findElement(By.xpath("//input[@id='YourEmailAddress']")).click();
        //write personal message
        driver.findElement(By.xpath("//textarea[@id='PersonalMessage']")).sendKeys("Please check this out");
        //click on send mail
        driver.findElement(By.xpath("//input[@value='Send email']")).click();
        String Expectedresult="Your message has been sent.";
        String Actualresult= driver.findElement(By.xpath("//div[@class='result']")).getText();
        Assert.assertEquals(Actualresult,Expectedresult);
    }
@Test
public void userShouldBeAbleToNavigateCameraAndPhotopage() {
    //click on Register
    driver.findElement(By.xpath("//input[@name='register-continue']")).click();
        //click on Electronics
    driver.findElement(By.xpath("//h2/a[@href=\"/electronics\"]")).click();
    //Navigate to camera and photopage
    driver.findElement(By.linkText("Camera & photo")).click();
    String Expectedresult="Camera & photo";
    String Actualresult=driver.findElement(By.linkText("Camera & photo")).getText();
    Assert.assertEquals(Actualresult,Expectedresult);
}

@Test
public void userShouldBeAbleToFilterJewellaryByPrice() {
    //click on Register
    driver.findElement(By.xpath("//input[@name='register-continue']")).click();
        //Navigate to jewelry
    driver.findElement(By.linkText("Jewelry")).click();
    //select price limit
    driver.findElement(By.xpath("//a[contains(@href,\"700-3000\")]")).click();
    //check the Expected result
    String Expectedresult="$700.00 - $3,000.00";
    //check the Actual result
    String Actualresult= driver.findElement(By.xpath("//span[@class='item']")).getText();
   //compare the result
    Assert.assertEquals(Actualresult,Expectedresult);
    String Productprice=driver.findElement(By.xpath("//span[@class='price actual-price']")).getText();

    String price1=String.valueOf(Productprice.replace("$",""));
    String price2=String.valueOf(price1.replace(",",""));
    double price=Double.valueOf(price2);
    //check the result
    Assert.assertTrue(price>=700 && price<=3000);
}
    @Test
            public void userShouldBeAbleToAddToProductInBasket() throws InterruptedException {
        //click on Register
        driver.findElement(By.xpath("//input[@name='register-continue']")).click();
        //click on the Books Category
        driver.findElement(By.linkText("Books")).click();
        //add first book in cart
        driver.findElement(By.xpath("//input[contains(@onclick,'37/1')]")).click();
        Thread.sleep(5000);
        //add second book in cart
        driver.findElement(By.xpath("//input[contains(@onclick,'39/1')]")).click();
        //click on Shopping cart
       driver.findElement(By.linkText("Shopping cart")).click();
       //check Expected Result
       String Expectedresult="Shopping cart";
       //check Actual result
       String Actualresult=driver.findElement(By.linkText("Shopping cart")).getText();
       //compare the Actual vs Expected
       Assert.assertEquals(Actualresult,Expectedresult);
    }

    @AfterMethod
    public void afterMethod()
    {
        //close the driver
        driver.quit();

    }
}