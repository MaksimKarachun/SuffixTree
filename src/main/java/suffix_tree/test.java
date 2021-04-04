package suffix_tree;

public class test {

    public static void main(String[] args) {
        String str = "aad$asd$ohs.";
        int lastIndex = 0;
        String current;
        for (int i = 0; i < str.length(); i++){
            if (str.charAt(i) == '$' || i == str.length() - 1){
                if (i == str.length() - 1) {
                    current = str.substring(lastIndex, i + 1);
                    System.out.println(current);
                }
                else {
                    current = str.substring(lastIndex, i);
                    System.out.println(current);
                    lastIndex = i + 1;
                }
            }
        }
    }
}
