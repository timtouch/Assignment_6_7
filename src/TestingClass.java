/**
 * Created by ttouc on 5/3/2017.
 */
import java.io.IOException;

public class TestingClass {

    public static void main(String[] args) throws IOException{
        for (int i = 0; i < 3; i++) {
            new UserThread().start();
        }
    }
}
