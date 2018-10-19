package tech.thdev.coroutines;

public class Standard {

    public interface Block<T> {
        void block(T item);
    }

    public static <T> T apply(T item, Block<T> block) {
        block.block(item);
        return item;
    }
}
