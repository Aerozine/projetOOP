import java.io.FileNotFoundException;
import java.util.Scanner;

public class game {
    public static void main(String[] args) throws FileNotFoundException {
        tree gametree=new tree("textfile.txt");
        String answer=null;
        Scanner scanner=new Scanner(System.in);
        do {
            rungame(gametree);
            System.out.println("wanna retry ?");
            answer=scanner.nextLine();
        }while (answer.equals("y") || answer.equals("n"));
    }
    public static void rungame(tree gametree){
    System.out.println(gametree.getInitext());
    Node node = gametree.getFirstNode();
    Node prev = node;
    Scanner scanner=new Scanner(System.in);
    String answer=null;
    while(node instanceof NodeBranch){
        do {
            System.out.println(node.getData());
            answer=scanner.nextLine();
        }while (!answer.equals("y") && !answer.equals("n"));

        if(answer.equals("y")) {
            prev = node;
            node = ((NodeBranch) node).getPositive();
        }else {
            prev = node;
            node = ((NodeBranch) node).getNegative();
        }
        }
        do {
            System.out.println("Does it is"+node.getData());
            answer=scanner.nextLine();
        }while (!answer.equals("y") && !answer.equals("n"));
        if(answer.equals("n")){
            System.out.println("I am unable to guess, you have won !");
            System.out.println("What did you chose ?");
            String newValue=scanner.nextLine();
            System.out.println("What question could I ask to distinguish a " + newValue + " from a "+node.getData()+ " ?");
            String newQuestion=scanner.nextLine();
            do {
                System.out.println("For a "+newValue+", would you answer yes or no to this question ?");
                answer=scanner.nextLine();
            }while (!answer.equals("y") && !answer.equals("n"));
            gametree.reshape(prev,node,newQuestion,newValue,answer.equals("y"));

        }else
            System.out.println("bingo");



    }
}
