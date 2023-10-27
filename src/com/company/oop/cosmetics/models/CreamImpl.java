package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.contracts.Cream;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.ScentType;
import com.company.oop.cosmetics.utils.ValidationHelpers;

public class CreamImpl extends ProductImpl implements Cream {
    public static final int CREAM_NAME_MIN_LENGTH = 3;
    public static final int CREAM_NAME_MAX_LENGTH = 15;
    public static final int CREAM_BRAND_NAME_MIN_LENGTH = 3;
    public static final int CREAM_BRAND_NAME_MAX_LENGTH = 15;

    private final ScentType scent;

    public CreamImpl(String name, String brandName, double price, GenderType genderType, ScentType scent) {
        super(name,brandName,price,genderType);
        this.scent = scent;
    }


    public ScentType getScent() {
        return scent;
    }

    @Override
    public String print() {
        return String.format("#%s%n" +
                " #Scent: %s%n"
                ,super.print(),this.scent);
    }

    @Override
    public void validateName(String name) {
        ValidationHelpers.validateStringLength(name,CREAM_NAME_MIN_LENGTH,CREAM_NAME_MAX_LENGTH,"Name");

    }

    @Override
    public void validateBrand(String brandName) {
        ValidationHelpers.validateStringLength(brandName,CREAM_BRAND_NAME_MIN_LENGTH,CREAM_BRAND_NAME_MAX_LENGTH,"BrandName");
    }


}
