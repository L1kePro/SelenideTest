import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class YandexMarketMainPage {
    ElementsCollection list;

public YandexMarketMainPage openPage() {
    open("https://market.yandex.ru/");
    return this;
}//Открытие главной страницы

    public YandexMarketMainPage openAllCategories() {
        $(By.id("27903767-tab")).click();
        return this;
    }//Раскрыть список всех категорий товаров

    public YandexMarketMainPage hoverElement(String text) {
        $(By.linkText(text)).hover();
        return this;
    }//Навести на элемент

    public YandexMarketMainPage searchByBrand (String text) throws InterruptedException {
        $(By.cssSelector("div._178jz2CyDL > div:nth-child(2) button")).click();//Показать всех производителей
        $(By.name("Поле поиска")).setValue(text);
        $(By.className("_2RDCAZB4Gk")).shouldHave(text(text));
        $(By.name("Поле поиска")).sendKeys(Keys.TAB, Keys.ENTER);//Что бы выбрался верхний чек бокс;
        return this;
    }//Фильр по производителю

    public YandexMarketMainPage clickElemByText(String text) {
        $(By.linkText(text)).click();
        return this;
    }

    public YandexMarketMainPage topFilter(String text) {
        $(By.linkText(text)).click();
        return this;
    }//Сортировка

    public List getProducts() {
        return list = $$(By.cssSelector(".n-snippet-card2 .n-snippet-card2__title"));
    }//Получить список всех продуктов на странице

    public List getNamesPruducts(List ls) {
        List<String> elems = new ArrayList();
            for (int i = 1; i <= ls.size(); i++) {
                elems.add($(By.cssSelector(".n-snippet-card2:nth-child(" + i + ") .n-snippet-card2__title")).getText());
            }
        return elems;
    }//Получить список имен всех продуктов

    public String getNameProduct(int position) {
            return $(By.cssSelector(".n-snippet-card2:nth-child(" + position + ") .n-snippet-card2__title")).getText();
    }//Получить имя конкретного продукта

    public String getPriceProduct(int position) {
        return $(By.cssSelector("div.n-snippet-card2:nth-child(" + position + ") div.n-snippet-card2__main-price")).getText();
    }//Получить цену конкретного продукта

    public YandexMarketMainPage mainSearch(String text) {
        $(By.id("header-search")).sendKeys(text, Keys.ENTER);
        return this;
    }//Поиск

}
