package com.example.ramzigym.Model;

public class Athlete {
    private String id;
    private String name;
    private String sex;
    private String time;
    private String type;
    private String operator;

    public Athlete(String id, String name, String sex, String time, String type, String operator) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.time = time;
        this.type = type;
        this.operator = operator;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", time='" + time + '\'' +
                ", type='" + type + '\'' +
                ", operator='" + operator + '\'' +
                '}';
    }
}
