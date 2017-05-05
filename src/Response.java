/**
 * Place to store responses
 *
 * Created by ttouc on 5/4/2017.
 */
public class Response {
    private Request responseFrom;
    private int value;

    Response(Request request, int value){
        this.responseFrom = request;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Request getResponseFrom() {
        return responseFrom;
    }
}
