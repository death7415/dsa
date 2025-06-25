package tries.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Leetcode212 {
    static class Trie{
        public boolean endOfWord;
        public String actualWord;
        public List<Trie> children;
    }

    Trie getNode(){
        Trie newNode = new Trie();
        newNode.endOfWord = false;
        newNode.actualWord = "";
        newNode.children = new ArrayList<>(Collections.nCopies(26, null));
        return newNode;
    }


    public static void main(String[] args) {

    }
}
