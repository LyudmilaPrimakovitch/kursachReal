package StorOrg;

import java.io.Serializable;

public class Receive implements Serializable {
    int balance;
    String storage;

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

    @Override
    public String toString() {
        return "Receive{" +
                "balance=" + balance +
                ", storage='" + storage + '\'' +
                '}';
    }
}