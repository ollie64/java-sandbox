package hell;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class HelloTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();


    private final static String tmpFileName = "cellosState.txt";

    private final static String myJson = "{dsad: 14,\n dsad: 222}\n";

    @Test
    public void writeShouldBeAtomic() throws IOException {

//        System.out.println("Temp folder: " + tempFolder.getRoot().toString());

        Path tmpPath0 = tempFolder.getRoot().toPath();
        Path myPath1 = tmpPath0.resolve("relativeDir1").resolve("relativeDir2").resolve(tmpFileName);
        Path myPath2 = tmpPath0.resolve(tmpFileName);
        Assert.assertFalse(myPath1.toFile().exists());
        // check write to new dir
        Hello.writeStringToFileAtomic(myPath1, myJson);
        Assert.assertTrue(myPath1.toFile().isFile());
        // check existing dir
        Hello.writeStringToFileAtomic(myPath2, myJson);
        Assert.assertTrue(myPath2.toFile().isFile());
        List<String> strings = Files.readAllLines(myPath1);
        String writtenJson = String.join("\n", strings);
        Assert.assertEquals(writtenJson, myJson);

    }


}
