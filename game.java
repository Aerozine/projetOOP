import java.io.IOException;
import java.util.Scanner;

public class game {
    public static boolean posAns(String answer){
        return answer.equals("Y") || answer.equals("yes") || answer.equals("Yes") || answer.equals("y");
    }
    public static boolean negAns(String answer){
        return answer.equals("Nn") || answer.equals("no") || answer.equals("No") || answer.equals("n");
    }
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
            if (posAns(answer)) runGame(gametree);
            System.out.println("Do you want to retry ?");
            answer = scanner.nextLine();
        } while (!negAns(answer));
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
            } while (!posAns(answer) && !negAns(answer));
            if (posAns(answer))
            {
                prev = node;
                node = ((NodeBranch) node).getPositive();
            } else
            {
                prev = node;
                node = ((NodeBranch) node).getNegative();
            }
            if (node == null)
                throw new IllegalArgumentException("The game tree is incorrect at line " + (gametree.getIndex(prev) + 1));
        }
        do
        {
            System.out.println("Is it" + node.getData());
            answer = scanner.nextLine();
        } while (!posAns(answer) && !negAns(answer));
        if (negAns(answer))
        {
            System.out.println("I am unable to guess, you have won !");
            System.out.println("What did you chose ?");
            String newValue = scanner.nextLine();
            System.out.println("What question could I ask to distinguish " + newValue + " from " + node.getData() + " ?");
            String newQuestion = scanner.nextLine();
            do
            {
                System.out.println("For " + newValue + ", would you answer yes or no to this question ?");
                answer = scanner.nextLine();
            } while (!posAns(answer) && !negAns(answer));
            System.out.println("Thank you!");
            try
            {
                //reshaping the tree
                gametree.reshape(prev, node, newQuestion, newValue, posAns(answer));
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }

        } else System.out.println("Bingo !");
    }
}
