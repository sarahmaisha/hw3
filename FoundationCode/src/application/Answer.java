package application;

public class Answer {
    private static int nextId = 1;

    private final int id;
    private final String text;
    private int likes;
    private boolean accepted;

    public Answer(String text) {
        this.id = nextId++;
        this.text = text;
        this.likes = 0;
        this.accepted = false;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getLikes() {
        return likes;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void increaseLikes() {
        likes++;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
