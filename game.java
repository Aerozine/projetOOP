import java.io.IOException;
import java.util.Scanner;

public class game {
    public static void main(String[] args)
    {
        tree gametree;
        try
        {
            gametree = new tree("textfile.txt");
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        String answer = "y";
        Scanner scanner = new Scanner(System.in);
        do
        {
            if (answer.equals("y")) runGame(gametree);
            System.out.println("wanna retry ?");
            answer = scanner.nextLine();
        } while (!answer.equals("n"));
        scanner.close();
    }

    public static void runGame(tree gametree)
    {
        System.out.println(gametree.getInitext());
        Node node = gametree.getFirstNode();
        Node prev = node;
        Scanner scanner = new Scanner(System.in);
        String answer;
        // while it's a question
        while (node instanceof NodeBranch)
        {
            do
            {
                System.out.println(node.getData());
                answer = scanner.nextLine();
            } while (!answer.equals("y") && !answer.equals("n"));
            if (answer.equals("y"))
            {
                prev = node;
                node = ((NodeBranch) node).getPositive();
            } else
            {
                prev = node;
                node = ((NodeBranch) node).getNegative();
            }
            if (node == null)
                throw new IllegalArgumentException("the game tree is incorrect at " + (gametree.getIndex(prev) + 1));
        }
        do
        {
            System.out.println("Does it is" + node.getData());
            answer = scanner.nextLine();
        } while (!answer.equals("y") && !answer.equals("n"));
        if (answer.equals("n"))
        {
            System.out.println("I am unable to guess, you have won !");
            System.out.println("What did you chose ?");
            String newValue = scanner.nextLine();
            System.out.println("What question could I ask to distinguish a " + newValue + " from a " + node.getData() + " ?");
            String newQuestion = scanner.nextLine();
            do
            {
                System.out.println("For a " + newValue + ", would you answer yes or no to this question ?");
                answer = scanner.nextLine();
            } while (!answer.equals("y") && !answer.equals("n"));
            try
            {
                //reshaping the tree
                gametree.reshape(prev, node, newQuestion, newValue, answer.equals("y"));
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }

        } else System.out.println("bingo");
    }
}
