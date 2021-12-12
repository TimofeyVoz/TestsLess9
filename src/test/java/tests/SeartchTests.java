package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
@Epic("Test for search")
public class SeartchTests extends CoreTestCase {
    @Test
    @Features(value={@Feature(value="Search"),@Feature(value="Amount of search results")})
    @DisplayName("Checking amount of results")
    @Description("Checking the number of results")
    @Step("Starting test testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Diskography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue(
                "We found too few results!",
                amount_of_search_results>0
        );
    }

    @Test
    @Features(value={@Feature(value="Search"),@Feature(value="Amount of search results")})
    @DisplayName("Checking for no result")
    @Description("Checking for the absence of a result with invalid input")
    @Step("Starting test testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "3452345twetwertert";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
    @Test
    @Features(value={@Feature(value="Search"),@Feature(value="Amount of search results")})
    @DisplayName("Checking three article by title")
    @Description("Checking three first article by title")
    @Step("Starting test testVerificationSearchThreeArticle")
    @Severity(value = SeverityLevel.NORMAL)
    public  void testVerificationSearchThreeArticle()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.verificationArticleByTitle(search_line, "1");
        SearchPageObject.verificationArticleByTitle(search_line, "2");
        SearchPageObject.verificationArticleByTitle(search_line, "3");

    }

}
