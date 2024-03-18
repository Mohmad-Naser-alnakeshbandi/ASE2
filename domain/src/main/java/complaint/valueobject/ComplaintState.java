package complaint.valueobject;

public enum ComplaintState {
    RECEIVE,
    Engaged,
    COMPLETED;

    @Override
    public String toString() {
        // Convert the enum value to lowercase
        return this.name().toLowerCase();
    }
}
