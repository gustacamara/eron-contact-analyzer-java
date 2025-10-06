package model;

public class FrequencyNode {
    private String email;
    private int frequency;

    public FrequencyNode(String email, int frequency) {
        this.email = email;
        this.frequency = frequency;
    }

    public String getEmail() {
        return email;
    }

    public int getFrequency() {
        return frequency;
    }
}
