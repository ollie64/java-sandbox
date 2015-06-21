package hell;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class HelloTest {


    private final static String myPath = "/Users/obaskakov/data/tmp.txt";

    private final static String myJson = "{dsad: 14,\n dsad: 222}";

    @Test
    public void directoryMustExist() throws IOException {

        Hello.writeStringToFileAtomic(Paths.get(myPath), myJson);


    }


}
