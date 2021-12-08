import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JSONAlgoTest {
    //roeyf
//    String st1 = "C:\\Users\\roeyf\\OneDrive - Ariel University\\Matanel&Roey\\Ex2-OOP\\data\\G1.json";
//    String st2 = "C:\\Users\\roeyf\\OneDrive - Ariel University\\Matanel&Roey\\Ex2-OOP\\data\\G2.json";
//    String st3 = "C:\\Users\\roeyf\\OneDrive - Ariel University\\Matanel&Roey\\Ex2-OOP\\data\\G3.json";
//    String g1 = "C:\\Users\\roeyf\\OneDrive - Ariel University\\Matanel&Roey\\Ex2-OOP\\src\\jsonFiles\\g1.json";

    //Roey
    String st1 = "C:\\Users\\Roey\\OneDrive - Ariel University\\Matanel&Roey\\Ex2-OOP\\data\\G1.json";
    String st2 = "C:\\Users\\Roey\\OneDrive - Ariel University\\Matanel&Roey\\Ex2-OOP\\data\\G2.json";
    String st3 = "C:\\Users\\Roey\\OneDrive - Ariel University\\Matanel&Roey\\Ex2-OOP\\data\\G3.json";
    String g1 = "C:\\Users\\Roey\\OneDrive - Ariel University\\Matanel&Roey\\Ex2-OOP\\src\\jsonFiles\\g1.json";

    JSON g1_json = new JSON(g1);
    JSON G1_json = new JSON(st1);
    JSON G2_json = new JSON(st2);
    JSON G3_json = new JSON(st3);

    @Test
    void typeTest() {
        assertSame(g1_json.EdgeJsonArr.getClass(), JSONArray.class);
        assertSame(g1_json.NodeJsonArr.getClass(), JSONArray.class);
        assertSame(g1_json.graphObj.getClass(), JSONObject.class);
    }

    @Test
    void generateNodeTest() {
        for (int i = 0; i < g1_json.NodeJsonArr.length(); i++) {
            GeoLocationData gld = new GeoLocationData(i, i, 0);
            assertEquals(g1_json.generateNode(i).toString(), (new Node(i, gld)).toString());
        }
    }

    @Test
    void generateEdgeTest() {
        for (int i = 0; i < g1_json.EdgeJsonArr.length() / 2; i++) {
            assertEquals(g1_json.generateEdge(2 * i).toString(), (new Edge(i, i + 1, 1)).toString());
            assertEquals(g1_json.generateEdge(2 * i + 1).toString(), (new Edge(i + 1, i, 1)).toString());
        }
    }

    @Test
    void toJSONFileTest() {
        g1_json.toJSONFile("C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\g1_output.json");
        JSON g1_OP_test = new JSON("C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\g1_output.json");
        assertEquals(g1_json.graphObj.toString(), g1_OP_test.graphObj.toString());
        G1_json.toJSONFile("C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\G1_output.json");
        JSON G1_OP_test = new JSON("C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\G1_output.json");
        assertEquals(G1_json.graphObj.toString(), G1_OP_test.graphObj.toString());
        G2_json.toJSONFile("C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\G2_output.json");
        JSON G2_OP_test = new JSON("C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\G2_output.json");
        assertEquals(G2_json.graphObj.toString(), G2_OP_test.graphObj.toString());
        G3_json.toJSONFile("C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\G3_output.json");
        JSON G3_OP_test = new JSON("C:\\Users\\Roey\\Documents\\GitHub\\OOP_Ex2\\src\\OutputFiles\\G3_output.json");
        assertEquals(G3_json.graphObj.toString(), G3_OP_test.graphObj.toString());
    }
}