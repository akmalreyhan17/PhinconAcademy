public class Box<T extends Number & Comparable<T>> {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public double doubleValue() {
        return value.doubleValue();
    }
}


