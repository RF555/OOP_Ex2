import api.EdgeData;
import api.NodeData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GraphAlgoTest {
    String g1 = "C:\\Users\\Matanel\\IdeaProjects\\OOP=Ex3\\src\\jsonFiles\\g1.json";
    DWGraph MyGraph= new DWGraph(g1);
    GraphAlgo myGraph = new GraphAlgo(MyGraph);

    @Test
    void TestTranspose(){
        Boolean flag = myGraph.isConnected();
        Assertions.assertTrue(flag);
    }

}