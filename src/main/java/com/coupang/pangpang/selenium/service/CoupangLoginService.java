package com.coupang.pangpang.selenium.service;

import com.coupang.pangpang.selenium.config.ChromeDriverConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoupangLoginService {
    private static final String loginUrl = "https://wing.coupang.com/tenants/seller-web/post-matching/page/inventory-list";
    private final ChromeDriverConfig chrome;
    private Wait<WebDriver> wait;

    public void login(String keyword, String searchOrder) {
        ChromeDriver driver = chrome.getDriver();
        JavascriptExecutor js = chrome.getJs();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get(loginUrl);

        String title = driver.getTitle();
        searchOrder = ( searchOrder.isEmpty() || searchOrder.equals("") ) ? "DEFAULT" : searchOrder;

        log.info("title : " + title);
        log.info("keyword : " + keyword);
        log.info("searchOrder : " + searchOrder);


        /*
         * 로그인 확인
         */
        // TODO - cookie 조회로 받아오기
        if(title.indexOf("Coupang") < 0) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"username\"]")));
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
        }

        // BEST_SELLING // PV
        js.executeScript(
            "fetch('https://wing.coupang.com/tenants/seller-web/post-matching/search', \n" +
            "      { method: 'POST',\n" +
        "            headers: {'Content-Type': 'application/json'},\n" +
        "            body: JSON.stringify({ keyword: '"+keyword+"', " +
                    "                       excludedProductIds: [], " +
                    "                       searchPage: 0, " +
                    "                       searchOrder: '"+searchOrder+"'" +
                    "                   }),\n" +
        "           }\n" +
    "       )\n" +
        "    .then(res => res.json())\n" +
        "    .then(data =>  {   console.log(data.result);   \n" +
        "                       fetch('http://localhost:8090/coupang/excel/"+keyword+"/"+searchOrder+"', " +
            "                           { method: 'POST',       " +
            "                             headers : { 'Content-Type': 'application/json' },       " +
            "                             body: JSON.stringify(data.result)" +
        "                               }" +
        "                       ).then(res => console.log(res))" +
        "                    }" +
            ");"


//            "var xhr = new XMLHttpRequest();\n" +
//                    "xhr.open(\"POST\", \"https://wing.coupang.com/tenants/seller-web/post-matching/search\", false);\n" +
//                    "xhr.setRequestHeader('Content-Type', 'application/json');\n" +
//                    "xhr.send(JSON.stringify({\n" +
//                    "    keyword: '"+keyword+"', excludedProductIds: [], searchPage: 0, searchOrder: 'DEFAULT'\n " +
//                    "}));\n" +
//                    "xhr.onload = function() {\n" +
//                    "  console.log(this.responseText);\n"+
//                    "  //data = this.responseText;\n" +
//                        "data = JSON.parse(this.responseText);\n" +
//                    "window.localStorage.setItem('data', data);\n" +
//                        "//sendData(data);\n" +
//                    "" +
//                    "}\n"
//                    +
//                    "console.log(window.localStorage.getItem('data'));\n" +
//                    "var data = localStorage.getItem('data');\n" +
//                        "var ss = new XMLHttpRequest();\n" +
//                    "ss.withCredentials = false;\n"+
//                    "ss.open(\"POST\", \"http://localhost:8090/coupang/next\", true);\n"+
//                    "ss.setRequestHeader('Content-Type', 'application/json');\n"+"ss.setRequestHeader('Access-Control-Allow-Origin', '*');\n" +
//                    "var data =[{\"productId\": 7121132133,\n" +
//                    "            \"productName\": \"삼천리자전거 MTB형 접이식자전거 FD21\",\n" +
//                    "            \"brandName\": \"삼천리자전거\",\n" +
//                    "            \"itemId\": 13492335971,\n" +
//                    "            \"itemName\": \"170cm 블랙\",\n" +
//                    "            \"displayCategoryInfo\": [\n" +
//                    "                {\n" +
//                    "                    \"leafCategoryCode\": 82302,\n" +
//                    "                    \"rootCategoryCode\": 103371,\n" +
//                    "                    \"categoryHierarchy\": \"스포츠/레져>자전거>MTB/산악용\"\n" +
//                    "                }\n" +
//                    "            ],\n" +
//                    "            \"manufacture\": \"삼천리자전거\",\n" +
//                    "            \"categoryId\": 2752,\n" +
//                    "            \"itemCountOfProduct\": 2,\n" +
//                    "            \"imagePath\": \"retail/images/2022/03/02/15/7/682fcb66-f30a-4e4c-841c-c46c4b0360ac.jpg\",\n" +
//                    "            \"matchType\": null,\n" +
//                    "            \"salePrice\": 199000,\n" +
//                    "            \"vendorItemId\": 80746507874,\n" +
//                    "            \"ratingCount\": 197,\n" +
//                    "            \"rating\": 4.5,\n" +
//                    "            \"sponsored\": null,\n" +
//                    "            \"matchingResultId\": null,\n" +
//                    "            \"pvLast28Day\": 24152,\n" +
//                    "            \"deliveryMethod\": \"DOMESTIC\",\n" +
//                    "            \"attributeTypes\": null}];\n" +
//                    "ss.send(JSON.stringify(data));"
        );

//        driver.executeAsyncScript("function listView(){\n" +
//                                    "    let f = document.createElement('form');\n" +
//                                    "    \n" +
//                                    "    let obj;\n" +
//                                    "    obj = document.createElement('input');\n" +
//                                    "    obj.setAttribute('keyword', '"+keyword+"');\n" +
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
//        driver.quit();.
    }
}