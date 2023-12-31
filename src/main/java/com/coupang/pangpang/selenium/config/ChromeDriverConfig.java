package com.coupang.pangpang.selenium.config;

import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ChromeDriverConfig {
    private String WEB_DRIVER_ID; // = "webdriver.chrome.driver";
    private String WEB_DRIVER_PATH; // = "C:/chromedriver-win32/chromedriver-win32/chromedriver.exe";

    private ChromeDriver driver;
    private JavascriptExecutor js;

    public ChromeDriverConfig(@Value("${webdriver.chrome.driver_id}") String webDriverId,
                              @Value("${webdriver.chrome.driver_path}") String webDriverPath) {
        this.WEB_DRIVER_ID = webDriverId;
        this.WEB_DRIVER_PATH = webDriverPath;

        System.setProperty (WEB_DRIVER_ID, WEB_DRIVER_PATH);

        ChromeOptions options = new ChromeOptions();
        options.setCapability("ignoreProtectedModeSettings", true);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--start-maximized");            // 전체화면으로 실행
        options.addArguments("--disable-popup-blocking");    // 팝업 무시
//        options.addArguments("--disable-default-apps");     // 기본앱 사용안함
//        options.addArguments("--disable-popup-blocking");       //팝업안띄움
        options.addArguments("--disable-gpu");            //gpu 비활성화
//        options.addArguments("--blink-settings=imagesEnabled=false"); //이미지 다운 안받음
//        options.addArguments("--headless");
        options.setHeadless(true);

        this.driver = new ChromeDriver(options);
        this.js = (JavascriptExecutor) this.getDriver();
    }

//    @AfterEach // junit 아직 설정 안함
    public void quit() {
        System.clearProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY);
        System.clearProperty(ChromeDriverService.CHROME_DRIVER_LOG_LEVEL_PROPERTY);

        driver.quit();
    }
}
