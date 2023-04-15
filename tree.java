import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class tree {
    private final String initext;
    private final Vector<Node> nodeArray;
    private final String Filename;


    public tree(String Filename) throws Exception
    {
        this.Filename = Filename;
        Scanner scanner;
        scanner = new Scanner(new File(Filename));
        int numberOfLine = scanner.nextInt();
        initext = scanner.nextLine();
        nodeArray = new Vector<>(numberOfLine);
        Stack<Integer> cords = new Stack<>(); // store the number who link to another node temporary
        while (scanner.hasNextLine())
        {
            // if it s a question, set the element as a branch and set theirs branch
            String type = scanner.next();
            if (type.equals("?"))
            {
                cords.push(scanner.nextInt() - 1);
                cords.push(scanner.nextInt() - 1);
                nodeArray.add(new NodeBranch(scanner.nextLine()));
            } else if (type.equals("="))
            {
                nodeArray.add(new Node(scanner.nextLine()));
            } else
            {
                // throw an error if it's not a = or a?
                throw new IllegalArgumentException("a line isn't a question nor a response");
            }
        }
        //create link between elements in array
        for (int i = numberOfLine - 1; i >= 0; i--)
        {
            if (nodeArray.get(i) instanceof NodeBranch && !cords.empty())
            {
                Node node = nodeArray.get(i);
                ((NodeBranch) node).setNegative(nodeArray.get(cords.pop()));
                ((NodeBranch) node).setPositive(nodeArray.get(cords.pop()));
            }
        }

        scanner.close();
    }

    public String getInitext()
    {
        return initext;
    }

    public Node getFirstNode()
    {
        return nodeArray.get(0);
    }

    public int getIndex(Node node)
    {
        return nodeArray.indexOf(node);
    }

    //to beautify but it works
    public void reshape(Node question, Node Answer, String newQuestion, String alternativeAnswer, boolean alternativeIsYes) throws IOException
    {
        //creating the new node
        nodeArray.add(new Node(alternativeAnswer));
        //depending on if the new value is positive or negative, create a new question
        if (alternativeIsYes) nodeArray.add(new NodeBranch(nodeArray.lastElement(), Answer, newQuestion));
        else nodeArray.add(new NodeBranch(Answer, nodeArray.lastElement(), newQuestion));
        //modify the link to the question to the new question
        if (((NodeBranch) question).getPositive() == Answer)
            ((NodeBranch) question).setPositive(nodeArray.lastElement());
        else ((NodeBranch) question).setNegative(nodeArray.lastElement());
        //update the file
        updateFile((NodeBranch) question);
    }

    private String NodeToString(Node q)
    {
        if (q instanceof NodeBranch)
        {
            // get the positive and negative node values
            int pos = nodeArray.indexOf(((NodeBranch) q).getPositive()) + 1;
            int neg = nodeArray.indexOf(((NodeBranch) q).getNegative()) + 1;
            return "? " + pos + " " + neg + " " + q.getData();
        }
        return "= " + q.getData();
    }

    public void updateFile(NodeBranch question) throws IOException
    {
        //+1 because we have the inittext
        int index = nodeArray.indexOf(question) + 1;
        //read all the files
        Path path = Paths.get(Filename);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        //modifying the question node
        lines.set(index, NodeToString(question));
        // adding the new question and the new answer
        lines.add(NodeToString(nodeArray.get(nodeArray.size() - 2)));
        lines.add(NodeToString(nodeArray.lastElement()));
        //write all changes
        Files.write(path, lines, StandardCharsets.UTF_8);
    }
}