package com.etroya.poligon.domain;

import com.etroya.poligon.domain.data.Drink;
import com.etroya.poligon.domain.data.Food;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class ProductFactory {



    //    private ProductAbstract product;
//    private Review[] reviews = new Review[5];
    private Map<ProductAbstract, List<Review>> products = new HashMap<>();
    private static Map<String, ResourceFormatter> formatters
            =Map.of("en-GB", new ResourceFormatter(Locale.UK),
            ("en-US", new ResourceFormatter(Locale.US),
            ("pl-PL", new ResourceFormatter(Locale.)


    )


    public ProductFactory(Locale locale) {

    }

    public ProductAbstract createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        ProductAbstract product = new Food(id, name, price, rating, bestBefore);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }

    public ProductAbstract createProduct(int id, String name, BigDecimal price, Rating rating) {
        ProductAbstract product = new Drink(id, name, price, rating);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }

    public ProductAbstract reviewProduct(int id, Rating rating, String comments) {
        reviewProduct(findProduct(id), rating, comments);
    }

    public ProductAbstract reviewProduct(ProductAbstract product, Rating rating, String comments) {
        List<Review> reviews = products.get(product);
        products.remove(product, reviews);
        reviews.add(new Review(rating, comments));
        int sum = 0;
        for (Review review : reviews) {
            sum += review.getRating().ordinal();
        }

        product = product.applyRating(Rateable.convert(Math.round((float) sum / reviews.size())));
        products.put(product, reviews);
        return product;
    }

    public void printProductReport(int id) {
        printProductReport(findProduct(id));
    }

    public void printProductReport(ProductAbstract product) {
        List<Review> reviews = products.get(product);
        StringBuilder txt = new StringBuilder();
        txt.append();
        txt.append('\n');
        Collections.sort(reviews);
        for (Review review : reviews) {
            if (review == null) {
                break;
            }

        }
        if (reviews.isEmpty()) {
            txt.append(resources.getString("no.reviews"));
            txt.append('\n');
        }
        System.out.println(txt);
    }

    public ProductAbstract findProduct(int id) {
        ProductAbstract result = null;
        for (ProductAbstract product : products.keySet()) {
            if (product.getId() == id) {
                result = product;
                break;
            }
        }
        return result;


    }

    private static class ResourceFormatter {
        private Locale locale;
        private ResourceBundle resources;
        private DateTimeFormatter dateFormat;
        private NumberFormat moneyFormat;

        private ResourceFormatter(Locale locale) {
            this.locale = locale;
            resources = ResourceBundle.getBundle("com.etroya.poligon", locale);
            dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                    .localizedBy(locale);
            moneyFormat = NumberFormat.getCurrencyInstance(locale);
        }

        private String formatProduct(ProductAbstract product) {
            return MessageFormat.format(resources.getString("product"),
                    product.getName(),
                    moneyFormat.format(product.getPrice()),
                    product.getRating().getStars(),
                    dateFormat.format(product.getBestBefore());
        }

        private String formatReview(Review review) {
            return MessageFormat.format(resources.getString("review"),
                    review.getRating().getStars(),
                    review.getComments());
        }

        private String getText(){
    }

}


//    public static Product createProduct(ProductType productType) {
//        switch (productType) {
//            case FOOD:
//                return new Food();
//            case DRINK:
//                return new Drink();
//
//        }
//        return null;
//
//    }
}
