import api.EdgeData;
import api.NodeData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GraphAlgoTest {
//    String sg1 = "C:\\Users\\roeyf\\Documents\\GitHub\\OOP_Ex2\\src\\jsonFiles\\g1.json";
//    String sg2 = "C:\\Users\\roeyf\\Documents\\GitHub\\OOP_Ex2\\src\\jsonFiles\\g2.json";
//    GraphAlgo ga1 = new GraphAlgo(new DWGraph(sg1));
//    GraphAlgo ga2 = new GraphAlgo(new DWGraph(sg2));

    String sg1 = "C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\jsonFiles\\g1.json";
    String sg2 = "C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\jsonFiles\\g2.json";
    GraphAlgo ga1 = new GraphAlgo(new DWGraph(sg1));
    GraphAlgo ga2 = new GraphAlgo(new DWGraph(sg2));

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
        List<NodeData> nodeList01= ga1.shortestPath(0,1);
        Assertions.assertEquals(nodeList01.get(0).getKey(),0);
        Assertions.assertEquals(nodeList01.get(1).getKey(),1);
        List<NodeData> nodeList02= ga1.shortestPath(0,2);
        Assertions.assertEquals(nodeList02.get(0).getKey(),0);
        Assertions.assertEquals(nodeList02.get(1).getKey(),1);
        Assertions.assertEquals(nodeList02.get(2).getKey(),2);
        List<NodeData> nodeList20= ga1.shortestPath(2,0);
        Assertions.assertEquals(nodeList20.get(0).getKey(),2);
        Assertions.assertEquals(nodeList20.get(1).getKey(),1);
        Assertions.assertEquals(nodeList20.get(2).getKey(),0);
        List<NodeData> nodeList03= ga1.shortestPath(0,3);
        Assertions.assertEquals(nodeList03.get(0).getKey(),0);
        Assertions.assertEquals(nodeList03.get(1).getKey(),1);
        Assertions.assertEquals(nodeList03.get(2).getKey(),2);
        Assertions.assertEquals(nodeList03.get(3).getKey(),3);
        List<NodeData> nodeList30= ga1.shortestPath(3,0);
        Assertions.assertEquals(nodeList30.get(0).getKey(),3);
        Assertions.assertEquals(nodeList30.get(1).getKey(),2);
        Assertions.assertEquals(nodeList30.get(2).getKey(),1);
        Assertions.assertEquals(nodeList30.get(3).getKey(),0);

    }
}