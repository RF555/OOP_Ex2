import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class main_Roey {
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
//        System.out.println(Nll);

        String G1 = "C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\data\\G1.json";
        String G2 = "C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\data\\G2.json";
        String G3 = "C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\data\\G3.json";
        String g1 = "C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\jsonFiles\\my_g1.json";
        String g2 = "C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\jsonFiles\\my_g2.json";
        JSON g1_json=new JSON(g1);
        JSON g2_json=new JSON(g2);
        JSON G1_json=new JSON(G1);
        JSON G2_json=new JSON(G2);
        JSON G3_json=new JSON(G3);
        String g1_output="C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\g1_output.json";
        String g2_output="C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\g2_output.json";
        String G1_output="C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\G1_output.json";
        String G2_output="C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\G2_output.json";
        String G3_output="C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\G3_output.json";
//        g1_json.toJSONFile(g1_output);
//        g2_json.toJSONFile(g2_output);
//        G1_json.toJSONFile(G1_output);
//        G2_json.toJSONFile(G2_output);
//        G3_json.toJSONFile(G3_output);
//        System.out.println(Nll);
        DWGraph g=new DWGraph(G1_json);
        g.getNode(0).setInfo(g.getNode(1).toString());
        System.out.println(g.getNode(0).getInfo());



    }
}