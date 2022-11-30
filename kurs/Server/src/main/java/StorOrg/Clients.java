package StorOrg;

import java.io.Serializable;
import java.util.Objects;

public class Clients implements Serializable{
    private String companyName;
    private String email;
    private String login;
    private String password;

    private String averageV;
    private int fullV=0;
    private int positiveV=0;
    private int payment;

    public String getAverageV() {
        return averageV;
    }

    public int getFullV() {
        return fullV;
    }

    public void setFullV(int fullV) {
        this.fullV = fullV;
    }

    public void setAverageV(String averageV) {
        this.averageV = averageV;
    }

    public int getPositiveV() {
        return positiveV;
    }

    public void setPositiveV(int positiveV) {
        this.positiveV = positiveV;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clients that = (Clients) o;

        return  Objects.equals(this.login, that.login) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.companyName, that.companyName) &&
                Objects.equals(this.email, that.email) &&
                Objects.equals(this.averageV,that.averageV);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.login, this.password, this.companyName, this.email, this.averageV);
    }

    @Override
    public String toString() {
        return "Clients{" +
                "companyName='" + companyName + '\'' +
                ", email='" + email + '\'' +
                ", averageV='" + averageV + '\'' +
                ", fullV='" + fullV + '\'' +
                ", positiveV='" + positiveV + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", payment=" + payment +
                '}';
    }
}
