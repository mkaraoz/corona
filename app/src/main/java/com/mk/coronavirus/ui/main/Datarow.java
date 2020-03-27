package com.mk.coronavirus.ui.main;

public class Datarow {
    private String name;
    private int deathCount;
    private int caseCount;
    private int flag;

    public Datarow() {
    }

    public Datarow(String id, String name, int deathCount, int caseCount, int flag) {
        this.name = name;
        this.deathCount = deathCount;
        this.caseCount = caseCount;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeathCount() {
        return deathCount;
    }

    public void setDeathCount(int deathCount) {
        this.deathCount = deathCount;
    }

    public int getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(int caseCount) {
        this.caseCount = caseCount;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
