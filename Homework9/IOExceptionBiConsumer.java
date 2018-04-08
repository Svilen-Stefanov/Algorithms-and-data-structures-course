import java.io.IOException;

/**
 * Dieses {@link FunctionalInterface} implementiert einen
 * {@link BiConsumer}, der eine {@link IOException} werfen kann.
 */
@FunctionalInterface
public interface IOExceptionBiConsumer<T, U> {
  void accept(T t, U u) throws IOException;
  
}
