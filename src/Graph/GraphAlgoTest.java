package Graph;

import api.NodeData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GraphAlgoTest {
    //roeyf
//    String G1 = "C:\\Users\\roeyf\\Documents\\GitHub\\OOP_Ex2\\data\\G1.json";
//    String G2 = "C:\\Users\\roeyf\\Documents\\GitHub\\OOP_Ex2\\data\\G2.json";
//    String G3 = "C:\\Users\\roeyf\\Documents\\GitHub\\OOP_Ex2\\data\\G3.json";
//    String my_g1 = "C:\\Users\\roeyf\\Documents\\GitHub\\OOP_Ex2\\src\\jsonFiles\\my_g1.json";
//    String my_g2 = "C:\\Users\\roeyf\\Documents\\GitHub\\OOP_Ex2\\src\\jsonFiles\\my_g2.json";
//    String g1000 = "C:\\Users\\roeyf\\Documents\\GitHub\\OOP_Ex2\\src\\jsonFiles\\1000Nodes.json";
//    String g10000 = "C:\\Users\\roeyf\\Documents\\GitHub\\OOP_Ex2\\src\\jsonFiles\\10000Nodes.json";


    // Roey
    String G1 = "C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\data\\G1.json";
    String G2 = "C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\data\\G2.json";
    String G3 = "C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\data\\G3.json";
    String my_g1 = "C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\jsonFiles\\my_g1.json";
    String my_g2 = "C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\jsonFiles\\my_g2.json";
    String g1000 = "C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\jsonFiles\\1000Nodes.json";
    String g10000 = "C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\jsonFiles\\10000Nodes.json";


    GraphAlgo ga1 = new GraphAlgo(new DWGraph(my_g1));
    GraphAlgo ga2 = new GraphAlgo(new DWGraph(my_g2));
    GraphAlgo Ga1 = new GraphAlgo(new DWGraph(G1));
    GraphAlgo Ga2 = new GraphAlgo(new DWGraph(G2));
    GraphAlgo Ga3 = new GraphAlgo(new DWGraph(G3));

//    String g1 = "C:\\Users\\Matanel\\IdeaProjects\\OOP=Ex3\\src\\jsonFiles\\g1.json";
//    DWGraph MyGraph = new DWGraph(g1);
//    GraphAlgo myGraph = new GraphAlgo(MyGraph);

//    @Test
//    void TestTranspose() {
//        Boolean flag = myGraph.isConnected();
//        Assertions.assertTrue(flag);
//    }
//
//    @Test
//    void test() {
//        int i = 3;
//        PriorityQueue<EdgeData> pq = new PriorityQueue<EdgeData>(ga2.edegs.get(i).size());
//        pq.addAll((Collection<? extends EdgeData>) ga2.edegs.get(i).values());
//        System.out.println(pq);
//
//    }


    @Test
    void shortestPathDistTest1() {
        Assertions.assertEquals(ga1.shortestPathDist(0, 0), 0);
        Assertions.assertEquals(ga1.shortestPathDist(1, 1), 0);
        Assertions.assertEquals(ga1.shortestPathDist(2, 2), 0);
        Assertions.assertEquals(ga1.shortestPathDist(3, 3), 0);
        Assertions.assertEquals(ga1.shortestPathDist(0, 1), 1);
        Assertions.assertEquals(ga1.shortestPathDist(1, 0), 1);
        Assertions.assertEquals(ga1.shortestPathDist(2, 1), 1);
        Assertions.assertEquals(ga1.shortestPathDist(1, 2), 1);
        Assertions.assertEquals(ga1.shortestPathDist(2, 3), 1);
        Assertions.assertEquals(ga1.shortestPathDist(3, 2), 1);
        Assertions.assertEquals(ga1.shortestPathDist(3, 1), 2);
        Assertions.assertEquals(ga1.shortestPathDist(0, 2), 2);
        Assertions.assertEquals(ga1.shortestPathDist(0, 3), 3);
        Assertions.assertEquals(ga1.shortestPathDist(1, 3), 2);
        Assertions.assertEquals(ga1.shortestPathDist(1, 3), 2);
        Assertions.assertEquals(ga1.shortestPathDist(3, 1), 2);
        Assertions.assertEquals(ga1.shortestPathDist(3, 0), 3);

    }

    @Test
    void shortestPathDistTest2() {
        Assertions.assertEquals(ga2.shortestPathDist(0, 0), 0);
        Assertions.assertEquals(ga2.shortestPathDist(1, 1), 0);
        Assertions.assertEquals(ga2.shortestPathDist(2, 2), 0);
        Assertions.assertEquals(ga2.shortestPathDist(3, 3), 0);
        Assertions.assertEquals(ga2.shortestPathDist(4, 4), 0);
        Assertions.assertEquals(ga2.shortestPathDist(0, 1), 3);
        Assertions.assertEquals(ga2.shortestPathDist(0, 2), 6);
        Assertions.assertEquals(ga2.shortestPathDist(0, 3), 9);
        Assertions.assertEquals(ga2.shortestPathDist(1, 0), 10);
        Assertions.assertEquals(ga2.shortestPathDist(1, 2), 3);
        Assertions.assertEquals(ga2.shortestPathDist(1, 3), 6);
        Assertions.assertEquals(ga2.shortestPathDist(0, 4), 8);
        Assertions.assertEquals(ga2.shortestPathDist(4, 0), 12);
        Assertions.assertEquals(ga2.shortestPathDist(2, 1), 5);
        Assertions.assertEquals(ga2.shortestPathDist(1, 4), 5);
        Assertions.assertEquals(ga2.shortestPathDist(3, 0), 11);
    }

    @Test
    void shortestPathListTest1() {
        List<NodeData> nodeList01 = ga1.shortestPath(0, 1);
        Assertions.assertEquals(nodeList01.get(0).getKey(), 0);
        Assertions.assertEquals(nodeList01.get(1).getKey(), 1);
        List<NodeData> nodeList02 = ga1.shortestPath(0, 2);
        Assertions.assertEquals(nodeList02.get(0).getKey(), 0);
        Assertions.assertEquals(nodeList02.get(1).getKey(), 1);
        Assertions.assertEquals(nodeList02.get(2).getKey(), 2);
        List<NodeData> nodeList20 = ga1.shortestPath(2, 0);
        Assertions.assertEquals(nodeList20.get(0).getKey(), 2);
        Assertions.assertEquals(nodeList20.get(1).getKey(), 1);
        Assertions.assertEquals(nodeList20.get(2).getKey(), 0);
        List<NodeData> nodeList03 = ga1.shortestPath(0, 3);
        Assertions.assertEquals(nodeList03.get(0).getKey(), 0);
        Assertions.assertEquals(nodeList03.get(1).getKey(), 1);
        Assertions.assertEquals(nodeList03.get(2).getKey(), 2);
        Assertions.assertEquals(nodeList03.get(3).getKey(), 3);
        List<NodeData> nodeList30 = ga1.shortestPath(3, 0);
        Assertions.assertEquals(nodeList30.get(0).getKey(), 3);
        Assertions.assertEquals(nodeList30.get(1).getKey(), 2);
        Assertions.assertEquals(nodeList30.get(2).getKey(), 1);
        Assertions.assertEquals(nodeList30.get(3).getKey(), 0);
    }

    GraphAlgo ga1000 = new GraphAlgo(new DWGraph(g1000));
    GraphAlgo ga10000 = new GraphAlgo(new DWGraph(g10000));

    /*
    from whatsapp group:
    G1 center 8
    G2 center 0
    G3 center 40
    Center 1000 nodes = id:362
    Center 10000 nodes = id:3846
    **/

    @Test
    void centerTest() {
        // CHECK THE DIST OF THOSE OPTIONS, IF THE SAME IT'S FINE
        Assertions.assertEquals(Ga1.center().getKey(), 8);
        Assertions.assertEquals(Ga2.center().getKey(), 0);
        Assertions.assertEquals(Ga3.center().getKey(), 40);
        Assertions.assertEquals(ga1.center().getKey(), 1);
        Assertions.assertEquals(ga2.center().getKey(), 2);
        List<NodeData> NL = new LinkedList<>();
        int[] centerNodeID = new int[1];
        double[] dist = new double[1];
        ga2.center(NL, centerNodeID, dist);
        Assertions.assertEquals(dist[0], 7);
        Assertions.assertEquals(ga1000.center().getKey(),362);
//        Assertions.assertEquals(ga10000.center().getKey(),3846);
    }

    //roeyf
//    String my_g1_output = "C:\\Users\\roeyf\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\my_g1_output.json";
//    String my_g2_output = "C:\\Users\\roeyf\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\my_g2_output.json";
//    String G1_output = "C:\\Users\\roeyf\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\G1_output.json";
//    String G2_output = "C:\\Users\\roeyf\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\G2_output.json";
//    String G3_output = "C:\\Users\\roeyf\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\G3_output.json";

    //Roey
    String my_g1_output = "C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\my_g1_output.json";
    String my_g2_output = "C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\my_g2_output.json";
    String G1_output = "C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\G1_output.json";
    String G2_output = "C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\G2_output.json";
    String G3_output = "C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\G3_output.json";

    @Test
    void savTest() {
        double d1=ga1.shortestPathDist(0,3);
        d1=ga2.shortestPathDist(0,4);
        d1=Ga1.shortestPathDist(0,16);
        d1=Ga2.shortestPathDist(0,30);
        d1=Ga3.shortestPathDist(0,47);
        boolean save_my_g1 = ga1.save(my_g1_output);
        boolean save_my_g2 = ga2.save(my_g2_output);
        boolean save_G1 = Ga1.save(G1_output);
        boolean save_G2 = Ga2.save(G2_output);
        boolean save_G3 = Ga3.save(G3_output);
    }


}