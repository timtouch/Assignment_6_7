/**
 * Enum class for request data type
 *
 * Created by ttouc on 5/4/2017.
 */
public enum Request {
    NEXTEVEN,
    NEXTODD,
    NEXTEVENFIB,
    NEXTPRIME,
    NEXTLARGERRAND;

    public static Request getRandomRequest(){
        return Request.values()[(int)(Math.random()*Request.values().length)];
    }
}
