package bstmap;

import org.apache.commons.math3.exception.NullArgumentException;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private int size;
    private BSTNode root;

    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(key, root);
    }

    private boolean containsKeyHelper(K key, BSTNode node) {
        if (node == null) {
            return false;
        }
        if (key.compareTo(node.key) > 0) {
            return containsKeyHelper(key, node.right);
        } else if (key.compareTo(node.key) < 0) {
            return containsKeyHelper(key, node.left);
        } else {
            return true;
        }
    }

    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    private V getHelper(K key, BSTNode node) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            return getHelper(key, node.right);
        } else if (key.compareTo(node.key) < 0) {
            return getHelper(key, node.left);
        } else {
            return node.value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    private void NullArgumentException(K key) {
        if (key == null)
            throw new NullArgumentException();
    }

    public void printInOrder(){
        for(K key:this){
            System.out.print(key+" ");
        }
    }

    @Override
    public void put(K key, V value) {
        NullArgumentException(key);
        if (value == null) {
            remove(key);
            return;
        }
        root = putHelper(key, value, root);
    }

    private BSTNode putHelper(K key, V value, BSTNode node) {
        if (node == null) {
            size++;
            return new BSTNode(key, value);
        }
        if (key.compareTo(node.key) > 0) {
            node.right = putHelper(key, value, node.right);
        } else if (key.compareTo(node.key) < 0) {
            node.left = putHelper(key, value, node.left);
        } else {
            node.value=value;
        }
        return node;
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> keys = new HashSet<K>();
        keys.add(keySetHelper(keys, root));
        keys.remove(null);
        return keys;
    }

    private K keySetHelper(Set<K> set, BSTNode node) {
        if (node == null)
            return null;
        set.add(keySetHelper(set, node.left));
        set.add(keySetHelper(set, node.right));
        return node.key;
    }

    private BSTNode removeHelper(K key, BSTNode node) {
        if (node == null) {
            return null;//hasn't written here.
        }
        if (key.compareTo(node.key) > 0) {
            node.right = removeHelper(key, node.right);
        } else if (key.compareTo(node.key) < 0) {
            node.left = removeHelper(key, node.left);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                BSTNode MaxNode =maxNode(node.left);
                node.value = MaxNode.value;
                node.key = MaxNode.key;
                deleteMaxNode(node.left);
            }
        }
        return node;
    }

    private BSTNode maxNode(BSTNode node) {
        if (node.right == null)
            return node;
        return maxNode(node.right);
    }

    private BSTNode deleteMaxNode(BSTNode node) {
        if (node.right == null) {
            return null;
        }
        node.right = deleteMaxNode(node.right);
        return node;
    }

    @Override
    public V remove(K key) {
        NullArgumentException(key);
        V deletedValue = get(key);
        if (deletedValue != null) {
            root=removeHelper(key, root);
            size--;
        }
        return deletedValue;
    }

    @Override
    public V remove(K key, V value) {
        NullArgumentException(key);
        if (value.equals(get(key))) {
            return remove(key);
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    private class BSTNode {
        K key;
        V value;
        BSTNode right;
        BSTNode left;

        public BSTNode(K k, V v) {
            key = k;
            value = v;
            right = null;
            left = null;
        }
    }

}
/**
 My analysis for these methods above:
 clear:Θ(1);
 containsKey:θ(logN)
 get:θ(logN)
 size:θ(1)
 put:θ(logN)
 keySet:θ(N)
 remove:θ(logN)
 */