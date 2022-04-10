import com.kazurayam.subprocessj.OSType;
import com.kazurayam.subprocessj.Subprocess;
import java.nio.file.Path

Subprocess.CompletedProcess cp;

if (OSType.isMac() || OSType.isUnix()) {
	cp = new Subprocess().cwd(new File("."))
			.run(Arrays.asList("/usr/local/bin/node","-v"));
} else { // possibly Windows
	cp = new Subprocess().cwd(new File("."))
			.run(Arrays.asList("cmd.exe", "/C", "node", "-v"));
}
assert 0 == cp.returncode();
assert cp.stdout().size() > 0;
cp.stdout().each({ line ->
	println line;
});
cp.stderr().each({ line ->
	println line;
});
cp.stdout().toString().contains("v16.14.2");
