package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Test for articles")
public class ArticleTests extends CoreTestCase
{
    @Test
    @Features(value={@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Compare article title with expected one")
    @Description("Описание работы теста. Проверка русского языка")
    @Step("Starting test testCompareArticleTitle")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject =  SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = ArticlePageObject.getArticleTitle();


        Assert.assertEquals(
                "Wee see unexpected title",
                "Java (programming language)",
                article_title
        );
    }
    @Test
   @Features(value={@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Swipe article to footer")
    @Description("Пролистывает до конца страницы")
    @Step("Starting test testSwipeArticle")
    @Severity(value = SeverityLevel.MINOR)
     public void testSwipeArticle() {
        SearchPageObject SearchPageObject =SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Car");
        SearchPageObject.clickByArticleWithSubstring("road");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();


    }

}
