package DB;

import StorOrg.Admin;
import StorOrg.Worker;

import java.util.ArrayList;

public interface IAdmin {
    ArrayList<Admin> get();
    void change(Worker obj);
}