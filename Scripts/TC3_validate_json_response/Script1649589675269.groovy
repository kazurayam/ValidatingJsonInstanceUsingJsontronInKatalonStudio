import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.nio.file.Path
import java.nio.file.Paths

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS


Path projectDir = Paths.get(RunConfiguration.getProjectDir())

ResponseObject response = WS.sendRequest(findTestObject('product1'))

Path schemaDir = projectDir.resolve("Include/jsonschema");
Path schema  = schemaDir.resolve("schema5.json");
String schemaText = schema.toFile().text;

assert WS.validateJsonSchema(response, schemaText)