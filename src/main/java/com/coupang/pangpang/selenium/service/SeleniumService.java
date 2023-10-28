package com.coupang.pangpang.selenium.service;

import com.coupang.pangpang.selenium.config.ChromeDriverConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
@RequiredArgsConstructor
public class SeleniumService {
//    private static final String loginUrl = "https://wing.coupang.com";
    private static final String loginUrl = "https://wing.coupang.com/tenants/seller-web/post-matching/page/inventory-list";

    private final ChromeDriverConfig chrome;
    private Wait<WebDriver> wait;

    public void login(String inputValue) {

        ChromeDriver driver = chrome.getDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get(loginUrl);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"username\"]")));


        String title = driver.getTitle();
        log.info(title);

        WebElement usernameInput = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"kc-login\"]"));
        usernameInput.clear();
        usernameInput.sendKeys("configenv");
        passwordInput.clear();
        passwordInput.sendKeys("!187f5391bf3d");
        loginBtn.sendKeys(Keys.ENTER);
        WebElement html = driver.findElement(By.xpath("/html"));
        driver.executeScript(
          "arguments[0].removeAttribute('data-wing-notify-state')", html
        );

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"wing-top-body\"]/div/div[5]/div[1]/div[2]/div/div/div/table/tbody/tr[1]/td[6]/button")));
        WebElement inBtn = driver.findElement(By.xpath("//*[@id=\"wing-top-body\"]/div/div[5]/div[1]/div[2]/div/div/div/table/tbody/tr[1]/td[6]/button"));
        inBtn.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"wing-top-body\"]/div/div[4]/div[2]/div[2]/div[2]/button")));
        WebElement innerBtn = driver.findElement(By.xpath("//*[@id=\"wing-top-body\"]/div/div[4]/div[2]/div[2]/div[2]/button"));
        innerBtn.sendKeys(Keys.ENTER);

//        WebElement searchInput = driver.findElement(By.xpath("//*[@id=\"container-wing-v2\"]/div[3]/div/div/div/div[2]/div/div[1]/div[4]/div[1]/div/span/span/input"));
//        searchInput.clear();
//        searchInput.sendKeys(inputValue);
// BEST_SELLING // PV
        driver.executeScript(
            "var xhr = new XMLHttpRequest();\n" +
            "xhr.open(\"POST\", \"https://wing.coupang.com/tenants/seller-web/post-matching/search\", false);\n" +
            "xhr.setRequestHeader('Content-Type', 'application/json');\n" +
            "xhr.send(JSON.stringify({\n" +
            "    keyword: '"+inputValue+"', excludedProductIds: [], searchPage: 0, searchOrder: 'DEFAULT'\n " +
            "}));" +
            "" +
            "xhr.onload = function() {\n" +
            "  console.log(this.responseText);\n"+
            "  data = JSON.parse(this.responseText);\n" +
            "console.log(data);\n" +
//                    "sessionStorage.setItem('searchData', this.responseText);" +
            "};\n");
//        driver.executeAsyncScript(
//                        "  var getCookie = function(name) {\n" +
//                        "      var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');\n" +
//                        "      return value? value[2] : null;\n" +
//                        "  };\n" +
//                        "var ss = new XMLHttpRequest();\n" +
//                "ss.open(\"POST\", \"http://localhost:8085/coupang/excel-download\", true);\n" +
//                "ss.setRequestHeader('Content-Type', 'application/json');\n" +
//                "ss.setRequestHeader('Access-Control-Allow-Origin','*');\n" +
//                "ss.send(getCookie('searchData'));\n" +
//                "ss.onload = function() {\n" +
//                "console.log(this.responseText);\n" +
//                "}\n"
//        );
//                    "" +
//                    "data = \"{'param1':'aaa', 'param2':'bbb'}\";" +
//                    "var ss = new XMLHttpRequest();" +
//                    "ss.open(\"POST\", \"http://localhost:8085/coupang/excel-download\", true);\n" +
//                    "ss.setRequestHeader('Content-Type', 'application/json');\n" +
//                    "ss.setRequestHeader('Access-Control-Allow-Origin','*')" +
//                    "ss.send(JSON.stringify(data));" +
//                    "ss.onload = function() {" +
//                    "console.log(this.responseText);" +
//                    "}"
//                    "function sendData(data) {" +
//                        "var xhr2 = new XMLHttpRequest();" +
//                        "xhr2.open('POST', 'http://localhost:8085/coupang/excel-download', false);\n" +
//                        "xhr2.send(data);" +
//                        "xhr2.onload = function() {\n" +
//                        "alert('END');\n" +
//                        "};" +
//                    "}\n"
//        );

//        driver.quit();

//        driver.executeAsyncScript("function listView(){\n" +
//                                    "    let f = document.createElement('form');\n" +
//                                    "    \n" +
//                                    "    let obj;\n" +
//                                    "    obj = document.createElement('input');\n" +
//                                    "    obj.setAttribute('keyword', '"+inputValue+"');\n" +
//                                    "    obj.setAttribute('excludedProductIds', []);\n" +
//                                    "    obj.setAttribute('searchPage', 0);\n" +
//                                    "    obj.setAttribute('searchOrder', 'DEFAULT');\n" +
//                                    "    \n" +
//                                    "    f.appendChild(obj);\n" +
//                                    "    f.setAttribute('method', 'post');\n" +
//                                    "    f.setAttribute('action', 'https://wing.coupang.com/tenants/seller-web/post-matching/search');\n" +
//                                    "    document.body.appendChild(f);\n" +
//                                    "    f.submit();\n" +
//                                    "}" +
//                "listView();");
    }
}