package suffix_tree;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String text = "There are five types of schools in the US educational system. They are: kindergarten, elementary school, middle school, high school and private school. Children go to kindergarten when they are 5 years old They go to elementary school from ages 6 through 11 (1-5 grades), middle school from ages 12 through 14 (6-8 grades) and high school from ages 15 through 19 (9-12 grades).\n" +
                "\n" +
                "About 90 percent of all children attend public school, which is free. The other 10 percent go I private schools, which often include religious education. They are similar to the public schools but parents must pay for their children to go to these schools. About half of all private schools are run by Catholics.\n" +
                "\n" +
                "In the United States, education is mainly the responsibility of state and local governments, not the national government. The amount of money spent on education differs from state to state. The subjects studied also differ a little. The school year usually runs from September to June. At the high school level, there are some specialized schools. They include schools that emphasize vocational subjects like business or auto mechanics. Most high schools are general schools. High school students are often involved in the non-academic activities that their school offers -for example, in drama clubs, sports teams, or the school newspaper.";

        /*Использование класса SuffixTree позволяет найти вхождение любого сочетания сиволов
        * вне зависимости от их положения в тексте
        * класс SuffixTree1 при меньшем количестве создаваемых узлов позволяет найти вхождение
        * слов и сочетаний символов находящихся в начале слов*/

        SuffixTree1 suffixTree1 = new SuffixTree1(text);
        SuffixTree suffixTree = new SuffixTree(text);

        List<Integer> result = suffixTree.search("school");

        if (result != null)
        for (Integer pos : result){
            System.out.println(pos);
        }

        System.out.println("Сравнение количества узлов" + "\n" + suffixTree.getNodeCounter() + "==========" + suffixTree1.getNodeCounter());
    }
}
