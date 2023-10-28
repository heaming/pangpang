package com.coupang.pangpang.selenium.config;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
//@Setter
public class ChromeDriverConfig {
//    @Value("${webdriver.chrome.driver_id}")
    private String WEB_DRIVER_ID;

//    @Value("${webdriver.chrome.driver_path}")
    private String WEB_DRIVER_PATH;

    private ChromeDriver driver;

    public ChromeDriverConfig(@Value("${webdriver.chrome.driver_id}") String webDriverId,
                              @Value("${webdriver.chrome.driver_path}") String webDriverPath
                              ) {
        this.WEB_DRIVER_ID = webDriverId;
        this.WEB_DRIVER_PATH = webDriverPath;

        System.setProperty (WEB_DRIVER_ID, WEB_DRIVER_PATH);

        ChromeOptions options = new ChromeOptions();
        options.setCapability("ignoreProtectedModeSettings", true);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--start-maximized");            // 전체화면으로 실행
        options.addArguments("--disable-popup-blocking");    // 팝업 무시
//        options.addArguments("--disable-default-apps");     // 기본앱 사용안함
        options.addArguments("--disable-gpu");            //gpu 비활성화
//        options.addArguments("--blink-settings=imagesEnabled=false"); //이미지 다운 안받음
        options.setHeadless(false); //addArguments("headless"); // 화면 안보이게

        this.driver = new ChromeDriver(options);
    }

//    @AfterEach // junit 아직 설정 안함
    public void quit() {
        System.clearProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY);
        System.clearProperty(ChromeDriverService.CHROME_DRIVER_LOG_LEVEL_PROPERTY);

        this.driver.quit();
    }
}
