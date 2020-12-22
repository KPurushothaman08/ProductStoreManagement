package productstore;


import model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ProductView {
    Map<String, Product> map = new HashMap<>();
    List<String> listOfKeys = new ArrayList<>();
    List<Product> listOfValues = new ArrayList<>();

//    Map<String, Product> map1 = new HashMap<>();
//    List<String> listOfKeys1 = new ArrayList<>();
    List<Double> billing1 = new ArrayList<>();


    // getting product id directly from map

    public Product getById(String productId){
        return map.values().stream().filter(p -> p.getProductId().equals(productId)).findFirst().orElse(null);
    }
    // getting product name directly from map

    public Product getByName(String productName){
        return map.values().stream().filter(product -> product.getProductName().equals(productName)).findFirst().orElse(null);

    }
    // getting product id from list

    public Product getById1(String productId){
        return listOfValues.stream().filter(p -> p.getProductId().equals(productId)).findFirst().orElse(null);
    }
    // getting product name from list

    public Product getByName1(String productName){
        return listOfValues.stream().filter(p -> p.getProductName().equals(productName)).findFirst().orElse(null);
    }
    // getting list of keys from map

    public List<String> getListKeys(){
        listOfKeys = map.keySet().stream().collect(Collectors.toCollection(ArrayList::new));
        return listOfKeys;
    }
    // getting list of values from map

    public List<Product> getListValues(){
        listOfValues = map.values().stream().collect(Collectors.toCollection(ArrayList::new));
        return listOfValues;
    }

    public double getTotal(){
        double result = 0;
        for (int i=0;i<billing1.size();i++){
            result +=billing1.get(i);
        }
        return result;
    }
    public void  clearAll(){
         billing1.clear();
    }



    // getting product id directly from map
   /* public Product getById(String productId){
        return map.values().stream().filter(p -> {
            System.out.println("PRODUCT : "+p.getProductId()+" : "+productId+" : "+p.getProductId().equals(productId));
            return p.getProductId().equals(productId);
        }).findFirst().orElse(null);
    } */



}
