package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
@Epic("Test saved articles")
public class MyListTests extends CoreTestCase
{
    private static final String name_of_folder="Learning programming";
    private static final String
            login ="tima-voz",
            password="zxcv4321";
    @Test
    @Features(value={@Feature(value="Articles"),@Feature(value="SavedList")})
    @DisplayName("Save and del any articles")
    @Description("Saving two articles, deleting one of them, and verifying that the deletion was correct")
    @Step("Starting test testSaveFirstArticleToMyList")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveFirstArticleToMyList() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title_1 = ArticlePageObject.getArticleTitle();
        ArticlePageObject.waitForBoldWord();
        String bold_word_1 = ArticlePageObject.getBoldWord();


        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else{
            ArticlePageObject.addArticlesToMySaved();
            Thread.sleep(4000);
        }
        if (Platform.getInstance().isMw()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject (driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals ( "We are not on the same page after login.",
                    article_title_1,
                    ArticlePageObject.getArticleTitle()

            );
    }

        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Car");
        SearchPageObject.clickByArticleWithSubstring("otorized passenger road vehicle");

        ArticlePageObject.waitForTitleElement();
        String article_title_2 = ArticlePageObject.getArticleTitle();
        ArticlePageObject.waitForBoldWord();
        String bold_word_2 = ArticlePageObject.getBoldWord();

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else{
            ArticlePageObject.addArticlesToMySaved();
            Thread.sleep(4000);
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();

        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        Thread.sleep(400);
        MyListsPageObject.swipeByArticleToDelete(article_title_1);

        MyListsPageObject.openSavedArticle(article_title_2);
        ArticlePageObject.waitForBoldWord();
        String bold_word_check = ArticlePageObject.getBoldWord();
        Assert.assertEquals(
                "Removed wrong article",
                bold_word_2,
                bold_word_check
        );



    }
}
