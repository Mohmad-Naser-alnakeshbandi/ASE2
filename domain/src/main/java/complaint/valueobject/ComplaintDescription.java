package complaint.valueobject;

import errors.Errors;

import java.util.Objects;

public class ComplaintDescription {

    private String title;
    private String body;
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return body;
    }
    public ComplaintDescription(String title, String body) {
        validateTitle(title);
        validateDescription(body);
        this.title = title;
        this.body = body;
    }
    private void validateTitle(String title) {
        if (title == null || title.length() > 10) {
            throw new Errors("Title should have at least 10 characters");
        }
    }
    private void validateDescription(String description) {
        if (description == null || description.length() > 10) {
            throw new Errors("Description should have at least 10 characters");
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
