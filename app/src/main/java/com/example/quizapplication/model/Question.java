package com.example.quizapplication.model;

public class Question {
    private int ID;
    private String QUESTION;
    private String OPTA;
    private String OPTB;

    private String ANSWER;

    public Question() {
        ID=0;
        QUESTION="";
        OPTA="";
        OPTB="";
        ANSWER="";
    }

    public Question(String QUESTION, String OPTA, String OPTB, String ANSWER) {
        this.QUESTION = QUESTION;
        this.OPTA = OPTA;
        this.OPTB = OPTB;
        this.ANSWER = ANSWER;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getQUESTION() {
        return QUESTION;
    }

    public void setQUESTION(String QUESTION) {
        this.QUESTION = QUESTION;
    }

    public String getOPTA() {
        return OPTA;
    }

    public void setOPTA(String OPTA) {
        this.OPTA = OPTA;
    }

    public String getOPTB() {
        return OPTB;
    }

    public void setOPTB(String OPTB) {
        this.OPTB = OPTB;
    }

    public String getANSWER() {
        return ANSWER;
    }

    public void setANSWER(String ANSWER) {
        this.ANSWER = ANSWER;
    }
}
