package suffix_tree;

import java.util.ArrayList;
import java.util.List;

public class SuffixTree
{
    private String text;
    private ArrayList<Node> nodes;
    private Node root;
    private int nodeCounter = 0;

    public SuffixTree(String text)
    {
        this.text = text + "$";
        nodes = new ArrayList<>();
        build();
    }

    private void build()
    {
        //TODO
        root = new Node(null, 0, 0);
        nodes.add(root);

        for (int i = 0; i < text.length(); i++){
            String currentSuf = text.substring(i);
            moveOnTree(root, currentSuf, i);
        }
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
                //вхождение найдено и значит все суффиксы в ветке начинаются с данного сочетания символов
                return checkAllDaughterNodes(nextNode);
            }
            else {//if step < query.length
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
                    Integer nextNodeFind = findNextNode(node, suffPart.charAt(step - 1));
                    if (nextNodeFind == null){
                        Node newNode = new Node(suffPart.substring(step), ++nodeCounter, suffStartPosition);
                        node.addNextNodes(nodeCounter);
                        nodes.add(newNode);
                    }
                    else {
                        moveOnTree(nodes.get(nextNodeFind), suffPart.substring(step), suffStartPosition);
                    }
                }
                //если прошли часть ребра и не прошли суффиксполностью (необходимо создание новой ветки)
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
        }
    }

    private Integer checkEdge(Node node, String suff){
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

    private List<Integer> checkAllDaughterNodes(Node node){
        List<Integer> nextNodeList = node.getNextNodes();
        List<Integer> allAntries = new ArrayList<>();
        if (nextNodeList.size() == 0){
            allAntries.add(node.getStartPosition());
            return allAntries;
        }
        for (Integer nodeNumber : nextNodeList) {
            Node currentNode = nodes.get(nodeNumber);
            allAntries.addAll(checkAllDaughterNodes(currentNode));
        }
        return allAntries;
    }
}