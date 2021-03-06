package Graph;

import api.EdgeData;
import api.NodeData;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class JSONAlgoTest {

    //input path
    String G1 = "data/G1.json";
    String G2 = "data/G2.json";
    String G3 = "data/G3.json";
    String my_g1 = "src/jsonFiles/my_g1.json";
    String my_g2 = "src/jsonFiles/my_g2.json";
    String g1000 = "src/jsonFiles/1000Nodes.json";
    String g10000 = "src/jsonFiles/10000Nodes.json";

    // create DWGraph
    DWGraph g1_DWGraph = new DWGraph(my_g1);
    DWGraph g2_DWGraph = new DWGraph(my_g2);
    DWGraph G1_DWGraph = new DWGraph(G1);
    DWGraph G2_DWGraph = new DWGraph(G2);
    DWGraph G3_DWGraph = new DWGraph(G3);

    // output path
    String my_g1_output = "src/OutputFiles/my_g1_output.json";
    String my_g2_output = "src/OutputFiles/my_g2_output.json";
    String G1_output = "src/OutputFiles/G1_output.json";
    String G2_output = "src/OutputFiles/G2_output.json";
    String G3_output = "src/OutputFiles/G3_output.json";


    @Test
    void constructDWGraphTest() {
        //g1
        assertEquals(g1_DWGraph.nodes.get(0).getKey(), 0);
        assertEquals(g1_DWGraph.nodes.get(1).getKey(), 1);
        assertEquals(g1_DWGraph.nodes.get(2).getKey(), 2);
        assertEquals(g1_DWGraph.nodes.get(3).getKey(), 3);
        assertNotEquals(g1_DWGraph.getEdge(0, 1), null);
        assertNotEquals(g1_DWGraph.getEdge(1, 0), null);
        assertNotEquals(g1_DWGraph.getEdge(1, 2), null);
        assertNotEquals(g1_DWGraph.getEdge(2, 1), null);
        assertNotEquals(g1_DWGraph.getEdge(2, 3), null);
        assertNotEquals(g1_DWGraph.getEdge(3, 2), null);
        //g2
        assertEquals(g2_DWGraph.nodes.get(0).getKey(), 0);
        assertEquals(g2_DWGraph.nodes.get(1).getKey(), 1);
        assertEquals(g2_DWGraph.nodes.get(2).getKey(), 2);
        assertEquals(g2_DWGraph.nodes.get(3).getKey(), 3);
        assertEquals(g2_DWGraph.nodes.get(4).getKey(), 4);
        assertNotEquals(g2_DWGraph.getEdge(0, 1), null);
        assertNotEquals(g2_DWGraph.getEdge(0, 2), null);
        assertNotEquals(g2_DWGraph.getEdge(1, 2), null);
        assertNotEquals(g2_DWGraph.getEdge(1, 3), null);
        assertNotEquals(g2_DWGraph.getEdge(2, 0), null);
        assertNotEquals(g2_DWGraph.getEdge(2, 3), null);
        assertNotEquals(g2_DWGraph.getEdge(2, 4), null);
        assertNotEquals(g2_DWGraph.getEdge(3, 1), null);
        assertNotEquals(g2_DWGraph.getEdge(3, 2), null);
        assertNotEquals(g2_DWGraph.getEdge(4, 3), null);

        assertEquals(g2_DWGraph.getEdge(0, 1).getWeight(), 3);
        assertEquals(g2_DWGraph.getEdge(0, 2).getWeight(), 8);
        assertEquals(g2_DWGraph.getEdge(1, 2).getWeight(), 3);
        assertEquals(g2_DWGraph.getEdge(1, 3).getWeight(), 11);
        assertEquals(g2_DWGraph.getEdge(2, 0).getWeight(), 7);
        assertEquals(g2_DWGraph.getEdge(2, 3).getWeight(), 7);
        assertEquals(g2_DWGraph.getEdge(2, 4).getWeight(), 2);
        assertEquals(g2_DWGraph.getEdge(3, 1).getWeight(), 2);
        assertEquals(g2_DWGraph.getEdge(3, 2).getWeight(), 4);
        assertEquals(g2_DWGraph.getEdge(4, 3).getWeight(), 1);
    }

    GraphAlgo g1_GraphAlgo = new GraphAlgo();
    GraphAlgo OG_g1_GraphAlgo = new GraphAlgo(g1_DWGraph);
    GraphAlgo g2_GraphAlgo = new GraphAlgo();
    GraphAlgo OG_g2_GraphAlgo = new GraphAlgo(g2_DWGraph);
    GraphAlgo G1_GraphAlgo = new GraphAlgo();
    GraphAlgo OG_G1_GraphAlgo = new GraphAlgo(G1_DWGraph);
    GraphAlgo G2_GraphAlgo = new GraphAlgo();
    GraphAlgo OG_G2_GraphAlgo = new GraphAlgo(G2_DWGraph);
    GraphAlgo G3_GraphAlgo = new GraphAlgo();
    GraphAlgo OG_G3_GraphAlgo = new GraphAlgo(G3_DWGraph);

    @Test
    void load_DWGraph_To_GraphAlgo_Test() {
        g1_GraphAlgo.load(my_g1);
        Iterator<EdgeData> OG_Eit = OG_g1_GraphAlgo.myGraph.edgeIter();
        Iterator<EdgeData> load_Eit = g1_GraphAlgo.myGraph.edgeIter();
        while (OG_Eit.hasNext() && load_Eit.hasNext()) {
            EdgeData OGE = OG_Eit.next(), loadE = load_Eit.next();
            assertEquals(OGE.getSrc(), loadE.getSrc());
            assertEquals(OGE.getDest(), loadE.getDest());
            assertEquals(OGE.getWeight(), loadE.getWeight());
        }
        Iterator<NodeData> OG_Nit = OG_g1_GraphAlgo.myGraph.nodeIter();
        Iterator<NodeData> load_Nit = g1_GraphAlgo.myGraph.nodeIter();
        while (OG_Nit.hasNext() && load_Nit.hasNext()) {
            NodeData OGN = OG_Nit.next(), loadN = load_Nit.next();
            assertEquals(OGN.getKey(), loadN.getKey());
            assertEquals(OGN.getLocation().x(), loadN.getLocation().x());
            assertEquals(OGN.getLocation().y(), loadN.getLocation().y());
            assertEquals(OGN.getLocation().z(), loadN.getLocation().z());
        }

        g2_GraphAlgo.load(my_g2);
        Iterator<EdgeData> OG_Eit2 = OG_g2_GraphAlgo.myGraph.edgeIter();
        Iterator<EdgeData> load_Eit2 = g2_GraphAlgo.myGraph.edgeIter();
        while (OG_Eit2.hasNext() && load_Eit2.hasNext()) {
            EdgeData OGE = OG_Eit2.next(), loadE = load_Eit2.next();
            assertEquals(OGE.getSrc(), loadE.getSrc());
            assertEquals(OGE.getDest(), loadE.getDest());
            assertEquals(OGE.getWeight(), loadE.getWeight());
        }
        Iterator<NodeData> OG_Nit2 = OG_g2_GraphAlgo.myGraph.nodeIter();
        Iterator<NodeData> load_Nit2 = g1_GraphAlgo.myGraph.nodeIter();
        while (OG_Nit2.hasNext() && load_Nit2.hasNext()) {
            NodeData OGN = OG_Nit2.next(), loadN = load_Nit2.next();
            assertEquals(OGN.getKey(), loadN.getKey());
            assertEquals(OGN.getLocation().x(), loadN.getLocation().x());
            assertEquals(OGN.getLocation().y(), loadN.getLocation().y());
            assertEquals(OGN.getLocation().z(), loadN.getLocation().z());
        }
    }

    @Test
    void save_GraphAlgo_To_JSON_Test() {
        // g1
        g1_GraphAlgo.load(my_g1);
        g1_GraphAlgo.save(my_g1_output);
        Iterator<EdgeData> OG_Eit = OG_g1_GraphAlgo.myGraph.edgeIter();
        Iterator<EdgeData> load_Eit = g1_GraphAlgo.myGraph.edgeIter();
        while (OG_Eit.hasNext() && load_Eit.hasNext()) {
            EdgeData OGE = OG_Eit.next(), loadE = load_Eit.next();
            assertEquals(OGE.getSrc(), loadE.getSrc());
            assertEquals(OGE.getDest(), loadE.getDest());
            assertEquals(OGE.getWeight(), loadE.getWeight());
        }
        Iterator<NodeData> OG_Nit = OG_g1_GraphAlgo.myGraph.nodeIter();
        Iterator<NodeData> load_Nit = g1_GraphAlgo.myGraph.nodeIter();
        while (OG_Nit.hasNext() && load_Nit.hasNext()) {
            NodeData OGN = OG_Nit.next(), loadN = load_Nit.next();
            assertEquals(OGN.getKey(), loadN.getKey());
            assertEquals(OGN.getLocation().x(), loadN.getLocation().x());
            assertEquals(OGN.getLocation().y(), loadN.getLocation().y());
            assertEquals(OGN.getLocation().z(), loadN.getLocation().z());
        }
        // g2
        g2_GraphAlgo.load(my_g2);
        g2_GraphAlgo.save(my_g2_output);
        Iterator<EdgeData> OG_Eit2 = OG_g2_GraphAlgo.myGraph.edgeIter();
        Iterator<EdgeData> load_Eit2 = g2_GraphAlgo.myGraph.edgeIter();
        while (OG_Eit2.hasNext() && load_Eit2.hasNext()) {
            EdgeData OGE = OG_Eit2.next(), loadE = load_Eit2.next();
            assertEquals(OGE.getSrc(), loadE.getSrc());
            assertEquals(OGE.getDest(), loadE.getDest());
            assertEquals(OGE.getWeight(), loadE.getWeight());
        }
        Iterator<NodeData> OG_Nit2 = OG_g2_GraphAlgo.myGraph.nodeIter();
        Iterator<NodeData> load_Nit2 = g1_GraphAlgo.myGraph.nodeIter();
        while (OG_Nit2.hasNext() && load_Nit2.hasNext()) {
            NodeData OGN = OG_Nit2.next(), loadN = load_Nit2.next();
            assertEquals(OGN.getKey(), loadN.getKey());
            assertEquals(OGN.getLocation().x(), loadN.getLocation().x());
            assertEquals(OGN.getLocation().y(), loadN.getLocation().y());
            assertEquals(OGN.getLocation().z(), loadN.getLocation().z());
        }
        // G1
        G1_GraphAlgo.load(G1);
        G1_GraphAlgo.save(G1_output);
        OG_Eit2 = OG_G1_GraphAlgo.myGraph.edgeIter();
        load_Eit2 = G1_GraphAlgo.myGraph.edgeIter();
        while (OG_Eit2.hasNext() && load_Eit2.hasNext()) {
            EdgeData OGE = OG_Eit2.next(), loadE = load_Eit2.next();
            assertEquals(OGE.getSrc(), loadE.getSrc());
            assertEquals(OGE.getDest(), loadE.getDest());
            assertEquals(OGE.getWeight(), loadE.getWeight());
        }
        OG_Nit2 = OG_G1_GraphAlgo.myGraph.nodeIter();
        load_Nit2 = G1_GraphAlgo.myGraph.nodeIter();
        while (OG_Nit2.hasNext() && load_Nit2.hasNext()) {
            NodeData OGN = OG_Nit2.next(), loadN = load_Nit2.next();
            assertEquals(OGN.getKey(), loadN.getKey());
            assertEquals(OGN.getLocation().x(), loadN.getLocation().x());
            assertEquals(OGN.getLocation().y(), loadN.getLocation().y());
            assertEquals(OGN.getLocation().z(), loadN.getLocation().z());
        }
        // G2
        G2_GraphAlgo.load(G2);
        G2_GraphAlgo.save(G2_output);
        OG_Eit2 = OG_G2_GraphAlgo.myGraph.edgeIter();
        load_Eit2 = G2_GraphAlgo.myGraph.edgeIter();
        while (OG_Eit2.hasNext() && load_Eit2.hasNext()) {
            EdgeData OGE = OG_Eit2.next(), loadE = load_Eit2.next();
            assertEquals(OGE.getSrc(), loadE.getSrc());
            assertEquals(OGE.getDest(), loadE.getDest());
            assertEquals(OGE.getWeight(), loadE.getWeight());
        }
        OG_Nit2 = OG_G2_GraphAlgo.myGraph.nodeIter();
        load_Nit2 = G2_GraphAlgo.myGraph.nodeIter();
        while (OG_Nit2.hasNext() && load_Nit2.hasNext()) {
            NodeData OGN = OG_Nit2.next(), loadN = load_Nit2.next();
            assertEquals(OGN.getKey(), loadN.getKey());
            assertEquals(OGN.getLocation().x(), loadN.getLocation().x());
            assertEquals(OGN.getLocation().y(), loadN.getLocation().y());
            assertEquals(OGN.getLocation().z(), loadN.getLocation().z());
        }
        // G3
        G3_GraphAlgo.load(G3);
        G3_GraphAlgo.save(G3_output);
        OG_Eit2 = OG_G3_GraphAlgo.myGraph.edgeIter();
        load_Eit2 = G3_GraphAlgo.myGraph.edgeIter();
        while (OG_Eit2.hasNext() && load_Eit2.hasNext()) {
            EdgeData OGE = OG_Eit2.next(), loadE = load_Eit2.next();
            assertEquals(OGE.getSrc(), loadE.getSrc());
            assertEquals(OGE.getDest(), loadE.getDest());
            assertEquals(OGE.getWeight(), loadE.getWeight());
        }
        OG_Nit2 = OG_G3_GraphAlgo.myGraph.nodeIter();
        load_Nit2 = G3_GraphAlgo.myGraph.nodeIter();
        while (OG_Nit2.hasNext() && load_Nit2.hasNext()) {
            NodeData OGN = OG_Nit2.next(), loadN = load_Nit2.next();
            assertEquals(OGN.getKey(), loadN.getKey());
            assertEquals(OGN.getLocation().x(), loadN.getLocation().x());
            assertEquals(OGN.getLocation().y(), loadN.getLocation().y());
            assertEquals(OGN.getLocation().z(), loadN.getLocation().z());
        }

    }

    GraphAlgo ga1 = new GraphAlgo(new DWGraph(my_g1));
    GraphAlgo ga2 = new GraphAlgo(new DWGraph(my_g2));
    GraphAlgo Ga1 = new GraphAlgo(new DWGraph(G1));
    GraphAlgo Ga2 = new GraphAlgo(new DWGraph(G2));
    GraphAlgo Ga3 = new GraphAlgo(new DWGraph(G3));

    @Test
    void saveTest() {
        double d = ga1.shortestPathDist(0, 3);
//        JSON json =new JSON(ga1.myGraph);
//        json.toJSONFile(my_g1_output);
        ga1.save(my_g1_output);
    }

//    @Test
//    void toJSONFileTest() {
//        g1_json.toJSONFile("C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\g1_output.json");
//        JSON g1_OP_test = new JSON("C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\g1_output.json");
//        assertEquals(g1_json.graphObj.toString(), g1_OP_test.graphObj.toString());
//        g2_json.toJSONFile("C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\g2_output.json");
//        JSON g2_OP_test = new JSON("C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\g2_output.json");
//        assertEquals(g2_json.graphObj.toString(), g2_OP_test.graphObj.toString());
//        G1_json.toJSONFile("C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\G1_output.json");
//        JSON G1_OP_test = new JSON("C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\G1_output.json");
//        assertEquals(G1_json.graphObj.toString(), G1_OP_test.graphObj.toString());
//        G2_json.toJSONFile("C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\G2_output.json");
//        JSON G2_OP_test = new JSON("C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\G2_output.json");
//        assertEquals(G2_json.graphObj.toString(), G2_OP_test.graphObj.toString());
//        G3_json.toJSONFile("C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\G3_output.json");
//        JSON G3_OP_test = new JSON("C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\G3_output.json");
//        assertEquals(G3_json.graphObj.toString(), G3_OP_test.graphObj.toString());
//    }
}