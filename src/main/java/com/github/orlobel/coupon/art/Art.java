package com.github.orlobel.coupon.art;

public enum Art {
    login_Manager(" +-+-+-+-+-+-+-+-+-+-+-+-+-+\n" +
        " |l|o|g|i|n|_|M|a|n|a|g|e|r|\n" +
        " +-+-+-+-+-+-+-+-+-+-+-+-+-+"),
    Initiating_Data(" +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n" +
        " |I|n|i|t|i|a|t|i|n|g|_|D|a|t|a|\n" +
        " +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+"),
    Service_Layer_Finished(" +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n" +
        " |S|e|r|v|i|c|e|_|L|a|y|e|r|_|F|i|n|i|s|h|e|d|\n" +
        " +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+"),
    Finished_Initiating_Data(" +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n" +
        " |F|i|n|i|s|h|e|d|_|I|n|i|t|i|a|t|i|n|g|_|D|a|t|a|\n" +
        " +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+"),
    Admin_Service(" +-+-+-+-+-+-+-+-+-+-+-+-+-+\n" +
        " |A|d|m|i|n|_|S|e|r|v|i|c|e|\n" +
        " +-+-+-+-+-+-+-+-+-+-+-+-+-+"),
    Company_Service(" +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n" +
        " |C|o|m|p|a|n|y|_|S|e|r|v|i|c|e|\n" +
        " +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+"),
    Customer_Service(" +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n" +
        " |C|u|s|t|o|m|e|r|_|S|e|r|v|i|c|e|\n" +
        " +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");

    private final String asciiArt;

    public String getAsciiArt() {
        return asciiArt;
    }

    Art(String asciiArt) {
        this.asciiArt = asciiArt;
    }
}
