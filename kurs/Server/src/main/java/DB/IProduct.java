package DB;

import StorOrg.Products;
import java.util.ArrayList;

public interface IProduct {
    boolean insert(Products obj);
    ArrayList<Products> get();
    ArrayList<Products> find(Products c);
    boolean insertProducts(Products obj);

    boolean registration(Products obj);
}
