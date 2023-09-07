package csci2320.collections2;

public interface Map<K, V> {
  void put(K key, V value);
  V get(K key);
  V remove(K key);
  int size();
  boolean isEmpty();
}
