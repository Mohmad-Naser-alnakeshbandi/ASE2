package complaint.aggregate;

import complaint.entity.Complaint;

import java.util.Objects;

public class complaints {
    private Complaint complaint;

    public Complaint getComplaint() {
        return complaint;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof complaints that)) return false;
        return Objects.equals(getComplaint(), that.getComplaint());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getComplaint());
    }
}
