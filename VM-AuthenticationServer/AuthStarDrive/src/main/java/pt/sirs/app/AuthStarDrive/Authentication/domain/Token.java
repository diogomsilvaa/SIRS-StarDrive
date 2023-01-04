package pt.sirs.app.AuthStarDrive.Authentication.domain;

public class Token {
    private String content;

    public Token(String content) {
        setContent(content);
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
}
