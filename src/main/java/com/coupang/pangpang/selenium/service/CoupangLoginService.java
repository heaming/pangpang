package com.coupang.pangpang.selenium.service;

import com.coupang.pangpang.selenium.driver.CustChromeDriver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoupangLoginService {
//    private static final String loginUrl = "https://wing.coupang.com";

    private static final String loginUrl = "https://wing.coupang.com/tenants/seller-web/post-matching/page/inventory-list";
    private static final String searchUrl = "";
    private final CustChromeDriver chrome;
    private Wait<WebDriver> wait;

    public void login(String inputValue) {

        ChromeDriver driver = chrome.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        driver.get(loginUrl);

        String title = driver.getTitle();
        log.info(title);

        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("kc-login"));
        usernameInput.clear();
        usernameInput.sendKeys("configenv");
        passwordInput.clear();
        passwordInput.sendKeys("!187f5391bf3d");
        loginBtn.click();
        WebElement html = driver.findElement(By.xpath("/html"));
        driver.executeScript(
          "arguments[0].removeAttribute('data-wing-notify-state')", html
        );

        WebElement inBtn = driver.findElement(By.xpath("//*[@id=\"wing-top-body\"]/div/div[5]/div[1]/div[2]/div/div/div/table/tbody/tr[1]/td[6]/button"));
        inBtn.click();

        WebElement innerBtn = driver.findElement(By.xpath("//*[@id=\"wing-top-body\"]/div/div[4]/div[2]/div[2]/div[2]/button"));
        innerBtn.click();

//        WebElement searchInput = driver.findElement(By.xpath("//*[@id=\"container-wing-v2\"]/div[3]/div/div/div/div[2]/div/div[1]/div[4]/div[1]/div/span/span/input"));
//        searchInput.clear();
//        searchInput.sendKeys(inputValue);


        driver.executeAsyncScript(
            "var xhr = new XMLHttpRequest();\n" +
                    "xhr.open(\"POST\", \"https://wing.coupang.com/tenants/seller-web/post-matching/search\", true);\n" +
                    "xhr.setRequestHeader('Content-Type', 'application/json');\n" +
                    "xhr.send(JSON.stringify({\n" +
                    "    keyword: '"+inputValue+"', excludedProductIds: [], searchPage: 0, searchOrder: 'DEFAULT'\n " +
                    "}));\n" +
                    "xhr.onload = function() {\n" +
                    "  console.log(this.responseText);\n"+
                    "  var data = JSON.parse(this.responseText);\n" +
                    "  console.log(data);\n" +
                    "}"
        );

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