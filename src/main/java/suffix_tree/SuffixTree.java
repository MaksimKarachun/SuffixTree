package suffix_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SuffixTree
{
    private final String text;
    private final ArrayList<Node> nodes;
    private Node root;
    private int nodeCounter = 0;

    public SuffixTree(String text)
    {
        this.text = text;
        nodes = new ArrayList<>();
        build();
    }

    private void build()
    {
        //TODO
        root = new Node(null, 0, 0);
        nodes.add(root);
        prepreTextAndBuildTree();
    }

    public List<Integer> search(String query)
    {
        //TODO
        return searchInTree(root, query);
    }

    private List<Integer> searchInTree(Node node, String query){
        Integer nextNodeNum = findNextNode(node, query.charAt(0));
        if (nextNodeNum == null){
            return null;
        }
        else {
            Node nextNode = nodes.get(nextNodeNum);
            int step = checkEdge(nextNode, query);
            if (step == query.length()){
                return nextNode.getStartPosition();
            }
            else {
                return searchInTree(nextNode, query.substring(step));
            }

        }
    }

    private void moveOnTree(Node node, String suffPart, Integer suffStartPosition){
        Integer nextNodeNum = findNextNode(node, suffPart.charAt(0));
        if (nextNodeNum == null){
            Node newNode = new Node(suffPart, ++nodeCounter, suffStartPosition);
            node.addNextNodes(nodeCounter);
            nodes.add(newNode);
        }
        else {
            Node nextNode = nodes.get(nextNodeNum);
            int step = checkEdge(nextNode, suffPart);
            if (step != suffPart.length()) {
                //если полностью прошли ребро и не прошли суффикс полностью
                if (step < suffPart.length() && step == nextNode.getFragment().length()) {
                    moveOnTree(nodes.get(nextNodeNum), suffPart.substring(step), suffStartPosition);
                }
                //если прошли часть ребра и не прошли суффикс полностью (необходимо создание новой ветки)
                if (step < nextNode.getFragment().length() && step < suffPart.length()) {
                    Node nodePart = new Node(suffPart.substring(0, step), ++nodeCounter, suffStartPosition);
                    nodes.add(nodePart);
                    node.removeNextNode(nextNodeNum);
                    node.addNextNodes(nodePart.getPosition());
                    nextNode.setFragment(nextNode.getFragment().substring(step));
                    nodePart.addNextNodes(nextNode.getPosition());
                    moveOnTree(nodePart, suffPart.substring(step), suffStartPosition);
                }
            }
            else {
                nextNode.addStartPosition(suffStartPosition);
            }
        }
    }

    private Integer checkEdge(Node node, String suff){
        char[] edgeChar = node.getFragment().toCharArray();
        char[] suffixChar = suff.toCharArray();
        int count = 0;
        int length = Math.min(suffixChar.length, edgeChar.length);
        for (int i = 0; i < length; i++) {
            if (suffixChar[i] != edgeChar[i]){
                break;
            }
            count++;
        }
        return count;
    }

    private Integer findNextNode(Node node, char firstSuffChar){
        List<Integer> nextNodeList = node.getNextNodes();
        for (Integer nodeNumber : nextNodeList){
            Node currentNode = nodes.get(nodeNumber);
            char[] currentNodeChars = currentNode.getFragment().toCharArray();
            if (firstSuffChar == currentNodeChars[0]){
                return nodeNumber;
            }
        }
        return null;
    }

    private void prepreTextAndBuildTree(){
        StringBuilder word = new StringBuilder();
        char currentChar;
        int lastIndex = 0;
        for (int i = 0; i < text.length(); i++){
            if (i == text.length() - 1 && word.length() != 0){
                moveOnTree(root, word.toString(), lastIndex);
                break;
            }

            currentChar = text.charAt(i);
            if (Character.isLetter(currentChar)) {
                word.append(currentChar);
            }
            else {
                if (word.length() != 0) {
                    moveOnTree(root, word.toString(), lastIndex);
                    lastIndex = i + 1;
                    word = new StringBuilder();
                }
                else {
                    lastIndex++;
                }
            }
        }
    }
}