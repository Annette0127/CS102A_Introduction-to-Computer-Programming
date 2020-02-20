package sustech.cs102a.lab10;

public enum PhoneModel {
    IPHONE(9999),
    HUAWEI(8888),
    PIXEL(6666),
    SAMSUNG(9399),
    LG(5588);

    private final int price;

    private PhoneModel(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

}
