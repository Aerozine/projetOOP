public class NodeBranch extends Node
{
    public Node positive;
    public Node negative;

    public NodeBranch(Node positive , Node negative,String data)
    {
        super(data);
        this.positive=positive;
        this.negative=negative;
    }
}