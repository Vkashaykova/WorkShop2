package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.Objects;

public abstract class ProductImpl implements Product {
    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 10;
    public static final int BRAND_NAME_MIN_LENGTH = 2;
    public static final int BRAND_NAME_MAX_LENGTH = 10;
    private String name;
    private String brandName;
    private double price;
    GenderType genderType;


    public ProductImpl(String name, String brandName, double price, GenderType genderType) {
        setName(name);
        setBrand(brandName);
        setPrice(price);
        this.genderType = genderType;
    }


    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        validateName(name);
        this.name = name;
    }
    @Override
    public String getBrandName() {
        validateBrand(brandName);


        return this.brandName;
    }

    private void setBrand(String brandName) {
        validateBrand(brandName);
        this.brandName = brandName;
    }
    @Override
    public double getPrice() {
        return this.price;
    }

    private void setPrice(double price) {
        if(price<0){
            throw new IllegalArgumentException("Price should be non negative.");
        }
        this.price = price;
    }
    @Override
    public GenderType getGenderType() {
        return this.genderType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductImpl product = (ProductImpl) o;
        return Double.compare(product.price, price) == 0 && Objects.equals(name, product.name) && Objects.equals(brandName, product.brandName) && genderType == product.genderType;
    }
    @Override
    public String print() {
        return String.format("%s %s%n" +
                " #Price: %.2f%n" +
                " #Gender: %s",this.name, this.brandName,this.price,this.genderType);
    }

    public void validateName(String name){
        ValidationHelpers.validateStringLength(name,NAME_MIN_LENGTH,NAME_MAX_LENGTH,"Name");
    }
    public void validateBrand(String brandName){
        ValidationHelpers.validateStringLength(brandName,BRAND_NAME_MIN_LENGTH,BRAND_NAME_MAX_LENGTH,"BrandName");
    }


}
