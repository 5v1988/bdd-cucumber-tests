package enabler;

import java.util.HashMap;

public class TestContext<K,V> extends HashMap<K,V> {

  @Override
  public V get(Object key) {
    return super.get(key);
  }

  @Override
  public V put(K key, V value) {
    return super.put(key, value);
  }
}
