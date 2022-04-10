import java.nio.file.Path
import java.nio.file.Paths

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

Path projectDir = Paths.get(RunConfiguration.getProjectDir())
Path webDir = projectDir.resolve("Include/web");
Path schemaDir = projectDir.resolve("Include/jsonschema");

Path product1 = webDir.resolve("products/1.json");
String product1Text = product1.toFile().text;

Path schema  = schemaDir.resolve("schema5.json");
String schemaText = schema.toFile().text;

assert WS.validateJsonSchema(product1Text, schemaText)


