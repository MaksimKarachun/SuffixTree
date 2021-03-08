package suffix_tree;

import java.util.ArrayList;
import java.util.List;

public class Node
{
    private String fragment;
    private ArrayList<Integer> nextNodes;
    private Integer startPosition;
    private int position;

    public Node(String fragment, int position, Integer suffStartPostion)
    {
        this.fragment = fragment;
        nextNodes = new ArrayList<>();
        this.position = position;
        this.startPosition = suffStartPostion;
    }

    public String getFragment()
    {
        return fragment;
    }

    public void setFragment(String fragment)
    {
        this.fragment = fragment;
    }

    public int getPosition()
    {
        return position;
    }

    public List<Integer> getNextNodes()
    {
        return nextNodes;
    }

    public void addNextNodes(Integer nodeNumber){
        nextNodes.add(nodeNumber);
    }

    public void removeNextNode(Integer nodeNumber){
        nextNodes.remove(nodeNumber);
    }

    public Integer getStartPosition()
    {
        return startPosition;
    }

}