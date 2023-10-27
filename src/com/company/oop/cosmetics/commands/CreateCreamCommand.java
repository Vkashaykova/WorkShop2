package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.ScentType;
import com.company.oop.cosmetics.utils.ParsingHelpers;
import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.List;

public class CreateCreamCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;
    private static final String PRODUCT_CREATED = "Cream with name %s was created!";
    private static final String PRODUCT_ALREADY_EXISTS = "Cream with name %s already exists!";

    private final CosmeticsRepository cosmeticsRepository;

    public CreateCreamCommand(CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsRepository = cosmeticsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String productName = parameters.get(0);
        String brandName=parameters.get(1);
        double price= ParsingHelpers.tryParseDouble(parameters.get(2),ParsingHelpers.INVALID_PRICE);
        GenderType genderType= ParsingHelpers.tryParseGender(parameters.get(3));
        ScentType scentType=ParsingHelpers.tryParseScent(parameters.get(4));
        return createCream(productName,brandName,price, genderType,scentType);


    }
    private String createCream(String productName, String brandName, double price, GenderType genderType, ScentType scentType) {
        if (cosmeticsRepository.productExist(productName)) {
            throw new IllegalArgumentException(String.format(PRODUCT_ALREADY_EXISTS, productName));
        }

        cosmeticsRepository.createCream(productName,brandName, price,genderType,scentType );


        return String.format(PRODUCT_CREATED, productName);
    }


}
