package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.UsageType;
import com.company.oop.cosmetics.utils.ParsingHelpers;
import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.List;

public class CreateShampooCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;
    private static final String PRODUCT_CREATED = "Shampoo with name %s was created!";
    private static final String PRODUCT_ALREADY_EXISTS = "Shampoo with name %s already exists!";

    private final CosmeticsRepository cosmeticsRepository;

    public CreateShampooCommand(CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsRepository = cosmeticsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String productName = parameters.get(0);
        String brandName=parameters.get(1);
        double price=ParsingHelpers.tryParseDouble(parameters.get(2),ParsingHelpers.INVALID_PRICE);
        GenderType genderType= ParsingHelpers.tryParseGender(parameters.get(3));
        int millilitres=ParsingHelpers.tryParseInt(parameters.get(4),ParsingHelpers.INVALID_PRICE);
        UsageType usageType= ParsingHelpers.tryParseUsageType(parameters.get(5));
        return createShampoo(productName,brandName,price, genderType,millilitres, usageType);


    }
    private String createShampoo(String productName, String brandName, double price, GenderType genderType, int millilitres, UsageType usageType) {
        if (cosmeticsRepository.productExist(productName)) {
            throw new IllegalArgumentException(String.format(PRODUCT_ALREADY_EXISTS, productName));
        }

        cosmeticsRepository.createShampoo(productName,brandName, price,genderType,millilitres,usageType );


        return String.format(PRODUCT_CREATED, productName);
    }

    @Override
    public String toString() {
        return "CreateShampooCommand{" +
                "cosmeticsRepository=" + cosmeticsRepository +
                '}';
    }


}
