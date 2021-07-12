package Model;

public class Message {
    private String text;
    private String profileAddress;
    private String token;
    private String senderUsername;

    public Message(String text, String profileAddress, String token, String senderUsername) {
        this.text = text;
        this.token = token;
        this.profileAddress = profileAddress;
        this.senderUsername = senderUsername;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getProfileAddress() {
        return profileAddress;
    }

    public void setProfileAddress(String profileAddress) {
        this.profileAddress = profileAddress;
    }

    public String getToken() {
        return token;
    }

    public String getText() {
        return text;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", sender='" + token + '\'' +
                '}';
    }
}
