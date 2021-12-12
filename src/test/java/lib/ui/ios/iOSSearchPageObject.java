package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSSearchPageObject extends SearchPageObject {
      static {
          SEARCH_INT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
                  SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
                  SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
                  SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
                  SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
                  SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text = 'No results found']";
      }

    public iOSSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
