
public interface Sampler<T> {

    public void add(T item, int value);
    public T output();
}