import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.beust.jcommander.JCommander;
import com.kazurayam.webdriverfactory.CookieServer;
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.context.TestCaseContext

class TL {
	
	private static CookieServer cookieServer = null;
	private static JCommander jc
	private static Path outputDir
	
	
	/**
	 * Executes before every test case starts.
	 * @param testCaseContext related information of the executed test case.
	 */
	@BeforeTestCase
	def sampleBeforeTestCase(TestCaseContext testCaseContext) {
		if (cookieServer == null) {
			cookieServer = new CookieServer()
			jc = JCommander.newBuilder().addObject(cookieServer).build()
			outputDir = Paths.get("./build/tmp/testOutput").resolve("TL");
			Files.createDirectories(outputDir);
			//
			cookieServer.setPort(8080)
			cookieServer.setBaseDir(Paths.get("./Include/web"))
			cookieServer.isPrintingRequested(true)
			cookieServer.isDebugMode(true)
			cookieServer.startup()
		}
	}

	/**
	 * Executes after every test case ends.
	 * @param testCaseContext related information of the executed test case.
	 */
	@AfterTestCase
	def sampleAfterTestCase(TestCaseContext testCaseContext) {
		println testCaseContext.getTestCaseId()
		println testCaseContext.getTestCaseStatus()
		if (cookieServer != null) {
			cookieServer.shutdown();
			cookieServer = null;
		}
	}

}