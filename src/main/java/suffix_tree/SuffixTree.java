package suffix_tree;

import java.util.ArrayList;
import java.util.List;

public class SuffixTree
{
    private String text;
    private ArrayList<Node> nodes;
    private Node root;

    public SuffixTree(String text)
    {
        this.text = text;
        nodes = new ArrayList<>();
        build();
    }

    private void build()
    {
        //TODO
        root = new Node(null, 0);

        for (int i = 0; i < text.length(); i++){
            String currentSuf = text.substring(i);
            findNextNode(root, currentSuf.charAt(0));
        }
    }

    private List<Integer> search(String query)
    {
        ArrayList<Integer> positions = new ArrayList<>();
        //TODO
        return positions;
    }

    private void moveOnTree(Node node, String suffPart){
        Integer nextNode = findNextNode(node, suffPart.charAt(0));
        if (nextNode == null){
            Node newNode = new Node(suffPart, nodes.size());
            nodes.add(newNode);
        }
        else {
            int step = checkEdge(node, suffPart);
            if (step <= node.getFragment().length()) {
            }
            else {
                node.setFragment(suffPart.substring(step));
                Node nodePart = new Node(suffPart.substring(0, suffPart.length() - step), nodes.size());
                moveOnTree(nodePart, nodePart.getFragment());
            }
        }
    }

    private Integer checkEdge(Node node, String suff){
        char[] edgeChar = node.getFragment().toCharArray();
        char[] suffChar = suff.toCharArray();
        int i = 0;
        while (suffChar[i] == edgeChar[i]){
            i++;
        }
        return i;
    }

    private Integer findNextNode(Node node, char firstSuffChar){
        for (Integer nodeNumber : node.getNextNodes()){
            Node currentNode = nodes.get(nodeNumber);
            char[] currentNodeChars = currentNode.getFragment().toCharArray();
            if (firstSuffChar == currentNodeChars[0]){
                return nodeNumber;
            }
        }
        return null;
    }
}