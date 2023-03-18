public class NodeBranch extends Node {
    public Node positive;
    public Node negative;

    public NodeBranch(Node positive , Node negative,String data) {
        super(data);
        System.out.println("coucou");
        this.positive=positive;
        System.out.println("couddcou");
        this.negative=negative;

    }
}