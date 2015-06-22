package hell;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import static java.nio.file.StandardCopyOption.ATOMIC_MOVE;

class Hello {


    static final String TMP_PATH_PREFIX = "celos-";

    private static final Charset CHARSET = Charset.forName("UTF-8");

    public static void
    writeStringToFileAtomic(Path pdst, String json) throws IOException {
        Path ptmp = null;
        try {
            ptmp = Files.createTempFile(TMP_PATH_PREFIX, null);

            Files.createDirectories(pdst.getParent());

            // FIXME convert json String to Iterable[String]
            Files.write(ptmp, Collections.singletonList(json), CHARSET);

            Files.move(ptmp, pdst, ATOMIC_MOVE);
        } finally {
            if (null != ptmp) {
                Files.deleteIfExists(ptmp);
            }
        }
    }

    private final static String myPath = "/Users/obaskakov/data/tmp.txt";

    public static void main(String[] args) {
        System.out.println("Hello World!");

        String myJson = "{dsad: 14,\n dsad: 222}";

        try {

            writeStringToFileAtomic(Paths.get(myPath), myJson);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
