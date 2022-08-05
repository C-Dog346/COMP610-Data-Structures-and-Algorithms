/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa_assignment_2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Callum
 */
public class Trie {

    private TrieNode root;

    private class TrieNode {

        private Character character;
        private String word;
        private HashMap<Character, TrieNode> children;
        private TrieNode parent;

        private TrieNode(Character character, TrieNode parent) {
            this.character = character;
            this.word = null;
            this.children = new HashMap<>();
            this.parent = parent;
        }

        public void addChild(Character character, TrieNode parent) {
            if (character == null) {
                throw new IllegalArgumentException("Null child my G");
            }

            this.children.put(character, new TrieNode(character, parent));
        }
    }

    public Trie() {
        this.root = new TrieNode(null, null);
    }

    public boolean add(String element) {
        if (contains(element)) {
            return false;
        }

        char[] array = element.toCharArray();

        TrieNode currentNode = this.root;

        for (int i = 0; i < array.length; i++) {
            if (currentNode.children.containsKey(array[i])) {
                currentNode = currentNode.children.get(array[i]);
            }
            else {
                currentNode.addChild(array[i], currentNode);
                currentNode = currentNode.children.get(array[i]);
            }
        }

        currentNode.word = element;

        return false;
    }

    public boolean remove(String element) {
        char[] array = element.toCharArray();

        TrieNode currentNode = this.root;

        for (int i = 0; i < array.length; i++) {
            if (currentNode.children.containsKey(array[i])) {
                currentNode = currentNode.children.get(array[i]);
            }
            else {
                return false;
            }
        }

        if (currentNode.word.equals(element)) {
            currentNode.word = null;
            while (currentNode.parent.word == null && currentNode.parent.children.size() == 1
                    && currentNode != this.root) {
                currentNode = currentNode.parent;
            }
            currentNode.parent.children.remove(currentNode.character);

            return true;
        }

        return false;
    }

    public boolean removeAll(String prefix) {
        char[] array = prefix.toCharArray();

        TrieNode currentNode = this.root;

        for (int i = 0; i < array.length; i++) {
            if (currentNode.children.containsKey(array[i])) {
                currentNode = currentNode.children.get(array[i]);
            }
            else {
                return false;
            }
        }

        while (!currentNode.parent.children.containsKey(array[prefix.length() - 1])
                && currentNode != this.root) {
            currentNode = currentNode.parent;
        }

        currentNode.parent.children.get(currentNode.character).word = null;
        currentNode.parent.children.remove(currentNode.character);

        while (currentNode.parent.word == null && currentNode.parent.children.size() == 0
                && currentNode != this.root) {
            Character character = currentNode.parent.character;
            currentNode = currentNode.parent;
            currentNode.parent.children.remove(character);
        }

        return true;
    }

    public boolean contains(String element) {
        char[] array = element.toCharArray();

        TrieNode currentNode = this.root;

        for (int i = 0; i < array.length; i++) {
            if (currentNode.children.containsKey(array[i])) {
                currentNode = currentNode.children.get(array[i]);
            }
            else {
                return false;
            }
        }

        if (currentNode.word == null) {
            return false;
        }

        return currentNode.word.equals(element);
    }

    public boolean startsWith(String prefix) {
        char[] array = prefix.toCharArray();

        TrieNode currentNode = this.root;

        for (int i = 0; i < array.length; i++) {
            if (currentNode.children.containsKey(array[i])) {
                currentNode = currentNode.children.get(array[i]);
            }
            else {
                return false;
            }
        }

        return true;
    }

    public Set<String> suggestions(String prefix) {
        char[] array = prefix.toCharArray();

        TrieNode currentNode = this.root;

        for (int i = 0; i < array.length; i++) {
            if (currentNode.children.containsKey(array[i])) {
                currentNode = currentNode.children.get(array[i]);
            }
            else {
                return null;
            }
        }

        return recursiveSuggestions(new HashSet<String>(), currentNode);
    }

    public Set<String> recursiveSuggestions(HashSet set, TrieNode currentNode) {
        if (currentNode.word != null) {
            set.add(currentNode.word);
        }

        for (Map.Entry<Character, TrieNode> k : currentNode.children.entrySet()) {
            recursiveSuggestions(set, k.getValue());
        }

        return set;
    }

    @Override
    public String toString() {
        return recursiveString(root, 0);
    }

    private String recursiveString(TrieNode current, int level) {
        String levelString = "";
        if (current.children.size() > 0) {
            Set<Character> chars = current.children.keySet();
            String tabs = "";
            for (int i = 0; i < level; i++) {
                tabs += "\t";
            }

            for (Character c : chars) {
                TrieNode child = current.children.get(c);
                levelString += tabs + " [" + c + "]";
                if (child.word != null) {
                    levelString += " >> " + child.word;
                }
                levelString += "\n";
                levelString += recursiveString(child, level + 1);
            }
        }
        return levelString;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.add("Fis");
        trie.add("Fisherman");
        trie.add("Fishing");
        trie.add("Beef");
        trie.add("Beefy");
        trie.add("Beer");
        trie.add("Beer");
        trie.add("Fingers");
        trie.add("Watermelon");
        trie.add("Wagyu");
        trie.add("A");
        trie.add("Fibbing");
        trie.add("Fish");
        System.out.println(trie.toString());

        System.out.println("Does the trie contain 'Beer'?: " + trie.contains("Beer"));

        System.out.println("Does the trie contain the prefix 'Bee'?: " + trie.startsWith("Bee"));
        System.out.println("Does the trie contain the prefix 'Glo'?: " + trie.startsWith("Glo"));

        trie.remove("Fisherman");
        System.out.println(trie.toString());
        trie.remove("Watermelon");
        System.out.println(trie.toString());
        trie.remove("A");
        System.out.println(trie.toString());

        System.out.println("Does a word start with 'Fi'?: " + trie.startsWith("Fi"));

        System.out.println(trie.suggestions("Fish"));
        System.out.println(trie.suggestions("Bee"));
        System.out.println(trie.suggestions("Fishermans"));

        trie.removeAll("Fish");
        System.out.println(trie.toString());

    }

}
