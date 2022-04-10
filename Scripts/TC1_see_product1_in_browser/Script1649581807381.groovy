import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser("");
WebUI.navigateToUrl("http://127.0.0.1:8080/products/1.json");
WebUI.delay(3);
WebUI.closeBrowser();