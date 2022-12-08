package StorOrg;

import java.io.Serializable;

public class TTNs implements Serializable {

    int id;
    String time;
    String storage;
    String typeProduct;
    String prodName;
    int idClient;
    int size;
    String status;

    int workerid;

    public TTNs(TTNs ttns)
    {
        this.id= ttns.id;
        this.time= ttns.time;
        this.storage= ttns.storage;
        this.typeProduct= ttns.typeProduct;
        this.prodName= ttns.prodName;
        this.idClient= ttns.idClient;
        this.size=ttns.size;
        this.status= ttns.status;
        this.workerid = ttns.workerid;
    }

    public TTNs() {
        this.id= 0;
        this.time= "";
        this.storage= "";
        this.typeProduct= "";
        this.prodName= "";
        this.idClient= 0;
        this.size=0;
        this.status= "";
        this.workerid = 0;

    }

    public TTNs(int id, String time, String storage, String typeProduct, String prodName, int idClient, int size, String status, int workerLastname) {
        this.id = id;
        this.time = time;
        this.storage = storage;
        this.typeProduct = typeProduct;
        this.prodName = prodName;
        this.idClient = idClient;
        this.size = size;
        this.status = status;
        this.workerid = workerLastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(String typeProduct) {
        this.typeProduct = typeProduct;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getWorkerid() {
        return workerid;
    }

    public void setWorkerid(int workerid) {
        this.workerid = workerid;
    }

    @Override
    public String toString(){
        return "TTNs{" +
                "id=" + id +
                ", time='" + time +'\'' +
                ", storage='" + storage +'\'' +
                ", typeProduct='" + typeProduct + '\'' +
                ", prodName='" + prodName + '\'' +
                ", idClient=" + idClient +
                ", size=" + size +
                ", status='" + status + '\'' +
                ", workerid=" + workerid +
                '}';
    }
}
