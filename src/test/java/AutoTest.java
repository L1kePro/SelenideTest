import com.codeborne.selenide.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertTrue;

public class AutoTest {
        YandexMarketMainPage ymmp = new YandexMarketMainPage();
        String name, price;

        @Before
        public void before() {
            Configuration.startMaximized = true;
            Configuration.timeout = 15000;
        }

        /*
    1.	Перейти на https://market.yandex.ru
    2.	Выбрать категорию: Компьютерная техника -> Компьютеры -> Планшеты
    3.	Выбрать производителя: Samsung
    4.	Установить сортировку: по цене
    5.	Вывести в лог все доступные товары)
    6.	Запомнить вторую позицию из списка продукт (наименование и цена)
    7.	Ввести в строке поиска запомненный продукт (наименование) и нажать «Поиск»
    8.	Убедиться, что в выведенном списке 1 строкой указан искомый продукт по наименованию и стоимости. При не совпадении тест должен завершиться с ошибкой.
     */
        @Test
        public void T2() throws InterruptedException {
            ymmp.openPage().openAllCategories()
                    .hoverElement("Компьютерная техника")
                    .clickElemByText("Планшеты")
                    .searchByBrand("Samsung")
                    .topFilter("по цене");

            List elems = ymmp.getNamesPruducts(ymmp.getProducts());
            Iterator iter = elems.iterator();
            while (iter.hasNext()) {
                System.out.println(iter.next());
            }
            name = ymmp.getNameProduct(2);//получение имени второго элемента
            price = ymmp.getPriceProduct(2);//Получение цены второго элемента
            ymmp.mainSearch(name); //Поиск по имени
            assertTrue(name.equalsIgnoreCase(ymmp.getNameProduct(1)));
            assertTrue(price.equalsIgnoreCase(ymmp.getPriceProduct(1)));
            Thread.sleep(5000);
        }

        @After
        public void end() {
            getWebDriver().quit();
        }

    }

