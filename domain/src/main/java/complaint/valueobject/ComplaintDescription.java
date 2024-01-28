package complaint.valueobject;
import java.io.IOException;
import java.util.Objects;

public class ComplaintDescription {

    private final String title;
    private final String body;
    public String getTitle() {
        return title;
    }
    public String getBody() {
        return body;
    }

    public ComplaintDescription(String title, String body) throws IOException {
        validateTitle(title);
        validateDescription(body);
        this.title = title;
        this.body = body;
    }
    private void validateTitle(String title) throws IOException {
        if (title.length() < 10) {
            throw  new IOException("Title should have at least 10 characters");
        }
    }
    private void validateDescription(String description) throws IOException {
        if (description.length() < 10) {
            throw new IOException("Description should have at least 10 characters");
        }
    }
    @Override
    public String toString() {
        return "ComplaintDescription{" +
                "title='" + title + '\'' +
                ", description='" + body + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplaintDescription that)) return false;
        return Objects.equals(title, that.title) && Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, body);
    }
}
