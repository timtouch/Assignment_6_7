/**
 * Created by ttouc on 5/3/2017.
 */

public class TestingClass {

    static int numOfUsers = 2;
    public static void main(String[] args){
        try {
            for (int i = 0; i < numOfUsers; i++) {
                new UserThread(20, new RuntimeThread()).start();
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
