package com.douye.map;

import com.douye.model.Key;

public class MapTest {
    static void test1() {
        Map<String, Integer> map = new TreeMap<>();
        map.put("c", 2);
        map.put("a", 5);
        map.put("b", 6);
        map.put("a", 8);
        map.put("g", 5);
        map.put("h", 6);
        map.put("y", 8);
        map.remove("g");
        System.out.println(map.get("b"));
        map.remove("a");


        map.traversal(new Map.Visitor<String, Integer>() {
            public boolean visit(String key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }

    static void test2() {
        Map<String, Integer> map = new HashMap_v1<>();
        map.put("c", 2);
        map.put("a", 5);
        map.put("b", 6);
        map.put("a", 8);
        map.remove("b");
        System.out.println(map.containsValue(2));
        System.out.println(map.containsValue(10));
        System.out.println(map.containsKey("b"));
        System.out.println(map.containsKey("a"));

        map.traversal(new Map.Visitor<String, Integer>() {
            public boolean visit(String key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }

    static void testHashMapV1(){
        HashMap_v1<Key,Integer> hashMap_v1 = new HashMap_v1<>();
        for (int i = 0; i < 15; i++) {
            hashMap_v1.put(new Key(i),i);
        }
        //System.out.println(hashMap_v1.get(new Key(15)));
        System.out.println(hashMap_v1.size());
        hashMap_v1.print();
    }
    public static void main(String[] args) {
        testHashMapV1();
    }
}
