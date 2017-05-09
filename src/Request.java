/**
 * Enum class for request data type
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
