package complaint.valueobject;

public enum ComplaintState {
    RECEIVE,
    WORKING_ON,
    COMPLETED;

    @Override
    public String toString() {
        // Convert the enum value to lowercase and replace underscores with spaces
        return this.name().toLowerCase().replace('_', ' ');
    }
}
