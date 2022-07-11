package graphql.response;

/**
 * 10:13 AM 10-Jun-22
 * Long Tran
 */
public enum  GraphqlState {

    SUCCESS(0),
    FAILED(-1);

    private final int value;

    GraphqlState(int i) {
        value = i;
    }

    public int getValue(){
        return this.value;
    }

    public static GraphqlState getEnum(int i){
        if (i == 1) {
            return FAILED;
        }
        return SUCCESS;

    }
}
