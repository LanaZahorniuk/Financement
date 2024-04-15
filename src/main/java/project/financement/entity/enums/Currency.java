package project.financement.entity.enums;

public enum Currency {
    USD("$"),
    EUR("€"),
    JPY("¥"),
    GBP("£"),
    AUD("A$"),
    PLN("zł"),
    UAH("₴"),
    CAD("C$"),
    CNY("¥"),
    CHF("CHF"),
    INR("₹"),
    BRL("R$"),
    SEK("kr"),
    NZD("NZ$"),
    SGD("S$"),
    MXN("$"),
    ZAR("R");

    private final String symbol;

    Currency(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
