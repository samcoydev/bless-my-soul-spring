package blessmysoulbackend.rest.helpers;

public enum OrderType {
    REQUESTED ("Requested"),
    ORDER_RECEIVED ("Order_Received"),
    WAITING_ON_MATERIALS ("Waiting_On_Materials"),
    ORDER_IN_PROGRESS ("Order_In_Progress"),
    ORDER_COMPLETE ("Order_Complete");

    private final String state;

    OrderType(String state  ) {
        this.state = state;
    }

    private String getState() {
        return this.state;
    }
}
