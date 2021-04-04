package suffix_tree;

import java.util.ArrayList;
import java.util.List;

public class SuffixTree1
{
    private String text;
    private ArrayList<Node1> nodes;
    private Node1 root;
    private int nodeCounter = 0;

    public SuffixTree1(String text)
    {
        this.text = text;
        nodes = new ArrayList<>();
        build();
    }

    private void build()
    {
        //TODO
        root = new Node1(null, 0, 0);
        nodes.add(root);

        int lastIndex = 0;
        for (int i = 0; i < text.length(); i++){
            if (text.charAt(i) == ' ' || i == text.length() - 1){
                if (i == text.length() - 1) {
                    moveOnTree(root, text.substring(lastIndex, i + 1), lastIndex);
                }
                else {
                    moveOnTree(root, text.substring(lastIndex, i), lastIndex);
                    lastIndex = i + 1;
                }
            }
        }
    }

    public List<Integer> search(String query)
    {
        //TODO
        return searchInTree(root, query);
    }

    private List<Integer> searchInTree(Node1 node, String query){
        Integer nextNodeNum = findNextNode(node, query.charAt(0));
        if (nextNodeNum == null){
            return null;
        }
        else {
            Node1 nextNode = nodes.get(nextNodeNum);
            int step = checkEdge(nextNode, query);
            if (step == query.length()){
                //вхождение найдено и значит все суффиксы в ветке начинаются с данного сочетания символов
                return checkAllDaughterNodes(nextNode);
            }
            else {//if step < query.length
                return searchInTree(nextNode, query.substring(step));
            }

        }
    }

    private void moveOnTree(Node1 node, String suffPart, Integer suffStartPosition){
        Integer nextNodeNum = findNextNode(node, suffPart.charAt(0));
        if (nextNodeNum == null){
            Node1 newNode = new Node1(suffPart, ++nodeCounter, suffStartPosition);
            node.addNextNodes(nodeCounter);
            nodes.add(newNode);
        }
        else {
            Node1 nextNode = nodes.get(nextNodeNum);
            int step = checkEdge(nextNode, suffPart);
            if (step != suffPart.length()) {
                //если полностью прошли ребро и не прошли суффикс полностью
                if (step < suffPart.length() && step == nextNode.getFragment().length()) {
                    Integer nextNodeFind = findNextNode(node, suffPart.charAt(step - 1));
                    if (nextNodeFind == null){
                        Node1 newNode = new Node1(suffPart.substring(step), ++nodeCounter, suffStartPosition);
                        node.addNextNodes(nodeCounter);
                        nodes.add(newNode);
                    }
                    else {
                        moveOnTree(nodes.get(nextNodeFind), suffPart.substring(step), suffStartPosition);
                    }
                }
                //если прошли часть ребра и не прошли суффиксполностью (необходимо создание новой ветки)
                if (step < nextNode.getFragment().length() && step < suffPart.length()) {
                    Node1 nodePart = new Node1(suffPart.substring(0, step), ++nodeCounter, suffStartPosition);
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

    private Integer checkEdge(Node1 node, String suff){
        char[] edgeChar = node.getFragment().toCharArray();
        char[] suffChar = suff.toCharArray();
        int count = 0;
        int length = Math.min(suffChar.length, edgeChar.length);
        for (int i = 0; i < length; i++) {
            if (suffChar[i] != edgeChar[i]){
                break;
            }
            count++;
        }
        return count;
    }

    private Integer findNextNode(Node1 node, char firstSuffChar){
        List<Integer> nextNodeList = node.getNextNodes();
        for (Integer nodeNumber : nextNodeList){
            Node1 currentNode = nodes.get(nodeNumber);
            char[] currentNodeChars = currentNode.getFragment().toCharArray();
            if (firstSuffChar == currentNodeChars[0]){
                return nodeNumber;
            }
        }
        return null;
    }

    private List<Integer> checkAllDaughterNodes(Node1 node){
        List<Integer> nextNodeList = node.getNextNodes();
        List<Integer> allAntries = new ArrayList<>();
        if (nextNodeList.size() == 0){
            allAntries.addAll(node.getStartPosition());
            return allAntries;
        }
        for (Integer nodeNumber : nextNodeList) {
            Node1 currentNode = nodes.get(nodeNumber);
            allAntries.addAll(checkAllDaughterNodes(currentNode));
        }
        return allAntries;
    }

    public int getNodeCounter(){
        return nodeCounter;
    }
}