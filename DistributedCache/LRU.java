package DistributedCache;

import java.util.HashMap;

class Node{
    Node next;
    Node prev;
    String val;

    Node(String val){
        this.val = val;
        next = null;
        prev = null;
    }
}

public class LRU implements EvictionPolicy{
    Node root;
    Node tail;
    HashMap<String, Node> mapping;

    LRU(){
        root = null;
        tail = null;
        mapping = new HashMap<>();
    }

    public void update(String key){
        Node temp;

        if(mapping.containsKey(key)){
            temp = mapping.get(key);

            if(tail == temp) tail = ((tail.prev != null) ? tail.prev : root);

            if(temp.prev != null) temp.prev.next = temp.next;
            if(temp.next != null) temp.next.prev = temp.prev;
            
            temp.prev = null;
        }
        else{
            temp = new Node(key);
            mapping.put(key, temp);

            if(root == null) tail = temp;
        }

        
        temp.next = root;
        root = temp;
    }

    public String getLeastRecentlyUsed(){
        if(tail == null) return null;

        String val = tail.val;
        if(tail.prev == null){
            root = null;
            tail = null;
        }
        else{
            Node temp = tail.prev;
            temp.next = null;
            tail.prev = null;
        }

        mapping.remove(val);
        return val;
    }

    public void removeNode(String key){
        Node temp = mapping.get(key);
        if(temp == root){
            root = temp.next;
            if(root != null) root.prev = null;
        }
        else if(temp == tail){
            tail = temp.prev;
            if(tail != null) tail.next = null;
        }
        else{
            Node prv = temp.prev;
            Node nxt = temp.next;

            prv.next = nxt;
            nxt.prev = prv;
        }

        temp.next = null;
        temp.prev = null;
        mapping.remove(key);
    }
}
