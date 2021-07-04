package Model;

import javafx.beans.property.SimpleStringProperty;

public class DefineDataModel {
    private final SimpleStringProperty rank = new SimpleStringProperty("");
    private final SimpleStringProperty nickName = new SimpleStringProperty("");
    private final SimpleStringProperty score = new SimpleStringProperty("");

    public DefineDataModel(String rank, String userName, String score) {
        setRank(rank);
        setNickName(userName);
        setScore(score);
    }

    public void setRank(String rank1) {
        rank.set(rank1);
    }

    public String getRank() {
        return rank.get();
    }

    public void setNickName(String userName1) {
        nickName.set(userName1);
    }

    public String getNickName() {
        return nickName.get();
    }

    public void setScore(String score1) {
        score.set(score1);
    }

    public String getScore() {
        return score.get();
    }
}