#Write a method to count the frequency of characters in a string using HashMap.
Input: "banana" → Output: {b=1, a=3, n=2}

import java.util.HashMap;
import java.util.Map;

public class CharacterFrequency {
    public static Map<Character, Integer> countCharacterFrequency(String input) {
        // Initialize HashMap to store character frequencies
        Map<Character, Integer> frequencyMap = new HashMap<>();
        
        // Convert string to char array and iterate through each character
        for (char c : input.toCharArray()) {
            // Update frequency: if character exists, increment count; else, set to 1
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        
        return frequencyMap;
    }

    // Test the method
    public static void main(String[] args) {
        String input = "banana";
        System.out.println(countCharacterFrequency(input)); // Output: {b=1, a=3, n=2}
    }
}
