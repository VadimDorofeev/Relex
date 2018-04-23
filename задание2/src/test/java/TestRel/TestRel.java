package TestRel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;


public class TestRel {

        private static WebDriver driver;

        @Before
        public void start(){

            System.setProperty("webdriver.gecko.driver","geckodriver.exe");
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://www.relex.ru/ru/");
        }

        @Test
        public void search(){

            //находим поле поиска и пишем в него "QA-школа"
            WebElement searchfield = driver.findElement(By.name("q"));
            searchfield.sendKeys("QA-школа");

            //нажимаем на значок поиска
            WebElement search = driver.findElement(By.id("relex-header-search"));
            search.click();

            //проверяем, что результат поиска не пустой
            boolean el = driver.findElements(By.className("search-item")).size()>0;
            Assert.assertTrue("результат поиска пустой",el);

            //нажимаем на первую ссылку
            WebElement link = driver.findElement((By.xpath("/html/body/div[4]/div/div/div[2]/div/div/div/div/h4/a")));
            link.click();

            //проверяем заголовок страницы
            String headReal = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/h2")).getText();
            Assert.assertEquals("QA - школа", headReal);

        }

        @After
        public void theEnd(){

            driver.quit();

        }

    }

