package suffix_tree;

import java.util.ArrayList;
import java.util.List;

public class Node1
{
    private String fragment;
    private ArrayList<Integer> nextNodes;
    private ArrayList<Integer> startPosition;
    private Integer position;

    public Node1(String fragment, int position, Integer suffStartPostion)
    {
        this.fragment = fragment;
        nextNodes = new ArrayList<>();
        this.position = position;
        startPosition = new ArrayList<>();
        startPosition.add(suffStartPostion);
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

    public void addStartPosition(Integer position){
        startPosition.add(position);
    }

    public ArrayList<Integer> getStartPosition()
    {
        return startPosition;
    }

}