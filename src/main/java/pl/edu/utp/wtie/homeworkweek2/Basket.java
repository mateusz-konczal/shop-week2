package pl.edu.utp.wtie.homeworkweek2;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@ConfigurationProperties(prefix="page-info")
public class Basket {

    private int tax;
    private int discount;
    private List<Product> productList;

    public Basket() {
        Product product1 = new Product("bread", getPrice());
        Product product2 = new Product("orange juice", getPrice());
        Product product3 = new Product("ham", getPrice());
        Product product4 = new Product("cheese", getPrice());
        Product product5 = new Product("wine", getPrice());
        productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getPrice() {
        Random random = new Random();
        double price = random.nextDouble() * (300 - 50) + 50;
        price *= 100;
        price = Math.round(price);
        price /= 100;
        return price;
    }

    public double getGrossPrice () {
        double grossPrice = getNetPrice() + calculateTax();
        return grossPrice;
    }

    public double calculateTax() {
        double tax = getNetPrice() * getTax() / 100;
        return tax;
    }

    public double getDiscountedPrice () {
        double discountedPrice = getGrossPrice() - calculateDiscount();
        return discountedPrice;
    }

    public double calculateDiscount() {
        double discount = getGrossPrice() * getDiscount() / 100;
        return discount;
    }

    public double getNetPrice() {
        double netPrice = 0;
        for (Product product : productList) {
            netPrice += product.getPrice();
        }
        return netPrice;
    }
}
