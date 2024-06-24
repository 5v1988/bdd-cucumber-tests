package enabler.util;

public interface Formatters {

  default String sf(String string, Object... o){
    return String.format(string, o);
  }
}
