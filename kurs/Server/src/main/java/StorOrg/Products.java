package StorOrg;

import java.io.Serializable;

public class Products implements Serializable {

    private int idclient;
    private String typeProd;
    private String storage;

    public int getV() {
        return V;
    }

    public void setV(int v) {
        V = v;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int V;
    private String name;
    private int balance;

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getTypeProd() {
        return typeProd;
    }

    public void setTypeProd(String typeProd) {
        this.typeProd = typeProd;
    }

    @Override
    public String toString() {
        return "Products{" +
                "idclient=" + idclient +
                ", typeProd='" + typeProd + '\'' +
                ", storage='" + storage + '\'' +
                ", name='" + name + '\'' +
                ", V=" + V +
                ", balance=" + balance +
                '}';
    }
}
