package common;

public enum EOrder {
    ASCENDING("asc"), DESCENDING("desc");

    private String order;

    EOrder() {
    }

    EOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return order;
    }
}
