public class Palindrome {
    
    public static boolean isPal(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }

        LLStack<Character> stack = new LLStack<>();
        
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isLetter(c)) {
                stack.push(Character.toLowerCase(c)); 
            }
        }

        int front = 0;
        while (!stack.isEmpty()) {
            while (front < str.length() && !Character.isLetter(str.charAt(front))) {
                front++;
            }

            if (front >= str.length()) {
                break;
            }

            char top = stack.pop();
            char originalChar = Character.toLowerCase(str.charAt(front++));
            
            if (originalChar != top) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("--- Testing method isPal ---");
        System.out.println();

        System.out.println("(0) Testing on \"A man, a plan, a canal, Panama!\"");
        try {
            boolean result = isPal("A man, a plan, a canal, Panama!");
            System.out.println("actual results: " + result);
            System.out.println("expected results: true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(result == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
    }
}