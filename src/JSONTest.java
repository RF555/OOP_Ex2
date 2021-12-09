import org.junit.jupiter.api.Test;
import org.json.JSONArray;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.*;

class JSONTest {
    //roeyf
//    String G1 = "C:\\Users\\roeyf\\Documents\\GitHub\\OOP_Ex2\\data\\G1.json";
//    String G2 = "C:\\Users\\roeyf\\Documents\\GitHub\\OOP_Ex2\\data\\G2.json";
//    String G3 = "C:\\Users\\roeyf\\Documents\\GitHub\\OOP_Ex2\\data\\G3.json";
//    String g1 = "C:\\Users\\roeyf\\Documents\\GitHub\\OOP_Ex2\\src\\jsonFiles\\my_g1.json";
//    String g2 = "C:\\Users\\roeyf\\Documents\\GitHub\\OOP_Ex2\\src\\jsonFiles\\my_g2.json";
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

    JSON my_g1_json = new JSON(my_g1);
    JSON my_g2_json = new JSON(my_g2);
    JSON G1_json = new JSON(G1);
    JSON G2_json = new JSON(G2);
    JSON G3_json = new JSON(G3);

    @Test
    void typeTest() {
        // g1
        assertSame(my_g1_json.EdgeJsonArr.getClass(), JSONArray.class);
        assertSame(my_g1_json.NodeJsonArr.getClass(), JSONArray.class);
        assertSame(my_g1_json.graphObj.getClass(), JSONObject.class);
        //g2
        assertSame(my_g2_json.EdgeJsonArr.getClass(), JSONArray.class);
        assertSame(my_g2_json.NodeJsonArr.getClass(), JSONArray.class);
        assertSame(my_g2_json.graphObj.getClass(), JSONObject.class);
        //G1
        assertSame(G1_json.EdgeJsonArr.getClass(), JSONArray.class);
        assertSame(G1_json.NodeJsonArr.getClass(), JSONArray.class);
        assertSame(G1_json.graphObj.getClass(), JSONObject.class);
        //G2
        assertSame(G2_json.EdgeJsonArr.getClass(), JSONArray.class);
        assertSame(G2_json.NodeJsonArr.getClass(), JSONArray.class);
        assertSame(G2_json.graphObj.getClass(), JSONObject.class);
        //G3
        assertSame(G3_json.EdgeJsonArr.getClass(), JSONArray.class);
        assertSame(G3_json.NodeJsonArr.getClass(), JSONArray.class);
        assertSame(G3_json.graphObj.getClass(), JSONObject.class);
    }

    //crate DWGraph g1
    DWGraph my_g1_DWGraph = new DWGraph(my_g1);
    DWGraph my_g2_DWGraph = new DWGraph(my_g2);

    @Test
    void constructDWGraphTest() {
        //g1
        assertEquals(my_g1_DWGraph.nodes.get(0).getKey(), 0);
        assertEquals(my_g1_DWGraph.nodes.get(1).getKey(), 1);
        assertEquals(my_g1_DWGraph.nodes.get(2).getKey(), 2);
        assertEquals(my_g1_DWGraph.nodes.get(3).getKey(), 3);
        assertNotEquals(my_g1_DWGraph.getEdge(0, 1), null);
        assertNotEquals(my_g1_DWGraph.getEdge(1, 0), null);
        assertNotEquals(my_g1_DWGraph.getEdge(1, 2), null);
        assertNotEquals(my_g1_DWGraph.getEdge(2, 1), null);
        assertNotEquals(my_g1_DWGraph.getEdge(2, 3), null);
        assertNotEquals(my_g1_DWGraph.getEdge(3, 2), null);
        //g2
        assertEquals(my_g2_DWGraph.nodes.get(0).getKey(), 0);
        assertEquals(my_g2_DWGraph.nodes.get(1).getKey(), 1);
        assertEquals(my_g2_DWGraph.nodes.get(2).getKey(), 2);
        assertEquals(my_g2_DWGraph.nodes.get(3).getKey(), 3);
        assertEquals(my_g2_DWGraph.nodes.get(4).getKey(), 4);
        assertNotEquals(my_g2_DWGraph.getEdge(0, 1), null);
        assertNotEquals(my_g2_DWGraph.getEdge(0, 2), null);
        assertNotEquals(my_g2_DWGraph.getEdge(1, 2), null);
        assertNotEquals(my_g2_DWGraph.getEdge(1, 3), null);
        assertNotEquals(my_g2_DWGraph.getEdge(2, 0), null);
        assertNotEquals(my_g2_DWGraph.getEdge(2, 3), null);
        assertNotEquals(my_g2_DWGraph.getEdge(2, 4), null);
        assertNotEquals(my_g2_DWGraph.getEdge(3, 1), null);
        assertNotEquals(my_g2_DWGraph.getEdge(3, 2), null);
        assertNotEquals(my_g2_DWGraph.getEdge(4, 3), null);

        assertEquals(my_g2_DWGraph.getEdge(0, 1).getWeight(), 3);
        assertEquals(my_g2_DWGraph.getEdge(0, 2).getWeight(), 8);
        assertEquals(my_g2_DWGraph.getEdge(1, 2).getWeight(), 3);
        assertEquals(my_g2_DWGraph.getEdge(1, 3).getWeight(), 11);
        assertEquals(my_g2_DWGraph.getEdge(2, 0).getWeight(), 7);
        assertEquals(my_g2_DWGraph.getEdge(2, 3).getWeight(), 7);
        assertEquals(my_g2_DWGraph.getEdge(2, 4).getWeight(), 2);
        assertEquals(my_g2_DWGraph.getEdge(3, 1).getWeight(), 2);
        assertEquals(my_g2_DWGraph.getEdge(3, 2).getWeight(), 4);
        assertEquals(my_g2_DWGraph.getEdge(4, 3).getWeight(), 1);


    }


    @Test
    void generateNodeTest() {
        for (int i = 0; i < my_g1_json.NodeJsonArr.length(); i++) {
            GeoLocationData gld = new GeoLocationData(i, i, 0);
            assertEquals(my_g1_json.generateNode(i).toString(), (new Node(i, gld)).toString());
        }
    }

    @Test
    void generateEdgeTest() {
        for (int i = 0; i < my_g1_json.EdgeJsonArr.length() / 2; i++) {
            assertEquals(my_g1_json.generateEdge(2 * i).toString(), (new Edge(i, i + 1, 1)).toString());
            assertEquals(my_g1_json.generateEdge(2 * i + 1).toString(), (new Edge(i + 1, i, 1)).toString());
        }
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
    void toJSONFileTest() {
        my_g1_json.toJSONFile(my_g1_output);
        JSON g1_OP_test = new JSON(my_g1_output);
        assertEquals(my_g1_json.graphObj.toString(), g1_OP_test.graphObj.toString());
        my_g2_json.toJSONFile(my_g2_output);
        JSON g2_OP_test = new JSON(my_g2_output);
        assertEquals(my_g2_json.graphObj.toString(), g2_OP_test.graphObj.toString());
        G1_json.toJSONFile(G1_output);
        JSON G1_OP_test = new JSON(G1_output);
        assertEquals(G1_json.graphObj.toString(), G1_OP_test.graphObj.toString());
        G2_json.toJSONFile(G2_output);
        JSON G2_OP_test = new JSON(G2_output);
        assertEquals(G2_json.graphObj.toString(), G2_OP_test.graphObj.toString());
        G3_json.toJSONFile(G3_output);
        JSON G3_OP_test = new JSON(G3_output);
        assertEquals(G3_json.graphObj.toString(), G3_OP_test.graphObj.toString());
    }
}