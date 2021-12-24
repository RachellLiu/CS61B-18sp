public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        ArrayDeque<Character> wordDeque = new ArrayDeque<>();
        for (char ch: word.toCharArray()){
            wordDeque.addLast(ch);
        }
        return wordDeque;
    }

    public boolean isPalindrome(String word){
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindrome_helper(wordDeque);
    }

    private boolean isPalindrome_helper(Deque<Character> wordDeque){
        if (wordDeque.size() <= 1) {
            return true;
        }
        else if (wordDeque.removeFirst() == wordDeque.removeLast()) {
            return isPalindrome_helper(wordDeque);
        }
        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindrome_helper(wordDeque, cc);
    }

    private boolean isPalindrome_helper(Deque<Character> wordDeque, CharacterComparator cc) {
        if (wordDeque.size() <= 1) {
            return true;
        }
        char first = wordDeque.removeFirst();
        char last = wordDeque.removeLast();
        return (cc.equalChars(first, last) && isPalindrome_helper(wordDeque, cc));
    }
}
