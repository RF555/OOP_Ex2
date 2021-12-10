package Graph;

import java.io.FileNotFoundException;
import java.util.List;

public class main_Matanel {
    public static void main(String[] args) throws FileNotFoundException {
        String st = "C:\\Users\\Roey\\OneDrive - Ariel University\\Matanel&Roey\\Ex2-OOP\\data\\G1.json";
//        String st = "C:\\Users\\Roey\\OneDrive - Ariel University\\Matanel&Roey\\Ex2-OOP\\data\\G2.json";
//        String st = "C:\\Users\\Roey\\OneDrive - Ariel University\\Matanel&Roey\\Ex2-OOP\\data\\G3.json";
        JSON jsonFile = new JSON(st);
//        System.out.println(test.graph.getString("Edges"));
//        System.out.println(test.graphObj);
//        System.out.println(test.NodeJsonArr.get(0));
//        System.out.println(test.EdgeJsonArr.get(0) + "\n\n");
//        Node n = test.generateNode(1);
//        Edge e = test.generateEdge(1);
//        System.out.println(n);
//        System.out.println(e);
//        n = test.generateNode(1);
//        e = test.generateEdge(1);
//        System.out.println(n);
//        System.out.println(e);
//        n = test.generateNode(1);
//        e = test.generateEdge(1);
//        System.out.println(n);
//        System.out.println(e+"\n\n");
        List<Node> Nll = jsonFile.all_nodes_to_list();
        List<Edge> Ell = jsonFile.all_edges_to_list();
        System.out.println(Nll);


    }
}