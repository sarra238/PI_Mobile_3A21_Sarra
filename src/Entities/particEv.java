/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author lv
 */
public class particEv {
 private int id;
private int idEv;
private int userId;    
private String Type;

    public particEv() {
        
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEv() {
        return idEv;
    }

    public void setIdEv(int idEv) {
        this.idEv = idEv;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public particEv(int idEv, String Type) {
        this.idEv = idEv;
        this.Type = Type;
    }

    public particEv(String Type) {
        this.Type = Type;
    }
   
}
