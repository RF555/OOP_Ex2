import api.EdgeData;
import api.NodeData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GraphAlgoTest {
    String sg1 = "C:\\Users\\roeyf\\Documents\\GitHub\\OOP_Ex2\\src\\jsonFiles\\g1.json";
    String sg2 = "C:\\Users\\roeyf\\Documents\\GitHub\\OOP_Ex2\\src\\jsonFiles\\g2.json";
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
//        assertEquals(ga1.shortestPathDist(1, 1), 0);
//        assertEquals(ga1.shortestPathDist(2, 2), 0);
//        assertEquals(ga1.shortestPathDist(3, 3), 0);
//        assertEquals(ga1.shortestPathDist(0, 1), 1);
//        assertEquals(ga1.shortestPathDist(1, 0), 1);
//        assertEquals(ga1.shortestPathDist(2, 1), 1);
//        assertEquals(ga1.shortestPathDist(1, 2), 1);
//        assertEquals(ga1.shortestPathDist(2, 3), 1);
//        assertEquals(ga1.shortestPathDist(3, 2), 1);
//        assertEquals(ga1.shortestPathDist(3, 1), Double.MAX_VALUE);
//        assertEquals(ga1.shortestPathDist(0, 2), 2);
//        assertEquals(ga1.shortestPathDist(0, 3), 3);
//        assertEquals(ga1.shortestPathDist(1, 3), 2);
//        assertEquals(ga1.shortestPathDist(1, 3), 2);
//        assertEquals(ga1.shortestPathDist(3, 1), 2);

    }

    @Test
    void shortestPathDistTest2() {
//        Assertions.assertEquals(ga2.shortestPathDist(0, 0), 0);
//        Assertions.assertEquals(ga2.shortestPathDist(1, 1), 0);
//        Assertions.assertEquals(ga2.shortestPathDist(2, 2), 0);
//        Assertions.assertEquals(ga2.shortestPathDist(3, 3), 0);
//        Assertions.assertEquals(ga2.shortestPathDist(4, 4), 0);
        Assertions.assertEquals(ga2.shortestPathDist(0, 1), 3);
        Assertions.assertEquals(ga2.shortestPathDist(0, 2), 6);
//        Assertions.assertEquals(ga2.shortestPathDist(0, 3), 13);
        Assertions.assertEquals(ga2.shortestPathDist(1, 0), 10);
        Assertions.assertEquals(ga2.shortestPathDist(1, 2), 3);
//        Assertions.assertEquals(ga2.shortestPathDist(1, 3), 10);
        Assertions.assertEquals(ga2.shortestPathDist(0, 4), 8);
        Assertions.assertEquals(ga2.shortestPathDist(4, 0), 12);

    }
}