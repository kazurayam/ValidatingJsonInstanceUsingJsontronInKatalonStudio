import java.nio.file.Path
import java.nio.file.Paths

import com.kazurayam.subprocessj.OSType;
import com.kazurayam.subprocessj.Subprocess;
import com.kms.katalon.core.configuration.RunConfiguration

Path projectDir = Paths.get(RunConfiguration.getProjectDir()).toAbsolutePath()
Path JSONTRON_HOME = projectDir.resolve("node_modules/jsontron")
Path JSONValidator = JSONTRON_HOME.resolve("bin/JSONValidator.js")
Path patternDir = JSONTRON_HOME.resolve("data/dissertation/pattern")

Subprocess.CompletedProcess cp;

if (OSType.isMac() || OSType.isUnix()) {
	cp = new Subprocess().cwd(patternDir.toFile())
			.run(Arrays.asList("/usr/local/bin/node",
				JSONValidator.toString(),
				"-i", "loandata_pattern_good1.json",
				"-r", "loandata-rules_dissertation_pattern_good1.json"
				));
} else { // possibly Windows
	cp = new Subprocess().cwd(new File("."))
			.run(Arrays.asList("cmd.exe", "/C", "node", "-v"));  // FIXME
}
assert 0 == cp.returncode();
assert cp.stdout().size() > 0;
cp.stdout().each({ line ->
	println line;
});
cp.stderr().each({ line ->
	println line;
});

assert cp.stdout().toString().contains("THIS INSTANCE IS SEMANTICALLY VALID");
