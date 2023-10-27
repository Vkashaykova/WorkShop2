package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.UsageType;
import com.company.oop.cosmetics.utils.ParsingHelpers;
import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CreateToothpasteCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;
    private static final String PRODUCT_ALREADY_EXISTS = "Toothpaste with name %s already exists!";
    private static final String PRODUCT_CREATED = "Toothpaste with name %s was created!";

    private final CosmeticsRepository cosmeticsRepository;

    public CreateToothpasteCommand(CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsRepository = cosmeticsRepository;
    }

    @Override
    public String execute(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String productName = parameters.get(0);
        String brandName = parameters.get(1);
        double price = ParsingHelpers.tryParseDouble(parameters.get(2),ParsingHelpers.INVALID_PRICE);
        GenderType genderType = ParsingHelpers.tryParseGender(parameters.get(3));
        List<String> ingredients= new ArrayList<>(Arrays.asList(parameters.get(4).split(",")));
        return createToothpaste(productName, brandName, price, genderType,ingredients );


    }

    private String createToothpaste(String productName, String brandName, double price, GenderType genderType,List<String>ingredients) {
        if (cosmeticsRepository.productExist(productName)) {
            throw new IllegalArgumentException(String.format(PRODUCT_ALREADY_EXISTS, productName));
        }

        cosmeticsRepository.createToothpaste(productName,brandName, price,genderType,ingredients);


        return String.format(PRODUCT_CREATED, productName);

}

    @Override
    public String toString() {
        return "CreateToothpasteCommand{" +
                "cosmeticsRepository=" + cosmeticsRepository +
                '}';
    }
}