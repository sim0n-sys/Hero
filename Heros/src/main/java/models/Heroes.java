package models;

import java.util.ArrayList;
import java.util.List;

public class Heroes {
    private String name;
    private String superPower;
    private String role;
    private static List<Heroes> allHeroes = new ArrayList<>();

    public Heroes(String name, String power, String role){
        this.name = name;
        this.superPower = power;
        this.role = role;
        allHeroes.add(this);
    }

    public String getName() {
        return name;
    }

    public String getSuperPower() {
        return superPower;
    }

    public String getRole() {
        return role;
    }

    public static List<Heroes> getAllHeroes() {
        return allHeroes;
    }

    public static void clearAllHeroes(){
        allHeroes.clear();
    }
}
