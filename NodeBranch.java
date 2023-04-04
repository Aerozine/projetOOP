public class NodeBranch extends Node {
    private Node positive;
    private Node negative;

    public NodeBranch(Node positive, Node negative, String data) {
        super(data);
        this.positive = positive;
        this.negative = negative;

    }

    public NodeBranch(String data) {
        super(data);
    }

    public Node getPositive() {
        return positive;
    }

    public void setPositive(Node positive) {
        this.positive = positive;
    }

    public Node getNegative() {
        return negative;
    }

    public void setNegative(Node negative) {
        this.negative = negative;
    }
}