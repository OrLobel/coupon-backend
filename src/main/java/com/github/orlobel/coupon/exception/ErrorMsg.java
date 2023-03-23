package com.github.orlobel.coupon.exception;

// TODO: 05/07/2022 Add capital names
public enum ErrorMsg {
    // general
    FORBIDDEN("forbidden"),
    // company
    Comapany_Email_Name_Exist("Email and name already exist in DB"),
    Company_Already_Exists("Company already exists in DB"),
    COMPANY_DOES_NOT_EXIST("The company you're tyring to update doesn't exist"),
    COMPANY_WITH_NAME_OR_PASSWORD_ALREADY_EXIST("Company with the provided name or password already exist"),
    // customer
    Customer_Already_Exists("The Customer mail already exists therefor he's already in the DB"),
    Customer_Doesnt_Exists("Customer doesn't exists"),
    // coupon
    Coupon_Already_Exists("The Coupon Description already exists for this company"),
    Coupon_Doesnt_Exists("The coupons doesn't exists therefor can't be updated"),
    Couldnt_Add_Coupon_To_Customer("Couldn't purchase the specific coupon for that customer"),
    Coupon_ID_DOESNT_FIT("THE COUPON AND THE COUPON ID DOESN'T MATCH"),
    CUSTOMER_ALREADY_PURCHASED_COUPON("The Customer already bought this coupon");
    private final String message;

    ErrorMsg(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
