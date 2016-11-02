package ch.hevs.androidproject644.js.workingtime.DB;

/**
 * Created by Samuel on 29.10.2016.
 */

public class Worker {
    private int id;
    private String name;
    private String prename;
    private String birthdate;
    private String sexe;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPrename() {
        return prename;
    }
    public void setPrename(String prename) {
        this.prename = prename;
    }
    public String getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }

}