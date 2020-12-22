package productstore;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Autowired
    private ProductView productView;

    // adding product Details by productId,productName

    @GetMapping("/addProductDetails")
    public Response<Product> getAll1(@RequestParam String productId, @RequestParam String productName, @RequestParam int stock, @RequestParam int price){
        Product product = new Product(productId,productName,stock,price);
        productView.map.put(productId, product);
        Response response = new Response(true,"Successfully Added",product);
        return response;

    }
    // getting products details by map Key

    @GetMapping("/getProducts")
    public ResponseEntity <Product> getAll2(@RequestParam String productId){
        Product product = productView.map.get(productId);
        if ((product == null)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    // get product by ProductId by predicate

    @GetMapping("/getById")
    public Product getByProductId(@RequestParam String productId){
        return productView.getById(productId);
    }

    // get product by productNAme

    @GetMapping("/getByName")
    public Product getByProductName(@RequestParam String productName){
        return productView.getByName(productName);
    }
    // getting responses whether the product is available or not

    @GetMapping("/response")
    public Response1 getAll3(@RequestParam String productId, @RequestParam int bought){
        Product product = productView.map.get(productId);
//        Product product = productView.getByName(productName); we can get a product by its name
        Response1 res = new Response1("IN Stock");
        if(bought> product.getStock()){
            res.setMessage("Out Of Stock");
        }
        else{
        int balance = product.getStock()-bought;
        product.setStock(balance);
        }
        return res;
    }
    // update the final price and update stock of individual product which is increasing or reducing the price of the product

    @GetMapping("/finalPrice")
    public Product getAll4(@RequestParam String productId, @RequestParam(required = false,defaultValue = "0") double priceUpdate,@RequestParam(required = false,defaultValue = "0") int addStock){
        Product product =productView.map.get(productId);
//        Product product = productView.getByName(productName); we can get a product by its name
        double balance = product.getPrice() + priceUpdate;
        product.setPrice(balance);
        int stock = product.getStock() + addStock;
        product.setStock(stock);
        return product;
    }
    // Billing a product with or without Discount and Tax

    @GetMapping("/billing")
    public Billing getAll5(@RequestParam String productId,@RequestParam(required = false) String productName,@RequestParam int quantity, @RequestParam (required = false,defaultValue = "0")  double discount,@RequestParam(required = false,defaultValue = "0") double tax){
       Product product = productView.map.get(productId);
//        Product product = productView.getByName(productName);
            double result = product.getPrice() * quantity;
            double discountPrice = (discount/100) * result;
            double tax1 = (tax/100) * result ;
            double result1 = result - discountPrice + tax1;
            if(product.getStock()>=quantity){
               int stock = product.getStock() - quantity;
               product.setStock(stock);
            }
            productView.billing1.add(result1);
            Billing billing = new Billing(productId,quantity,result1,productView.getTotal());
            Response1 response1 = new Response1("In Stock");
            if(product.getStock() < quantity){
                response1.setMessage("Out Of Stock");

            }
            return billing;
    }
    // final billing list of all products

    @GetMapping("/finalList")
    public List<Double> getBilling(){
        return productView.billing1;
    }

    // getting a list by Key

    @GetMapping("/listByKey")
    public List<String> getKey(){
        productView.listOfKeys = productView.map.keySet().stream().collect(Collectors.toCollection(ArrayList::new));
        return productView.listOfKeys;
    }
    // getting a list by Values

    @GetMapping("/listByValues")
    public List<Product> getValue(){
        productView.listOfValues = productView.map.values().stream().collect(Collectors.toCollection(ArrayList::new));
        return productView.listOfValues;
    }
    //getting a list by Key and Value

    @GetMapping("/listByKeyAndValue")
    public Map<String,Product> getMap(){
        return productView.map;
    }

    // final total of all products

    @GetMapping("/total")
    public double getTotal(){
        return productView.getTotal();

    }
    @GetMapping("/clear")
    public void clear(){
        productView.clearAll();
    }

}
